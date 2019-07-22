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

import com.sap.spe.conversion.ICurrencyUnit;
import com.sap.spe.pricing.customizing.PricingCustomizingConstants;
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.BaseFormulaAdapter;
import com.sap.spe.pricing.transactiondata.userexit.ILastPriceUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;


/**
 * freegoods inclusive without item generation(example condition type NRAB)
 */
public class FreeGoodsInclusive extends BaseFormulaAdapter {
    public BigDecimal overwriteConditionBase(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
        BigDecimal freeGoodsQuantity = PricingTransactiondataConstants.ZERO;
        String freeGoodsQuantityString = pricingItem.getAttributeValue("FREE_QUANTITY");
        if (!freeGoodsQuantityString.equals("")) {
            freeGoodsQuantity = new BigDecimal(freeGoodsQuantityString.trim());
        }

        if ((pricingCondition.getVariantConditionFactor().compareTo(PricingTransactiondataConstants.ZERO) != 0)
                || (freeGoodsQuantity.compareTo(PricingTransactiondataConstants.ZERO) != 0)) {
            return null;
        }

        pricingCondition.setConditionRate(pricingItem.getNetPrice().getValue(),
            (ICurrencyUnit) pricingItem.getNetValue().getUnit());
        ILastPriceUserExit lastPrice = pricingItem.getUserExitLastPrice();
        pricingCondition.setFraction(lastPrice.getFraction());

        if ((lastPrice.getQuantity() != null)
                && (lastPrice.getQuantity().getValue().compareTo(PricingTransactiondataConstants.ZERO) != 0)) {
            pricingCondition.setConditionRateValue(pricingItem.getNetValue().getValue()
                                                                  .add(pricingItem.getTaxValue().getValue()).divide(lastPrice.getQuantity()
                                                                                                                                 .getValue(),
                    BigDecimal.ROUND_UP));
        }
        else {
            pricingCondition.setInactive(PricingCustomizingConstants.InactiveFlag.INACTIVE_DUE_TO_ERROR);
        }

        if (!pricingItem.isReturn()) {
            pricingCondition.setConditionRateValue(pricingCondition.getConditionRate().getValue().multiply(PricingTransactiondataConstants.MINUS_ONE));
        }

        if (freeGoodsQuantity.compareTo(PricingTransactiondataConstants.ZERO) > 0) {
            pricingCondition.setVariantConditionFactor(freeGoodsQuantity.divide(
                    pricingItem.getProductQuantity().getValue(), 16, BigDecimal.ROUND_UP));
        }
        else if (freeGoodsQuantity.compareTo(PricingTransactiondataConstants.ZERO) < 0) {
            pricingCondition.setVariantConditionFactor(null);
        }

        if (pricingCondition.getVariantConditionFactor().compareTo(PricingTransactiondataConstants.ZERO) == 0) {
            pricingCondition.setInactive(PricingCustomizingConstants.InactiveFlag.INVISIBLE);
        }

        if ((lastPrice.getQuantity() != null)) {
            return lastPrice.getQuantity().getValue();
        }

        return PricingTransactiondataConstants.ZERO;
    }
}
