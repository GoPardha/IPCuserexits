/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

public class foreign_key {
    protected String m_name;
    protected String m_pk_table;
    protected String m_fk_table;
    protected column_pair[] m_column_pairs;  //columna => fk_column; columnb => pk_column;

    public foreign_key(String name, String pk_table, String fk_table, column_pair[] column_pairs) {
        m_name = name;
        m_pk_table = pk_table;
        m_fk_table = fk_table;
        m_column_pairs = column_pairs;
    }

    public String get_name() {
        return m_name;
    }

    public String get_pk_table() {
        return m_pk_table;
    }

    public String get_fk_table() {
        return m_fk_table;
    }

    public column_pair[] get_column_pairs() {
        return m_column_pairs;
    }
}
