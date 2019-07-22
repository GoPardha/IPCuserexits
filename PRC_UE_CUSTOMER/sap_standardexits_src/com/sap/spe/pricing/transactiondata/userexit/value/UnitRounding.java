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

import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.IGroupConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ValueFormulaAdapter;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UnitRounding extends ValueFormulaAdapter {
    public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
        BigDecimal condBaseValue = pricingCondition.getConditionBase().getValue();
        int scale = pricingCondition.getConditionValue().getUnit().getNumberOfDecimals();

        if (pricingItem.getUserExitDocument().getUnitToBeRoundedTo() != 0) {
            BigDecimal roundingValue = new BigDecimal(pricingItem.getUserExitDocument().getUnitToBeRoundedTo());
            return condBaseValue.multiply((PricingTransactiondataConstants.HUNDRED).divide(roundingValue, scale,
                    BigDecimal.ROUND_HALF_UP))
                                    .divide(PricingTransactiondataConstants.HUNDRED, scale, BigDecimal.ROUND_HALF_UP)
                                    .multiply(roundingValue).setScale(scale, BigDecimal.ROUND_HALF_UP);
        }

        return null;
    }

    public BigDecimal overwriteGroupConditionValue(IPricingDocumentUserExit pricingDocument,
        IGroupConditionUserExit groupCondition) {
        IPricingItemUserExit[] items = pricingDocument.getUserExitItems();
        IPricingItemUserExit prItem = null;

        if (items.length > 0) {
            prItem = items[0];
        }

        if (prItem != null) {
            BigDecimal condValue = groupCondition.getConditionValue().getValue();
            int scale = pricingDocument.getDocumentCurrencyUnit().getNumberOfDecimals();

            if (pricingDocument.getUnitToBeRoundedTo() != 0) {
                BigDecimal roundingValue = new BigDecimal(pricingDocument.getUnitToBeRoundedTo());

                return condValue.multiply((PricingTransactiondataConstants.HUNDRED).divide(roundingValue, scale,
                        BigDecimal.ROUND_HALF_UP))
                                    .divide(PricingTransactiondataConstants.HUNDRED, scale, BigDecimal.ROUND_HALF_UP)
                                    .multiply(roundingValue).setScale(scale, BigDecimal.ROUND_HALF_UP);
            }
        }

        return null;
    }
}
