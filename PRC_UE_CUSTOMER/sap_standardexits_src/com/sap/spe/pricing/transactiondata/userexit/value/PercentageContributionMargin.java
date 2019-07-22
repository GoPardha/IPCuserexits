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

import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.spe.pricing.customizing.PricingCustomizingConstants;
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ValueFormulaAdapter;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PercentageContributionMargin extends ValueFormulaAdapter {
    public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
        BigDecimal netValue = pricingItem.getNetValue().getValue();
        BigDecimal condValue =
            netValue.subtract(pricingItem.getSubtotal(PricingCustomizingConstants.ConditionSubtotal.COST).getValue());
        pricingCondition.setCalculationType(PricingCustomizingConstants.CalculationType.PERCENTAGE);
        pricingCondition.setPricingUnitValue(null);
        pricingCondition.setConditionBase(PricingTransactiondataConstants.ZERO,
            pricingItem.getUserExitDocument().getDocumentCurrencyUnit());

        try {
            if (netValue.compareTo(PricingTransactiondataConstants.ZERO) != 0) {

                // Calculate percentage with 3-decimal-precision (as does ABAP-Version by multiplying with 100.000 instead of 100)
                pricingCondition.setConditionRate(condValue.multiply(PricingTransactiondataConstants.HUNDRED).divide(netValue,
                        3, BigDecimal.ROUND_HALF_UP), "%");
            }
            else {
                pricingCondition.setConditionRate(PricingTransactiondataConstants.ZERO, "%");
            }
        }
        catch (ConversionMissingDataException ex) {
            pricingCondition.setInactive(PricingCustomizingConstants.InactiveFlag.INACTIVE_DUE_TO_ERROR);
            return null;
        }

        return condValue;
    }
}
