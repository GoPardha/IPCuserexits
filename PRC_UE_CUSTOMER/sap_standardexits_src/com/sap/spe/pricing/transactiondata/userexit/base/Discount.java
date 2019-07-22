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

import com.sap.spe.pricing.transactiondata.userexit.BaseFormulaAdapter;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Discount extends BaseFormulaAdapter {
    public BigDecimal overwriteConditionBase(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
        String discountAmountIsNet = pricingItem.getAttributeValue("BASE_IS_NET_VALUE");

        if (discountAmountIsNet.equals("")) {

            // take gross value as discount base
            return pricingItem.getNetValue().getValue().add(pricingItem.getTaxValue().getValue());
        }

        // take net value as discount base
        return pricingItem.getNetValue().getValue();
    }
}
