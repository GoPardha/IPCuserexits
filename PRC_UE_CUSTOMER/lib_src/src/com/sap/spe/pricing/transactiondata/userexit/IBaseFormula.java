/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit;

import java.math.BigDecimal;

import com.sap.spe.condmgnt.customizing.userexit.IFormula;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IBaseFormula extends IFormula {
    public final static String TYPE_NAME = "BAS";

    /**
     * This method is called after the calculation of the condition base value of each
     * pricing condition. If the baseFormulaNumber is smaller than 600 or greater than 999
     * coding in a standard class is processed else the code of the class provided
     * by the customer is processed (600 <= baseFormulaNumber <= 999).
     * The baseFormulaNumber should be used to specify which logic will be processed.
     * This number is provided by the customizing of the pricing step.
     */
    public BigDecimal overwriteConditionBase(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition);
}
