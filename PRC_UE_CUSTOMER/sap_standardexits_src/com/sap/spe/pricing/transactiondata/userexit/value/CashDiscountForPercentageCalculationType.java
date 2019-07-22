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
import com.sap.spe.pricing.transactiondata.userexit.RoundingFormula;
import com.sap.spe.pricing.transactiondata.userexit.ValueFormulaAdapter;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CashDiscountForPercentageCalculationType extends ValueFormulaAdapter implements RoundingFormula {
    public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
        if (pricingCondition.getInactive() != PricingCustomizingConstants.InactiveFlag.NOT_INACTIVE) {
            return null;
        }

        BigDecimal netPrice = null;
        int noOfDecimals = 0;

        if (pricingItem.getObjectForUserExits(NETPRICE_FOR_ROUNDING) != null) {
            netPrice = (BigDecimal) pricingItem.getObjectForUserExits(NETPRICE_FOR_ROUNDING);
            noOfDecimals = netPrice.scale() + 2;
        }

        ILastPriceUserExit lastPrice = pricingItem.getUserExitLastPrice();
        if ((netPrice != null) || (netPrice.compareTo(PricingTransactiondataConstants.ZERO) == 0)) {
            netPrice = lastPrice.getRate().getValue();
            noOfDecimals = netPrice.scale() + 2;
        }

        if (PricingCustomizingConstants.CalculationType.PERCENTAGE == pricingCondition.getCalculationType()) {

            // 5 extra decimals are needed to make rounding unnecessary: 2 are already added above.
            netPrice =
                netPrice.add(lastPrice.getRate().getValue().multiply(pricingCondition.getConditionRate().getValue()
                                                                                         .divide(PricingTransactiondataConstants.HUNDRED,
                                noOfDecimals + 3, BigDecimal.ROUND_UNNECESSARY)));
            netPrice.setScale(noOfDecimals, BigDecimal.ROUND_HALF_UP);
        }
        else if (PricingCustomizingConstants.CalculationType.PERCENTAGE_FINANCING == pricingCondition
                .getCalculationType()) {

            // 9 extra decimals are needed to make rounding unnecessary: 2 are already added above.
            netPrice =
                netPrice.add(lastPrice.getRate().getValue().multiply(pricingCondition.getConditionRate().getValue()
                                                                                         .divide(PricingTransactiondataConstants.HUNDRED,
                                noOfDecimals + 7, BigDecimal.ROUND_UNNECESSARY)));
            netPrice.setScale(noOfDecimals, BigDecimal.ROUND_HALF_UP);
        }
        else {
            pricingCondition.setInactive(PricingCustomizingConstants.InactiveFlag.INACTIVE_DUE_TO_ERROR);

            return null;
        }

        // set netPrice
        pricingItem.setObjectForUserExits(NETPRICE_FOR_ROUNDING, netPrice);

        return null;
    }
}
