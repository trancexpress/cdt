package org.eclipse.cdt.internal.core.model;

/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */

class FunctionInfo extends SourceManipulationInfo {

	protected int flags;
	protected String returnType = "";
	protected int numOfParams;

	protected FunctionInfo (CElement element) {
		super(element);
		flags = 0;
	}

	protected int getAccessControl() {
		return flags;
	}

	protected void setAccessControl(int flags) {
		this.flags = flags;
	}

	protected String getReturnType(){
		return returnType;
	}
	
	protected void setReturnType(String type){
		returnType = type;
	}	
}
