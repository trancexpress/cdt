/*
 *(c) Copyright QNX Software Systems Ltd. 2002.
 * All Rights Reserved.
 * 
 */
package org.eclipse.cdt.debug.internal.core;

import java.util.ArrayList;

import org.eclipse.cdt.debug.core.ICSignalManager;
import org.eclipse.cdt.debug.core.cdi.CDIException;
import org.eclipse.cdt.debug.core.cdi.ICDIManager;
import org.eclipse.cdt.debug.core.cdi.model.ICDISignal;
import org.eclipse.cdt.debug.core.model.ICSignal;
import org.eclipse.cdt.debug.internal.core.model.CDebugTarget;
import org.eclipse.cdt.debug.internal.core.model.CSignal;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;

/**
 * Enter type comment.
 * 
 * @since: Jan 31, 2003
 */
public class CSignalManager extends CUpdateManager implements ICSignalManager
{
	private ICSignal[] fSignals = null;
	private boolean fIsDisposed = false;

	/**
	 * Constructor for CSignalManager.
	 */
	public CSignalManager( CDebugTarget target )
	{
		super( target );
	}

	/* (non-Javadoc)
	 * @see org.eclipse.cdt.debug.core.ICSignalManager#getSignals()
	 */
	public ICSignal[] getSignals() throws DebugException
	{
		if ( !isDisposed() && fSignals == null )
		{
			try
			{
				ICDISignal[] cdiSignals = ((CDebugTarget)getDebugTarget()).getCDISession().getSignalManager().getSignals();
				ArrayList list = new ArrayList( cdiSignals.length );
				for ( int i = 0; i < cdiSignals.length; ++i )
				{
					list.add( new CSignal( (CDebugTarget)getDebugTarget(), cdiSignals[i] ) );
				}
				fSignals = (ICSignal[])list.toArray( new ICSignal[list.size()] );
			}
			catch( CDIException e )
			{
			}
		}
		return ( fSignals != null ) ? fSignals : new ICSignal[0];
	}

	/* (non-Javadoc)
	 * @see org.eclipse.cdt.debug.core.ICSignalManager#dispose()
	 */
	public void dispose()
	{
		if ( fSignals != null )
		for ( int i = 0; i < fSignals.length; ++i )
		{
			fSignals[i].dispose();
		}
		fSignals = null;
		fIsDisposed = true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(Class)
	 */
	public Object getAdapter( Class adapter )
	{
		if ( adapter.equals( ICSignalManager.class ) )
		{
			return this;
		}
		if ( adapter.equals( CSignalManager.class ) )
		{
			return this;
		}
		return super.getAdapter( adapter );
	}
	
	public void signalChanged( ICDISignal cdiSignal )
	{
		CSignal signal = find( cdiSignal );
		if ( signal != null )
		{
			signal.fireChangeEvent( DebugEvent.STATE );
		}
	}

	private CSignal find( ICDISignal cdiSignal )
	{
		try
		{
			ICSignal[] signals = getSignals();
			for ( int i = 0; i < signals.length; ++i )
				if ( signals[i].getName().equals( cdiSignal.getName() ) )
					return (CSignal)signals[i];
		}
		catch( DebugException e )
		{
		}
		return null;
	}
	
	protected boolean isDisposed()
	{
		return fIsDisposed;
	}

	protected ICDIManager getCDIManager()
	{
		if ( getDebugTarget() != null )
		{
			return ((CDebugTarget)getDebugTarget()).getCDISession().getSignalManager();
		}
		return null;
	}
}
