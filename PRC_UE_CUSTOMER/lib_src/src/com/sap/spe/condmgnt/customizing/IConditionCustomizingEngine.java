/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.customizing;

import com.sap.spe.base.cache.Engine;
import com.sap.spe.base.util.IF4Help;
import com.sap.spe.condmgnt.exception.ConditionInconsistentDBException;
import com.sap.spe.condmgnt.exception.FormulaIsMissingException;

public interface IConditionCustomizingEngine extends Engine {
    public String getUsage();

    // procedure
    public IProcedure getProcedure(String application, String name)
        throws ConditionInconsistentDBException;

    public String[] getAvailableProcedureNames(String application);

    // condition type
    public IConditionType getConditionType(String application, String conditionTypeName)
        throws ConditionInconsistentDBException;

    public IConditionType[] getAllConditionTypes(String application)
        throws ConditionInconsistentDBException;

    // access sequence
    public IAccessSequence getAccessSequence(String application, String accessSequence)
        throws ConditionInconsistentDBException;

    // condition table
    public IConditionTable getConditionTableByName(String application, String tableNamePart);

    public IConditionTable getConditionTableByVarnumh(String application, String varnumh);

    // field catalogue
    public IApplicationField getApplicationField(String application, String fieldName);

    public IApplicationField[] getApplicationFields(String application);
    
    public IUsageField getUsageField(String usage, String fieldname);

    public IUsageField[] getUsageFields(String usage);

    public IConditionTechniqueField getConditionTechniqueField( String fieldname);

    public IConditionTechniqueField[] getConditionTechniqueFields();

    // application
    public IApplication getApplication(String application);

    public IApplication[] getAllApplications();

    // usage
    public IUsage getUsage(String usage);

    public IUsage[] getAllUsages();

    // task
    public ITask[] getAllTasks();

    public ITask[] getAllTasks(String usage);

    public ITask getTask(String usageName, String applicationName);

    // condition group
    public IConditionGroup[] getConditionGroups()
        throws ConditionInconsistentDBException;

    public IConditionGroup getConditionGroup(String conditionGroupName)
        throws ConditionInconsistentDBException;

    public IConditionType[] getAllowedTypes(String groupName)
        throws ConditionInconsistentDBException;

    public ITableField[] getRelevantAttributes(String groupName)
        throws ConditionInconsistentDBException;

    // condition maintenance groups
    public IConditionMaintenanceGroup getConditionMaintenanceGroup (String contextGroupName) 
        throws ConditionInconsistentDBException;
    
    public IConditionMaintenanceGroup[] getConditionMaintenanceGroups()
    throws ConditionInconsistentDBException;

    // user exits
    public IUserExitFormulaContainer getUserExitFormulas(String application, String typeName) throws FormulaIsMissingException;    

    public IUserExitType[] getUserExitTypes();

    public IF4Help getF4HelpUserExits(String application, String typeName) throws FormulaIsMissingException;

    public IF4Help getF4HelpUserExits(String typeName) throws FormulaIsMissingException;
    
    public String[] getGroupname(String contextName) throws ConditionInconsistentDBException;
    
}
