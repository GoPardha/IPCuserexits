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


public interface columns {

    /**
     * Adds a column.
     */

    //public void add_column(column col);

    /**
     * Returns the column with the given name.
     */
    public column get_column(String column);

    /**
     * Returns the column at the given ordinal position.
     */
    public column get_column(int ordinal);

    /**
     * Returns the number of columns.
     */
    public int get_columns_count();

    /**
     * Creates a new column-object.
     * @deprecated This factory method had the side effect to add the
     * column to the columns collection. Now the name and the ordinal
     * position need to be set prior to adding the column to the columns collection.
     */
    public column new_column(String name);

    /**
     * Creates a new empty column object.
     */
    public column new_column();

    /**
     * Adds a column to the collection.
     * The name and the ordinal position need to be initialized prior
     * to calling this method.
     */
    public void add_column(column col);

    /**
     * Returns the columns ordered by name.
     */
    public Enumeration elements();

    /**
     * Returns the columns in their physical order.
     */
    public Enumeration columns_by_ordinal();
}
