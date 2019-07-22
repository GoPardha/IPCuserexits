/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;


/**
 * Represents a user namespace in the database.
 */
public interface schema {

    /**
     * Returns the parent collection of the schema.
     */
    public schemas get_schemas();

    public String get_name();

    public tables get_tables();

    public int get_ordinal();
}
