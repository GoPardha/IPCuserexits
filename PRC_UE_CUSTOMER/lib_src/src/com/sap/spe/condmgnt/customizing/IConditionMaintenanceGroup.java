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
public interface IConditionMaintenanceGroup extends Serializable {


    /**
     * Return Group Name
     */
    public String getName();
}
