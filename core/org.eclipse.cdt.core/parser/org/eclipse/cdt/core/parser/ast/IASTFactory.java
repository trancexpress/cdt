/**********************************************************************
 * Copyright (c) 2002,2003 Rational Software Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v0.5
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v05.html
 * 
 * Contributors: 
 * IBM Rational Software - Initial API and implementation
***********************************************************************/
package org.eclipse.cdt.core.parser.ast;
import java.util.List;

import org.eclipse.cdt.core.parser.IToken;
import org.eclipse.cdt.core.parser.ITokenDuple;
import org.eclipse.cdt.core.parser.ast.IASTClassSpecifier.ClassNameType;
import org.eclipse.cdt.core.parser.ast.IASTExpression.IASTNewExpressionDescriptor;
/**
 * @author jcamelon
 *
 */
public interface IASTFactory
{
    public IASTMacro createMacro(
        String name,
        int startingOffset,
        int nameOffset,
        int nameEndOffset, int endingOffset) ;
    public IASTInclusion createInclusion(
        String name,
        String fileName,
        boolean local,
        int startingOffset,
        int nameOffset,
        int nameEndOffset, int endingOffset) ;
    public IASTUsingDirective createUsingDirective(
        IASTScope scope,
        ITokenDuple duple, int startingOffset, int endingOffset)
        throws ASTSemanticException;
    public IASTUsingDeclaration createUsingDeclaration(
        IASTScope scope,
        boolean isTypeName,
        ITokenDuple name, int startingOffset, int endingOffset) throws ASTSemanticException;
    public IASTASMDefinition createASMDefinition(
        IASTScope scope,
        String assembly,
        int first,
        int last);
    public IASTNamespaceDefinition createNamespaceDefinition(
        IASTScope scope,
        String identifier,
        int startingOffset,
        int nameOffset, int nameEndOffset) throws ASTSemanticException;
        
    public IASTNamespaceAlias    createNamespaceAlias( 
    	IASTScope scope, 
    	String identifier, 
    	ITokenDuple alias, 
    	int startingOffset, 
    	int nameOffset, 
    	int nameEndOffset, int endOffset ) throws ASTSemanticException;
        
    public IASTCompilationUnit createCompilationUnit() ;
    public IASTLinkageSpecification createLinkageSpecification(
        IASTScope scope,
        String spec, int startingOffset) ;
    public IASTClassSpecifier createClassSpecifier(
        IASTScope scope,
        ITokenDuple name,
        ASTClassKind kind,
        ClassNameType type,
        ASTAccessVisibility access,
        int startingOffset,
        int nameOffset, int nameEndOffset) throws ASTSemanticException;
    /**
     * @param astClassSpec
     * @param isVirtual
     * @param visibility
     * @param string
     */
    public void addBaseSpecifier(
        IASTClassSpecifier astClassSpec,
        boolean isVirtual,
        ASTAccessVisibility visibility,
        ITokenDuple parentClassName) throws ASTSemanticException;
    public IASTElaboratedTypeSpecifier createElaboratedTypeSpecifier(
        IASTScope scope,
        ASTClassKind elaboratedClassKind,
        ITokenDuple typeName,
        int startingOffset, int endOffset, boolean isForewardDecl) throws ASTSemanticException;
    public IASTEnumerationSpecifier createEnumerationSpecifier(
        IASTScope scope,
        String name,
        int startingOffset, int nameOffset, int nameEndOffset) throws ASTSemanticException;
    public void addEnumerator(
        IASTEnumerationSpecifier enumeration,
        String string,
        int startingOffset,
        int nameOffset, int nameEndOffset, int endingOffset, IASTExpression initialValue)throws ASTSemanticException;
    public IASTExpression createExpression(
        IASTScope scope,
        IASTExpression.Kind kind,
        IASTExpression lhs,
        IASTExpression rhs,
        IASTExpression thirdExpression,
        IASTTypeId typeId,
        ITokenDuple idExpression, String literal, IASTNewExpressionDescriptor newDescriptor) throws ASTSemanticException;
    public IASTExpression.IASTNewExpressionDescriptor createNewDescriptor(List newPlacementExpressions,List newTypeIdExpressions,List newInitializerExpressions);
    public IASTInitializerClause createInitializerClause(
        IASTScope scope,
        IASTInitializerClause.Kind kind,
        IASTExpression assignmentExpression, List initializerClauses, List designators) ;
    public IASTExceptionSpecification createExceptionSpecification(IASTScope scope, List typeIds) throws ASTSemanticException;
    /**
     * @param exp
     */
    public IASTArrayModifier createArrayModifier(IASTExpression exp) ;
    /**
     * @param duple
     * @param expressionList
     * @return
     */
    public IASTConstructorMemberInitializer createConstructorMemberInitializer(
        IASTScope scope,
        ITokenDuple duple, IASTExpression expressionList) throws ASTSemanticException;
    public IASTSimpleTypeSpecifier createSimpleTypeSpecifier(
        IASTScope scope,
        IASTSimpleTypeSpecifier.Type kind,
        ITokenDuple typeName,
        boolean isShort,
        boolean isLong,
        boolean isSigned,
        boolean isUnsigned, boolean isTypename, boolean isComplex, boolean isImaginary) throws ASTSemanticException;
    public IASTFunction createFunction(
        IASTScope scope,
        ITokenDuple name,
        List parameters,
        IASTAbstractDeclaration returnType,
        IASTExceptionSpecification exception,
        boolean isInline,
        boolean isFriend,
        boolean isStatic,
        int startOffset,
        int nameOffset,
        int nameEndOffset,
		IASTTemplate ownerTemplate,
		boolean isConst,
		boolean isVolatile,
		boolean isVirtual,
		boolean isExplicit,
		boolean isPureVirtual, List constructorChain, boolean isDefinition, boolean hasFunctionTryBlock ) throws ASTSemanticException;
    public IASTAbstractDeclaration createAbstractDeclaration(
        boolean isConst,
        boolean isVolatile,
        IASTTypeSpecifier typeSpecifier,
        List pointerOperators, List arrayModifiers, List parameters, ASTPointerOperator pointerOperator);
    public IASTMethod createMethod(
        IASTScope scope,
        String name,
        List parameters,
        IASTAbstractDeclaration returnType,
        IASTExceptionSpecification exception,
        boolean isInline,
        boolean isFriend,
        boolean isStatic,
        int startOffset,
        int nameOffset,
        int nameEndOffset,
        IASTTemplate ownerTemplate,
        boolean isConst,
        boolean isVolatile,
        boolean isVirtual,
        boolean isExplicit,
        boolean isPureVirtual, ASTAccessVisibility visibility, List constructorChain, boolean isDefinition, boolean hasFunctionTryBlock) throws ASTSemanticException;
        
