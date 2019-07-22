/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

public interface table {
    public static final String TABLE = "TABLE";
    public static final String VIEW = "VIEW";
    public static final String SYSTEM_TABLE = "SYSTEM_TABLE";
    public static final String GLOBAL_TEMPORARY = "GLOBAL_TEMPORARY";
    public static final String LOCAL_TEMORARY = "LOCAL_TEMORARY";
    public static final String ALIAS = "ALIAS";
    public static final String SYNONYM = "SYNONYM";

    /**
       @roseuid 3742B4EE021E
     */
    public String get_name();

    /**
       @roseuid 3742B504002A
     */
    public String get_type();

    /**
     * Returns the parent container of this table.
     */
    public tables get_tables();

    /**
     * Returns the columns of the table.
     */
    public columns get_columns();

    /**
     * Returns the indexes of the table.
     */
    public indexes get_indexes();

    /**
     * Returns the foreign keys of the table.
     */
    public foreign_keys get_foreign_keys();

    /**
     * Returns the primary key of the table.
     */
    public primary_key get_primary_key();

    /**
     * Returns the primary keys of the table.
     */
    public column[] get_primary_keys();

    /**
     * Returns the ordinal position of this table
     * within the parent collection.
     */
    public int get_ordinal();

    /**
     * Set the primary keys of the table.
     */
    public void set_primary_keys(column[] columns);

    /**
     * Renames a table on database.
     */
    public void rename_table(String name);

    /**
     * Add columns on database.
     * - Only columns with <null> as possible value are allowed
     */
    public void add_columns(column[] columns);

    /**
     * Drops primary_key on database.
     */
    public void drop_primary_key();

    /**
     * Add primary_key on database.
     */
    public void add_primary_key(primary_key primary_key);

    /**
     * Drops foreign_keys on database.
     */
    public void drop_foreign_keys(foreign_key[] foreign_keys);

    /**
     * Add foreign_keys on database.
     */
    public void add_foreign_keys(foreign_key[] foreign_keys);
}
