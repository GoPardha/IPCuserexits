/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.masterdata;

import java.util.List;
import java.util.Vector;

import com.sap.spe.base.cache.Engine;
import com.sap.spe.condmgnt.customizing.IAccess;
import com.sap.spe.condmgnt.customizing.IConditionTable;
import com.sap.spe.condmgnt.customizing.IConditionType;
import com.sap.spe.condmgnt.customizing.ITableField;
import com.sap.spe.condmgnt.exception.ConditionInconsistentDBException;
import com.sap.sxe.sys.SAPDate;
import com.sap.sxe.sys.SAPTimestamp;


public interface IConditionMasterDataEngine extends Engine {
    public IConditionRecord[] getConditionRecords(IConditionType conditionType, IAccess access,
        ITableAttributeBinding[] attributeBindings, SAPTimestamp timestamp, char[] releaseStatus, IMasterdataTrace trace);

    public IConditionRecord[] getConditionRecords(IConditionType conditionType, IAccess access,
        ITableAttributeBinding[] attributeBindings, SAPTimestamp timestamp, char[] releaseStatus, boolean hierarchyAccess, IMasterdataTrace trace);

    public IConditionRecord[] getConditionRecords(IConditionType conditionType, IAccess access,
        ITableAttributeBinding[] attributeBindings, SAPTimestamp timestamp, IMasterdataTrace trace);
    
    public IConditionRecord[] getConditionRecords(IConditionTable conditionTable, List conditionRecordId);

    public ITableAttributeBinding createTableAttributeBinding(ITableField tableAttributeClass, String[] values);

    public IMasterdataTrace getMasterdataTrace();

    public IConditionRecord[] selectConditionRecords(IAttributeValuesContainer attributeValuesContainer,
        SAPDate validityStartDate, SAPDate validityEndDate, String conditionGroupName, int recordNumber)
        throws ConditionInconsistentDBException;

    public IConditionRecord[] getNextConditionRecords(int recordNumber);

    public int getNumberOfConditionRecordSelected();

    public IConditionRecord[] getConditionRecordsFromRefGuid(String application, String refGuid, String refType,
        int recordNumber);

    public IConditionRecord[] selectConditionRecords(String fileName, String name, String application,
        Vector attributesInclude, Vector attributesExclude, Vector ranges, String groupName, SAPDate validityStartDate,
        SAPDate validityEndDate, int recordNumber)
        throws ConditionInconsistentDBException;
}
