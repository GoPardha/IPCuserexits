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
import com.sap.spe.conversion.IDimensionalValue;
import com.sap.spe.conversion.IPhysicalUnit;
import com.sap.spe.pricing.customizing.PricingCustomizingConstants;
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.BaseFormulaAdapter;
import com.sap.spe.pricing.transactiondata.userexit.ILastPriceUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NetPrice extends BaseFormulaAdapter {
    public BigDecimal overwriteConditionBase(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
        ICurrencyUnit documentCurrency = pricingItem.getUserExitDocument().getDocumentCurrencyUnit();
        ILastPriceUserExit lastPrice = pricingItem.getUserExitLastPrice();
        IDimensionalValue pricingUnitOfLastPrice = lastPrice.getPricingUnit();

        pricingCondition.setConditionRate(pricingItem.getNetPrice().getValue(), documentCurrency);
        pricingCondition.setPricingUnit(pricingUnitOfLastPrice.getValue(),
            (IPhysicalUnit) pricingUnitOfLastPrice.getUnit());
        pricingCondition.setFraction(lastPrice.getFraction());

        BigDecimal baseOfLastPrice = lastPrice.getBase().getValue();

        if (baseOfLastPrice.compareTo(PricingTransactiondataConstants.ZERO) != 0) {
            BigDecimal netValue = pricingItem.getNetValue().getValue();
            BigDecimal taxValue = pricingItem.getTaxValue().getValue();
            int numberDecimalOfCurrencyUnit = documentCurrency.getNumberOfDecimals();
            BigDecimal newConditionRate =
                (netValue.add(taxValue)).multiply(pricingUnitOfLastPrice.getValue()).divide(baseOfLastPrice,
                    numberDecimalOfCurrencyUnit, BigDecimal.ROUND_HALF_UP);

            pricingCondition.setConditionRateValue(newConditionRate);
        }
        else {
            pricingCondition.setInactive(PricingCustomizingConstants.InactiveFlag.INACTIVE_DUE_TO_ERROR);
        }

        if (pricingItem.isReturn()) {
            pricingCondition.setConditionRateValue(pricingCondition.getConditionRate().getValue().multiply(PricingTransactiondataConstants.MINUS_ONE));
        }

        return lastPrice.getBase().getValue();
    }
}
