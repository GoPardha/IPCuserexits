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
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.ILastPriceUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ValueFormulaAdapter;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MinimumValueSurcharge extends ValueFormulaAdapter {
    public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
        BigDecimal xworkd =
            pricingItem.getSubtotal(PricingCustomizingConstants.ConditionSubtotal.SUBTOTAL_D).getValue();

        ILastPriceUserExit lastPrice = pricingItem.getUserExitLastPrice();
        if (lastPrice.getFactor() != null) {
            int scale = pricingItem.getUserExitDocument().getDocumentCurrencyUnit().getNumberOfDecimals();
            xworkd = xworkd.multiply(lastPrice.getFactor()).setScale(scale, BigDecimal.ROUND_HALF_UP);
        }

        BigDecimal netwr = pricingItem.getNetValue().getValue();

        if (xworkd.abs().compareTo(netwr.abs()) > 0) {
            return xworkd.subtract(netwr);
        }
        return PricingTransactiondataConstants.ZERO;
    }
}
