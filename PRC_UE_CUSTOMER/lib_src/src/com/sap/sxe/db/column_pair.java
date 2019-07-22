/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

public class column_pair {
    protected column m_columna;
    protected column m_columnb;

    public column_pair(column columna, column columnb) {
        m_columna = columna;
        m_columnb = columnb;
    }

    public column get_columna() {
        return m_columna;
    }

    public column get_columnb() {
        return m_columnb;
    }
}
