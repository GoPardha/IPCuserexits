/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit.base;

import java.math.BigDecimal;

import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.BaseFormulaAdapter;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;


/**
 * Pricing for free item (100 % discount; example condition type R100)
 */
public class FullDiscount extends BaseFormulaAdapter {
    public BigDecimal overwriteConditionBase(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
        pricingCondition.setConditionRateValue(PricingTransactiondataConstants.MINUS_HUNDRED);
        return null;
    }
}
