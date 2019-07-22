/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.masterdata;

import java.io.Serializable;

import java.util.Map;

import com.sap.spe.condmgnt.customizing.IConditionTable;
import com.sap.spe.condmgnt.customizing.IConditionType;
import com.sap.sxe.sys.SAPTimestamp;


/**
 * Interface for the conditionRecord-object. This object is a part of the result of
 * the search performed by the condition technique.
 */
public interface IConditionRecord extends Serializable {
    public String getId();

    /**
     * @return my conditiontype object.
     */
    public IConditionType getConditionType();

    /**
     * @return the start timestamp of my validity.
     */
    public SAPTimestamp getValidityStart();

    /**
     * @return the end timestamp of my validity.
     */
    public SAPTimestamp getValidityEnd();

    public String getObjectId();

    public IConditionTable getConditionTable();

    public Map getApplicationAndUsageData();

    public String getVariableKeyValue(String fieldName);

    public String getVariableDataValue(String fieldName);

    public String getUsageFieldValue(String fieldName);

    public String[] getVariableKeyValues();

    public String[] getVariableDataValues();

    public String[] getVariableKeyNames();

    public String[] getVariableDataNames();

    public char getReleaseStatus();

    public String getMaintenanceStatus();

    public ISupplementaryConditionRecord[] getSupplementaryConditionRecords();

    public String getGroupId();

    public IScale getScale();

    /**
     * Return the count of the scale dimensions for this condition record.
     */
    public int getScaleDimension();

    /** return this condition record's application */
    public String getApplication();

    /** return this condition record's usage */
    public String getUsage();
}
