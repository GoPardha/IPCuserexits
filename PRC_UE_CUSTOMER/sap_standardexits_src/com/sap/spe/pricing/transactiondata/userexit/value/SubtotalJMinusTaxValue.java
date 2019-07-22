/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit.value;

import java.math.BigDecimal;

import com.sap.spe.pricing.customizing.PricingCustomizingConstants;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ValueFormulaAdapter;


/**
 * Net 2 Berlin method
 */
public class SubtotalJMinusTaxValue extends ValueFormulaAdapter {
    public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
        return pricingItem.getSubtotal(PricingCustomizingConstants.ConditionSubtotal.SUBTOTAL_J).getValue().subtract(pricingItem.getTaxValue()
                                                                                                                                    .getValue());
    }
}
