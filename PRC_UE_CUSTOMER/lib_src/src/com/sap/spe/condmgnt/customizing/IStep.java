/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.customizing;

import java.io.Serializable;


public interface IStep extends Serializable {
    public int getStepNumber();

    public int getCounter();

    public IConditionType getConditionType();

    public int getRequirementNumber();

    public IUserExitFormula getRequirement();
    
    public IProcedure getProcedure();

    public String getDescription();
}
