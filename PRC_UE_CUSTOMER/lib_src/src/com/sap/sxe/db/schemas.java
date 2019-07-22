/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

import java.util.Enumeration;


public interface schemas {

    /**
     * Returns the database the schemas are in.
     */
    public database get_database();

    public schema get_schema(String schemaName);

    public schema get_schema(int ordinal);

    public int get_schemas_count();

    public Enumeration elements();
}
