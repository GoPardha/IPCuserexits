/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

public interface index {
    public String get_name();

    public String[] get_columns();

    public boolean is_unique();

    public void set_name(String name);

    public void set_columns(String[] columns);

    public void set_is_unique(boolean unique);
}
