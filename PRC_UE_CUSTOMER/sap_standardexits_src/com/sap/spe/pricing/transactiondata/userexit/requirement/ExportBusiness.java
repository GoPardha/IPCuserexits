/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit.requirement;

import com.sap.spe.condmgnt.customizing.IAccess;
import com.sap.spe.condmgnt.customizing.IStep;
import com.sap.spe.condmgnt.finding.userexit.IConditionFindingManagerUserExit;
import com.sap.spe.condmgnt.finding.userexit.RequirementAdapter;
import com.sap.spe.pricing.customizing.IPricingConditionType;
import com.sap.spe.pricing.customizing.PricingCustomizingConstants;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExportBusiness extends RequirementAdapter {
    public boolean checkRequirement(IConditionFindingManagerUserExit item, IStep step, IAccess access) {
        if (item.getAttributeValue("TAX_DEPART_CTY").equals("")) {
            return false;
        }

        if (item.getAttributeValue("TAX_DEST_CTY").equals("")) {
            return false;
        }

        if (item.getAttributeValue("TAX_DEPART_CTY").equals(item.getAttributeValue("TAX_DEST_CTY"))) {
            return false;
        }

        if ((step.getConditionType() != null)
                && (((IPricingConditionType) (step.getConditionType())).getConditionClass() == PricingCustomizingConstants.ConditionClass.TAX)) {
            if (item.getAttributeValue("DEST_CTY_IS_EU_COUNTRY").equals("X")
                    && item.getAttributeValue("DEPART_CTY_IS_EU_COUNTRY").equals("X")
                    && item.getAttributeValue("VAT_REG_NO").equals("")) {
                return false;
            }
        }
        return true;
    }
}
