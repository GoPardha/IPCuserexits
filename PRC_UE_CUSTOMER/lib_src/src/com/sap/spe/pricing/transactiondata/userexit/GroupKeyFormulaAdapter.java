/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class GroupKeyFormulaAdapter implements IGroupKeyFormula {

    /* (non-Javadoc)
     * @see com.sap.spe.pricing.transactiondata.userexit.IGroupKeyFormula#setGroupConditionKey(com.sap.spe.pricing.transactiondata.IPricingDocumentUserExit, com.sap.spe.pricing.transactiondata.IPricingItemUserExit, com.sap.spe.pricing.transactiondata.IPricingConditionUserExit, com.sap.spe.pricing.transactiondata.IGroupConditionUserExit)
     */
    public String setGroupKey(IPricingDocumentUserExit pricingDocument, IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition, IGroupConditionUserExit groupCondition) {
        return null;
    }
}
