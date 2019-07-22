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
public interface IScaleBaseFormula extends IFormula {
    public final static String TYPE_NAME = "SCL";

    /**
     * This method is called after the calculation of the condition base value of each
     * pricing condition. If the scaleBaseFormulaNumber is smaller than 600 or greater than 999
     * coding in a standard class is processed else the code of the class provided
     * by the customer is processed (600 <= scaleBaseFormulaNumber <= 999).
     * The scaleBaseFormulaNumber should be used to specify which logic will be processed.
     * This number is provided by the customizing of the pricing condition type.
     */
    public BigDecimal overwriteScaleBase(IPricingItemUserExit pricingItem, IPricingConditionUserExit pricingCondition,
        IGroupConditionUserExit groupCondition);

    // TODO: check if parameter IGroupConditionUserExit groupCondition is necessary

    /**
     * This method is called after the calculation of the condition base value of each
     * group condition on the pricing document level. If the scaleBaseFormulaNumber
     * is smaller than 600 or greater than 999 coding in a standard class is processed
     * else the code of the class provided by the customer is processed (600 <= scaleBaseFormulaNumber <= 999).
     * The scaleBaseFormulaNumber should be used to specify which logic will be processed.
     * This number is provided by the customizing of the pricing condition type.
     */
    public BigDecimal overwriteGroupScaleBase(IPricingDocumentUserExit pricingDocument,
        IGroupConditionUserExit groupCondition);
}
