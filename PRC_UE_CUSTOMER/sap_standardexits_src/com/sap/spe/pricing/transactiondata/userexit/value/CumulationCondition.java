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
import com.sap.spe.conversion.ICurrencyValue;
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
public class CumulationCondition extends ValueFormulaAdapter {
    public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
        if (pricingCondition.getStructureConditionFlag() != PricingCustomizingConstants.StructureIndicator.CONDITION_FOR_CUMULATION) {
            return null;
        }

        ICurrencyValue netValue = pricingItem.getNetValue();
        pricingCondition.setConditionBase(netValue.getValue(), netValue.getUnit());

        BigDecimal accumulatedNetValue = PricingTransactiondataConstants.ZERO;

        if (!pricingItem.isStatistical()) {
            accumulatedNetValue = pricingCondition.getConditionBase().getValue();
        }

        IPricingItemUserExit[] prItems = pricingItem.getSubPricingItemsUserExitRecursive();
        for (int i = 0; i < prItems.length; i++) {
            if (!prItems[i].isStatistical()) {
                IPricingConditionUserExit[] conditionsForCumulation = prItems[i].getConditionsForCumulation();
                for (int j = 0; j < conditionsForCumulation.length; j++) {
                    if ((pricingCondition.getStepNumber() == conditionsForCumulation[j].getStepNumber())
                            && (pricingCondition.getCounter() == conditionsForCumulation[j].getCounter())) {
                        accumulatedNetValue =
                            accumulatedNetValue.add(conditionsForCumulation[j].getConditionBase().getValue());
                        break;
                    }
                }
            }
        }

        pricingCondition.setConditionRate(accumulatedNetValue, (ICurrencyUnit) netValue.getUnit());
        pricingCondition.setStatistical(true);

        return accumulatedNetValue;
    }
}
