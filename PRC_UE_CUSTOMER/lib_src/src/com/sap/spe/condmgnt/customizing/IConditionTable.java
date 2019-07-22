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


/**
 * Interface of the conditionTable-object.
 */
public interface IConditionTable extends Serializable {
    public String getApplication();

    public String getUsage();

    /**
     * @return my name.
     */
    public String getName();

    /**
     * @return my description.
     */
    public String getDescription();

    public String getNameForAccess();

    public String getNameForSupplement();

    public String getNamePart();

    public String getNamePrefix(char type);

    public ITableField[] getVariableKeys();
    
    public ITableField[] getVariableData();

    public ITableField getVariableKeyField(String fieldName);

    public ITableField getVariableDataField(String fieldName);

    public IUsageField[] getUsageFields();
    
    public String[] getVariableHeaderKeyFieldNames();    
    
    public boolean hasValidityPeriod();

}
