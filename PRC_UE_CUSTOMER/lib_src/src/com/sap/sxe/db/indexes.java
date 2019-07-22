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


public interface indexes {
    public index get_index(String indexName);

    //public index get_index(int ordinal);
    public int get_indexes_count();

    public Enumeration elements();

    public void create_index(index index);

    public index new_index(String name);

    public void drop_index(String name);
}
