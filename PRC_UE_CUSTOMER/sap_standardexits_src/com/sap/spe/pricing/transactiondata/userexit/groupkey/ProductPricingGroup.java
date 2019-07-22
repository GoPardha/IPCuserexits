/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit.groupkey;

import com.sap.spe.pricing.transactiondata.userexit.GroupKeyFormulaAdapter;
import com.sap.spe.pricing.transactiondata.userexit.IGroupConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProductPricingGroup extends GroupKeyFormulaAdapter {
    public String setGroupKey(IPricingDocumentUserExit pricingDocument, IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition, IGroupConditionUserExit groupCondition) {
        String varkey = "003";
        String matgr = pricingItem.getAttributeValue("PRODUCT_PRICING_GROUP");
        if (matgr != null) {
            return varkey + matgr;
        }
        return varkey;
    }
}
