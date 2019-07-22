/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit;

import com.sap.spe.condmgnt.customizing.userexit.IFormula;
import com.sap.spe.condmgnt.exception.FormulaIsMissingException;
import com.sap.spe.pricing.transactiondata.IPricingDocumentCommon;


/**The pricing document contains accumulated pricing information of all pricing items
 * and provides methods which are processing all pricing items. The methods
 * of this interface can be used in the SPE userexits.*/
public interface IPricingDocumentUserExit extends IPricingDocumentCommon {

	/** generic convenience method for delegation to other user exit implementation
	 */ 
	public IFormula getFormula(String typeName, int number) throws FormulaIsMissingException;

	/** convenience method for delegation to other base formula implementation
	 */ 
	public IBaseFormula getBaseFormula(int number) throws FormulaIsMissingException;

	/** convenience method for delegation to other scale base formula implementation
	 */ 
	public IScaleBaseFormula getScaleBaseFormula(int number) throws FormulaIsMissingException;

	/** convenience method for delegation to other value formula implementation
	 */ 
	public IValueFormula getValueFormula(int number) throws FormulaIsMissingException;

    /** @return the document conditions
     */
    public IPricingConditionUserExit[] getUserExitConditions();

    /** @return the document items which are already priced
     */
    public IPricingItemUserExit[] getUserExitItems();
    
    /**
     * Set the flag which retains the return flag for header group
     * conditions.
     */
    
    public void setRetainReturnFlagForHdrGroupConditions( boolean flag);
    
    /**
     * @return the flag which retains the return flag for header group
     * conditions.
     */
    
    public boolean getRetainReturnFlagForHdrGroupConditions();
    
    /**
     * for checking whether optimized group process mode is active or not
     * @return true if optimized mode is active otherwise false
     */
	public boolean isOptimizedGroupProcessMode();
	
	// flags for edit mode
	/**
     * get edit mode
     *
     * @return edit mode (READ_WRITE = 'A' or READ_ONLY = 'B')
     */	
	public char getEditMode();

    public IPricingItemUserExit findUserExitItem(String itemGuid);
	 
}
