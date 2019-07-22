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
import com.sap.spe.conversion.IPhysicalUnit;
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.BaseFormulaAdapter;
import com.sap.spe.pricing.transactiondata.userexit.ILastPriceUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.RoundingFormula;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExactNetPrice extends BaseFormulaAdapter implements RoundingFormula {
    public BigDecimal overwriteConditionBase(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
        ILastPriceUserExit lastPrice = pricingItem.getUserExitLastPrice();
        pricingCondition.setPricingUnit(lastPrice.getPricingUnit().getValue(),
            (IPhysicalUnit) lastPrice.getPricingUnit().getUnit());
        pricingCondition.setFraction(lastPrice.getFraction());
        pricingCondition.setConditionRate(pricingCondition.getConditionRate().getValue(),
            pricingItem.getUserExitDocument().getDocumentCurrencyUnit());

        BigDecimal netPrice = PricingTransactiondataConstants.ZERO;

        if (pricingItem.getObjectForUserExits(NETPRICE_FOR_ROUNDING) != null) {
            netPrice = (BigDecimal) pricingItem.getObjectForUserExits(NETPRICE_FOR_ROUNDING);
        }

        if (netPrice.compareTo(PricingTransactiondataConstants.ZERO) == 0) {
            if (!pricingItem.isReturn()) {
                pricingCondition.setConditionRateValue(pricingItem.getNetPrice().getValue());
            }
            else {
                pricingCondition.setConditionRateValue(pricingItem.getNetPrice().getValue().negate());
            }
        }
        else {
            int scale = pricingCondition.getConditionRate().getUnit().getNumberOfDecimals();
            pricingCondition.setConditionRate(netPrice.setScale(scale, BigDecimal.ROUND_HALF_UP),
                (ICurrencyUnit) lastPrice.getRate().getUnit());
            pricingCondition.setExchangeRate(lastPrice.getExchangeRate());
        }

        return lastPrice.getQuantity().getValue();
    }
}
