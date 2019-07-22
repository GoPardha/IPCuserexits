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


public interface foreign_keys {

    /**
     * Returns the foreign_key with the given name.
     */
    public foreign_key get_foreign_key(String foreign_key);

    /**
     * Returns the number of foreign_keys.
     */
    public int get_foreign_keys_count();

    /**
     * Adds a foreign_key to the collection.
     */
    public void add_foreign_key(foreign_key fk);

    /**
     * Returns the foreign_keys ordered by name.
     */
    public Enumeration elements();
}
