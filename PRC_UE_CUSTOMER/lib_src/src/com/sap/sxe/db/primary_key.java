/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

public class primary_key {
    protected String m_name;
    protected column[] m_columns;

    public primary_key(String name, column[] columns) {
        m_name = name;
        m_columns = columns;
    }

    public String get_name() {
        return m_name;
    }

    public column[] get_columns() {
        return m_columns;
    }
}
