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


/**
 * Free goods pricing(its a free item, pricing indicator == 'B'(in R/3); example condition type R100)
 * now pricing process indicator has to be set to "X" for R100 conditiontype
 */
public class FreeItem extends RequirementAdapter {
    public boolean checkRequirement(IConditionFindingManagerUserExit item, IStep step, IAccess access) {
        if (item.getAttributeValue("PRICING_PROCESS").equals("")) {
            return false;
        }

        if (item.getAttributeValue("PRICING_PROCESS").equals("X")
                || item.getAttributeValue("PRICING_PROCESS").equals("B")) {
            return true;
        }

        return false;
    }
}
