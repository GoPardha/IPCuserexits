/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit.pricingcopy;

import com.sap.spe.conversion.IQuantityValue;
import com.sap.spe.pricing.customizing.ICopyType;
import com.sap.spe.pricing.customizing.IPricingType;
import com.sap.spe.pricing.customizing.PricingCustomizingConstants;
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.PricingCopyFormulaAdapter;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FixAndInvertValue extends PricingCopyFormulaAdapter {
    public void pricingCopy(IPricingDocumentUserExit pricingDocument, IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition, IPricingType pricingType, ICopyType copyType,
        IQuantityValue sourceSalesQuantity) {

        // corresponds to R/3 pricing type 'F' during copy
        // set condition control (KSTEU) to 'H'
        pricingCondition.setConditionControl(PricingCustomizingConstants.Control.VALUE_FIXED_FOR_COST_PRICE);

        // invert condition value
        pricingCondition.setConditionValue(pricingCondition.getConditionValue().getValue().multiply(PricingTransactiondataConstants.MINUS_ONE));

        // invert condition base for percentage conditions
        if (PricingCustomizingConstants.CalculationType.isFixedAmountOrPercentage(pricingCondition.getCalculationType())) {
            pricingCondition.setConditionBaseValue(pricingCondition.getConditionBase().getValue().multiply(PricingTransactiondataConstants.MINUS_ONE));
        }
    }
}
