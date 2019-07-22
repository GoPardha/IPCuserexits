/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit;

import com.sap.spe.conversion.IQuantityValue;
import com.sap.spe.pricing.customizing.ICopyType;
import com.sap.spe.pricing.customizing.IPricingType;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class PricingCopyFormulaAdapter implements IPricingCopyFormula {

    /* (non-Javadoc)
     * @see com.sap.spe.pricing.transactiondata.userexit.IPricingCopyFormula#pricingCopy(com.sap.spe.pricing.transactiondata.IPricingDocumentUserExit, com.sap.spe.pricing.transactiondata.IPricingItemUserExit, com.sap.spe.pricing.transactiondata.IPricingConditionUserExit, com.sap.spe.pricing.customizing.IPricingType, com.sap.spe.pricing.customizing.ICopyType, com.sap.spe.conversion.IQuantityValue)
     */
    public void pricingCopy(IPricingDocumentUserExit pricingDocument, IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition, IPricingType pricingType, ICopyType copyType,
        IQuantityValue sourceSalesQuantity) {
    }
}
