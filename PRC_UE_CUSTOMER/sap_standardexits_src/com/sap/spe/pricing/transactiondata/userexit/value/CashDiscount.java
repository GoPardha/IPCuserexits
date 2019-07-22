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

import com.sap.spe.conversion.ICurrencyUnit;
import com.sap.spe.pricing.customizing.PricingCustomizingConstants;
import com.sap.spe.pricing.transactiondata.IPricingCurrencyConversionResult;
import com.sap.spe.pricing.transactiondata.IPricingEngine;
import com.sap.spe.pricing.transactiondata.PricingEngineFactory;
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
public class CashDiscount extends ValueFormulaAdapter implements RoundingFormula {
    public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
        if (pricingCondition.getInactive() != PricingCustomizingConstants.InactiveFlag.NOT_INACTIVE) {
            return null;
        }

        try {
            BigDecimal netPrice = null;
            int noOfDecimals = 0;

            if (pricingItem.getObjectForUserExits(NETPRICE_FOR_ROUNDING) != null) {
                netPrice = (BigDecimal) pricingItem.getObjectForUserExits(NETPRICE_FOR_ROUNDING);
                noOfDecimals = netPrice.scale() + 2;
            }

            ILastPriceUserExit lastPrice = pricingItem.getUserExitLastPrice();
            if ((netPrice == null) || (netPrice.compareTo(PricingTransactiondataConstants.ZERO) == 0)) {
                netPrice = lastPrice.getRate().getValue();
                noOfDecimals = netPrice.scale() + 2;
            }

            BigDecimal rateValue = pricingCondition.getConditionRate().getValue();

            // currency conversion if necessary
            if ((pricingCondition.getCalculationType() != PricingCustomizingConstants.CalculationType.PERCENTAGE)
                    && !pricingCondition.getConditionTypeName().equals(lastPrice.getConditionTypeName())) {
                IPricingEngine pricingEngine =
                    PricingEngineFactory.getFactory().getPricingEngine(pricingItem.getUsage());
                IPricingCurrencyConversionResult convResult =
                    pricingEngine.convertTo(pricingItem.getUserExitDocument().getTryDirectCurrencyConversionFlag(),
                        pricingCondition.getConditionRate(), pricingCondition.getExchangeRate().getDate(),
                        pricingCondition.getExchangeRate().getResultingRate11Decimals(),
                        pricingItem.getUserExitDocument().getLocalCurrencyUnit(),
                        lastPrice.getExchangeRate().getDate(), lastPrice.getExchangeRate().getResultingRate5Decimals(),
                        ((ICurrencyUnit) lastPrice.getRate().getUnit()),
                        pricingItem.getExchangeRate().getExchangeRateTypeName());

                rateValue = convResult.getValue().getValue();
            }

            if (PricingCustomizingConstants.CalculationType.PERCENTAGE == pricingCondition.getCalculationType()) {

                // 5 extra decimals are needed to make rounding unnecessary: 2 are already added above.
                netPrice =
                    netPrice.add(lastPrice.getRate().getValue().multiply(rateValue.divide(
                                PricingTransactiondataConstants.HUNDRED, noOfDecimals + 3, BigDecimal.ROUND_UNNECESSARY)));
                netPrice.setScale(noOfDecimals, BigDecimal.ROUND_HALF_UP);
            }
            else if (PricingCustomizingConstants.CalculationType.PERCENTAGE_FINANCING == pricingCondition
                    .getCalculationType()) {

                // 9 extra decimals are needed to make rounding unnecessary: 2 are already added above.
                netPrice =
                    netPrice.add(lastPrice.getRate().getValue().multiply(rateValue.divide(
                                PricingTransactiondataConstants.HUNDRED, noOfDecimals + 7, BigDecimal.ROUND_UNNECESSARY)));
                netPrice.setScale(noOfDecimals, BigDecimal.ROUND_HALF_UP);
            }
            else if (PricingCustomizingConstants.CalculationType.QUANTITY_DEP == pricingCondition.getCalculationType()) {
                BigDecimal numeratorLastPrice = new BigDecimal(lastPrice.getFraction().getNumerator());
                BigDecimal denominatorLastPrice = new BigDecimal(lastPrice.getFraction().getDenominator());
                BigDecimal numeratorPrCondition = new BigDecimal(pricingCondition.getFraction().getNumerator());
                BigDecimal denominatorPrCondition = new BigDecimal(pricingCondition.getFraction().getDenominator());

                BigDecimal test1 =
                    rateValue.multiply(lastPrice.getPricingUnit().getValue()).multiply(numeratorLastPrice).multiply(denominatorPrCondition);
                BigDecimal test2 =
                    numeratorPrCondition.multiply(denominatorLastPrice).multiply(pricingCondition.getPricingUnit()
                                                                                                     .getValue());
                test1 = test1.divide(test2, noOfDecimals, BigDecimal.ROUND_HALF_UP);
                netPrice = netPrice.add(test1);
            }
            else if (PricingCustomizingConstants.CalculationType.isPhysicalUnitDependent(
                        pricingCondition.getCalculationType())) {
                if ((pricingItem.getBaseQuantity() != null)
                        && (pricingItem.getBaseQuantity().getValue().compareTo(PricingTransactiondataConstants.ZERO) != 0)) {
                    BigDecimal numeratorLastPrice = new BigDecimal(lastPrice.getFraction().getNumerator());
                    BigDecimal denominatorLastPrice = new BigDecimal(lastPrice.getFraction().getDenominator());

                    BigDecimal test1 =
                        rateValue.multiply(lastPrice.getPricingUnit().getValue()).multiply(numeratorLastPrice).multiply(pricingCondition.getConditionBase()
                                                                                                                                            .getValue());
                    BigDecimal test2 =
                        pricingItem.getBaseQuantity().getValue().multiply(denominatorLastPrice).multiply(pricingCondition.getPricingUnit()
                                                                                                                             .getValue());
                    test1 = test1.divide(test2, noOfDecimals, BigDecimal.ROUND_HALF_UP);
                    netPrice = netPrice.add(test1);
                }
            }
            else {
                pricingCondition.setInactive(PricingCustomizingConstants.InactiveFlag.INACTIVE_DUE_TO_ERROR);

                return null;
            }

            // set netPrice
            pricingItem.setObjectForUserExits(NETPRICE_FOR_ROUNDING, netPrice);

            return null;
        }
        catch (Exception ex) {
            pricingCondition.setInactive(PricingCustomizingConstants.InactiveFlag.INACTIVE_DUE_TO_ERROR);

            return null;
        }
    }
}
