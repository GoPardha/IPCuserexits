/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit.scalebase;

import java.math.BigDecimal;

import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.IGroupConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ScaleBaseFormulaAdapter;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PartialQuantity extends ScaleBaseFormulaAdapter {
    public BigDecimal overwriteScaleBase(IPricingItemUserExit pricingItem, IPricingConditionUserExit pricingCondition,
        IGroupConditionUserExit groupCondition) {
        if (pricingCondition.getConditionScale() != null) {
            BigDecimal roundedScaleBaseValue =
                pricingCondition.getConditionScale().getValue().setScale(0, BigDecimal.ROUND_FLOOR);
            return pricingCondition.getConditionScale().getValue().subtract(roundedScaleBaseValue);
        }
        return PricingTransactiondataConstants.ZERO;
    }

    public BigDecimal overwriteGroupScaleBase(IPricingDocumentUserExit pricingDocument,
        IGroupConditionUserExit groupCondition) {
        if (groupCondition.getConditionScale() != null) {
            BigDecimal roundedScaleBaseValue =
                groupCondition.getConditionScale().getValue().setScale(0, BigDecimal.ROUND_FLOOR);
            return groupCondition.getConditionScale().getValue().subtract(roundedScaleBaseValue);
        }
        return PricingTransactiondataConstants.ZERO;
    }
}