	public IASTVariable createVariable(IASTScope scope, String name, boolean isAuto, IASTInitializerClause initializerClause, IASTExpression bitfieldExpression, 
		   IASTAbstractDeclaration abstractDeclaration, boolean isMutable, boolean isExtern, boolean isRegister, boolean isStatic, int startingOffset, int nameOffset, int nameEndOffset, IASTExpression constructorExpression ) throws ASTSemanticException;
		   
	public IASTField createField( IASTScope scope, String name, boolean isAuto, IASTInitializerClause initializerClause, IASTExpression bitfieldExpression, IASTAbstractDeclaration abstractDeclaration, boolean isMutable, boolean isExtern, boolean isRegister, boolean isStatic, int startingOffset, int nameOffset, int nameEndOffset, IASTExpression constructorExpression, ASTAccessVisibility visibility) throws ASTSemanticException;

	public IASTDesignator createDesignator( IASTDesignator.DesignatorKind kind, IASTExpression constantExpression, IToken fieldIdentifier );
	
	public IASTParameterDeclaration createParameterDeclaration( boolean isConst, boolean isVolatile, IASTTypeSpecifier getTypeSpecifier, List pointerOperators, List arrayModifiers, List parameters, ASTPointerOperator pointerOp, String parameterName, IASTInitializerClause initializerClause, int startingOffset, int nameOffset, int nameEndOffset, int endingOffset ) ;
	
	public IASTTemplateDeclaration createTemplateDeclaration( IASTScope scope, List templateParameters, boolean exported, int startingOffset ) ; 

	public IASTTemplateParameter createTemplateParameter( IASTTemplateParameter.ParamKind kind, String identifier, String defaultValue, IASTParameterDeclaration parameter, List parms ) ; 

	public IASTTemplateInstantiation createTemplateInstantiation(IASTScope scope, int startingOffset); 
	
	public IASTTemplateSpecialization createTemplateSpecialization(IASTScope scope, int startingOffset); 
	
	public IASTTypedefDeclaration createTypedef( IASTScope scope, String name, IASTAbstractDeclaration mapping, int startingOffset, int nameOffset, int nameEndOffset ) throws ASTSemanticException;

	public IASTAbstractTypeSpecifierDeclaration createTypeSpecDeclaration( IASTScope scope, IASTTypeSpecifier typeSpecifier, IASTTemplate template, int startingOffset, int endingOffset);
	
	public boolean queryIsTypeName( IASTScope scope, ITokenDuple nameInQuestion ) ;

	static final String DOUBLE_COLON = "::";
	static final String TELTA = "~";
	/**
	 * @param scope
	 * @return
	 */
	public IASTCodeScope createNewCodeBlock(IASTScope scope);
	
	public IASTTypeId    createTypeId( IASTScope scope, IASTSimpleTypeSpecifier.Type kind, boolean isConst, boolean isVolatile, boolean isShort, 
			boolean isLong, boolean isSigned, boolean isUnsigned, boolean isTypename, ITokenDuple name, List pointerOps, List arrayMods ) throws ASTSemanticException;
    /**
     * @param astClassSpecifier
     */
    public void signalEndOfClassSpecifier(IASTClassSpecifier astClassSpecifier); 
						

}