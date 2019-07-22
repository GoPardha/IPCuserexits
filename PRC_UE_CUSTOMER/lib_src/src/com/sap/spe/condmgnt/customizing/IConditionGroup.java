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
 * ConditionGroup associates a (text string) name with a set of accessible
 * tables and types, and provides methods to determine if specific tables or types
 * are permitted by the condition group.
 **/
public interface IConditionGroup extends Serializable {

    /**
     * Get all the condition types that will be allowed with the given table.
     */
    public IConditionType[] getAllowedTypes(IConditionTable table);

    /**
     * Get all the condition types that will be allowed in this group.
     */
    public IConditionType[] getAllowedTypes();

    /**
     * Get all the condition tables that will be allowed in this group.
     */
    public IConditionTable[] getAllowedTables();

    /**
     * Get the set of attributes that are relevant to this group.
     */
    public ITableField[] getRelevantAttributes();

    /**
     * Get all allowances in this group
     */
    public IConditionGroupItem[] getConditionGroupItems();

    /**
     * Return Group Name
     */
    public String getName();
}
