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
public interface IValueFormula extends IFormula {
    public final static String TYPE_NAME = "VAL";

    /**
     * This method is called after the calculation of the condition value of each
     * pricing condition. If the valueFormulaNumber is smaller than 600 or greater than 999
     * coding in a standard class is processed else the code of the class provided
     * by the customer is processed (600 <= valueFormulaNumber <= 999).
     * The valueFormulaNumber should be used to specify which logic will be processed.
     * This number is provided by the customizing of the pricing step.
     */
    public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition);

    /**
     * This method is called after the calculation of the condition value of each
     * group condition on the pricing document level.
     * If the valueFormulaNumber is smaller than 600 or greater than 999
     * coding in a standard class is processed else the code of the class provided
     * by the customer is processed (600 <= valueFormulaNumber <= 999).
     * The valueFormulaNumber should be used to specify which logic will be processed.
     * This number is provided by the customizing of the pricing step.
     */
    public BigDecimal overwriteGroupConditionValue(IPricingDocumentUserExit pricingDocument,
        IGroupConditionUserExit groupCondition);
}
