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


public interface tables {

    /**
     * Returns the schema, the table is in.
     */
    public schema get_schema();

    public table get_table(String table);

    public table get_table(int ordinal);

    public int get_tables_count();

    public Enumeration elements();

    /**
     * Returns a new table_object.
     */
    public table new_table(String name);

    /**
     * Creates a table on database.
     */
    public void create_table(table table);

    /**
     * Deletes a table on database.
     */
    public void drop_table(String name);

    /**
     * Copy content from table a to table b.
     */
    public void copy_table(String table_a, String table_b, column_pair[] column_pairs, sys_query_pair[] query_pairs);

    /**
     * Synchronizes the collection with the database by reading the data again from the database.
     */
    public void refresh();
}
