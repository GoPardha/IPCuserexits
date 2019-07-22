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


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IGroupKeyFormula extends IFormula {
    public final static String TYPE_NAME = "GRP";

    /**
     * This method is called after the key of a group condition is determined
     * on the pricing document level.
     * If the keyFormulaNumber is smaller than 60 or greater than 99
     * coding in a standard class is processed else the code of the class provided
     * by the customer is processed (60 <= keyFormulaNumber <= 99).
     * The keyFormulaNumber should be used to specify which logic will be processed.
     * This number is provided by the customizing of the pricing condition type.
     */
    public String setGroupKey(IPricingDocumentUserExit pricingDocument, IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition, IGroupConditionUserExit groupCondition);
}
