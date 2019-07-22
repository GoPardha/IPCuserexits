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

import com.sap.spe.conversion.ConversionEngineFactory;
import com.sap.spe.conversion.IConversionEngine;
import com.sap.spe.conversion.ICurrencyUnit;
import com.sap.spe.conversion.IPhysicalUnit;
import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.spe.pricing.customizing.PricingCustomizingConstants;
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
public class RoundingNote80183Formula917 extends BaseFormulaAdapter implements RoundingFormula {
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
            pricingCondition.setConditionRateValue(pricingItem.getNetPrice().getValue());
        }
        else {
            int scale = pricingCondition.getConditionRate().getUnit().getNumberOfDecimals();
            pricingCondition.setConditionRate(netPrice.setScale(scale, BigDecimal.ROUND_HALF_UP),
                (ICurrencyUnit) pricingCondition.getConditionRate().getUnit());
            IConversionEngine conversionEngine = ConversionEngineFactory.getFactory().getConversionEngine();

            try {
                pricingCondition.setExchangeRate(conversionEngine.getIdentityExchangeRate("M",
                        pricingCondition.getConditionRate().getUnitName(), pricingCondition.getExchangeRate().getDate()));
            }
            catch (ConversionMissingDataException ex) {
                pricingCondition.setInactive(PricingCustomizingConstants.InactiveFlag.INACTIVE_DUE_TO_ERROR);
            }
        }

        return lastPrice.getQuantity().getValue();
    }
}
