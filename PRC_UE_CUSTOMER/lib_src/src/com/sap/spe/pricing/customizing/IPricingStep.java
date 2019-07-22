/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.customizing;

import com.sap.spe.condmgnt.customizing.IStep;
import com.sap.spe.condmgnt.customizing.IUserExitFormula;
import com.sap.spe.condmgnt.exception.FormulaIsMissingException;


public interface IPricingStep extends IStep {

    /** @return the authority level the user must have to work on this step.
     */
    public int getAuthorityLevel();

    /** @return the flag which specifies into which field the value of
     * the pricing condition will be copied.
     */
    public char getSubtotalFlag();

    /** @return my account key 1.
     */
    public String getAccountKey1();

    /** @return my account key 2.
     */
    public String getAccountKey2();

    /** @return the number of the fromStep (relevant for %-discounts or surcharges).
     */
    public int getFromStep();

    /** @return the number of the toStep (relevant for %-discounts or surcharges).
     */
    public int getToStep();

    /** @return true if I am statistical.
     */
    public boolean isStatistical();

    /** @return true if no automatic search of condition records is performed for me.
     */
    public boolean isManually();

    /** @return true if I am mandatory.
     */
    public boolean isMandatory();

    /** @return type of mandatory flag.
     */
    public char getMandatoryType();

    /** @return the number of the base formula.
     */
    public int getBaseFormulaNumber();
    
    public IUserExitFormula getBaseFormula() throws FormulaIsMissingException;
    
    public IUserExitFormula getValueFormula() throws FormulaIsMissingException;

    

    /** @return the number of the value formula number.
     */
    public int getValueFormulaNumber();

    /** @return the print ID.
     */
    public char getPrintId();
}
