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
import java.util.Vector;


public class type_infos {
    private Vector m_vector;

    public type_infos() {
        m_vector = new Vector();
    }

    public type_infos(int size) {
        m_vector = new Vector(size);
    }

    public type_info get_type_info(String datatype) {
        Enumeration en = m_vector.elements();
        while (en.hasMoreElements()) {
            type_info ti = (type_info) en.nextElement();
            if (ti.get_type_name().equalsIgnoreCase(datatype)) {
                return ti;
            }
        }
        return null;
    }

    public type_info get_type_info(int datatype) {
        Enumeration en = m_vector.elements();
        while (en.hasMoreElements()) {
            type_info ti = (type_info) en.nextElement();

            //if (ti.get_db_data_type() == datatype) {
            if (ti.get_data_type() == datatype) {
                return ti;
            }
        }
        return null;
    }

    public void push(type_info ti) {
        m_vector.addElement(ti);
    }

    public Object elementAt(int ordinal) {
        return m_vector.elementAt(ordinal);
    }

    public int length() {
        return m_vector.size();
    }

    public Enumeration elements() {
        return m_vector.elements();
    }
}
