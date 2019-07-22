/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata;

import java.math.BigDecimal;
import java.util.List;


public interface IExclusionCondition {
    public String getGroupName();

    public String getConditionTypeName();

    public char getInactive();

    public int getStepNo();
    
    public void setValue(BigDecimal value);
    
    public BigDecimal getValue();
    
    public void setCounter(int counter);

    public int getCounter();
    
    public BigDecimal getOriginalCondValue();
    
    public int getCount();
    
    public List getExclusionConditionsWithCounter();
}
