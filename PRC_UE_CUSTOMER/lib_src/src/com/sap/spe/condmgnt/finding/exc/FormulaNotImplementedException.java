/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.finding.exc;

import com.sap.spe.condmgnt.customizing.ConditionCustomizingEngineFactory;
import com.sap.spe.condmgnt.customizing.IConditionCustomizingEngine;
import com.sap.spe.condmgnt.finding.IConditionFindingManagerCommon;


public class FormulaNotImplementedException extends RuntimeException {
    private String message;

    public FormulaNotImplementedException(IConditionFindingManagerCommon item, String formulaType, int formulaNumber) {
        IConditionCustomizingEngine customizingEngine =
            ConditionCustomizingEngineFactory.getFactory().getCustomizingEngine(item.getUsage());
        String usageDescription = customizingEngine.getUsage(item.getUsage()).getDescription();
        if (usageDescription == null) {
            usageDescription = item.getUsage();
        }
        String applicationDescription = customizingEngine.getApplication(item.getApplication()).getDescription();
        if (applicationDescription == null) {
            applicationDescription = item.getApplication();
        }
        StringBuffer buffer = new StringBuffer("Error in ");
        buffer.append(usageDescription);
        buffer.append(" user exit for application ");
        buffer.append(applicationDescription);
        buffer.append(": no coding found for ");
        buffer.append(formulaType);
        buffer.append(" number ");
        buffer.append(formulaNumber);
        message = buffer.toString();
    }

    public String getMessage() {
        return message;
    }
}
