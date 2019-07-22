/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Timestamp;

import com.sap.sxe.sys.SAPDate;
import com.sap.sxe.sys.SAPTimestamp;
import com.sap.sxe.util.J2SECompatibilityUtils;


/**
   This class realize a data tupple (String, sys_variant).
   The constructors are : -(String, sys_variant)
                          -(String, String)
                                                  -(String, int)
                                                  -(String, long)
                                                  -()
 */
public class sys_query_pair implements java.io.Serializable {

    // operator
    public static final String EQUAL = "=";
    public static final String NOT_EQUAL = "<>";
    public static final String LESS = "<";
    public static final String GREATER = ">";
    public static final String LESS_OR_EQUAL = "<=";
    public static final String GREATER_OR_EQUAL = ">=";
    public static final String LIKE = "LIKE";

    // condition
    public static final String AND = "AND";
    public static final String OR = "OR";
    public static final String NONE = " ";
    public static final String OPENING_AND_BRACKET = " ) AND ( ( ";
    public static final String CLOSING_BRACKET = " ) ";
    protected String m_colName;
    protected sys_variant m_colVal;
    protected String m_operator;
    protected String m_condition = NONE;

    public sys_query_pair(String name, sys_variant val, String operator) {
        m_colName = name;
        m_colVal = val;
        m_operator = operator;
    }

    public sys_query_pair(String name, String val, String operator) {
        this(name, new sys_variant(val), operator);
    }

    public sys_query_pair(String name, String val, String operator, String condition) {
        this(name, new sys_variant(val), operator);
        m_condition = condition;
    }

    public sys_query_pair(String name, SAPDate val, String operator) {
        this(name, new sys_variant(val), operator);
    }

    public sys_query_pair(String name, Date val, String operator) {
        this(name, new sys_variant(val), operator);
    }

    public sys_query_pair(String name, SAPTimestamp val, String operator) {
        this(name, new sys_variant(val), operator);
    }

    public sys_query_pair(String name, Timestamp val, String operator) {
        this(name, new sys_variant(val), operator);
    }

    public sys_query_pair(String name, int val, String operator) {
        this(name, new sys_variant(val), operator);
    }

    public sys_query_pair(String name, long val, String operator) {
        this(name, new sys_variant(val), operator);
    }

    public sys_query_pair(String name, double val, String operator) {
        this(name, new sys_variant(val), operator);
    }

    public sys_query_pair(String name, int[] val, String operator) {
        this(name, new sys_variant(val), operator);
    }

    public sys_query_pair(String name, Integer val, String operator) {
        this(name, new sys_variant(val), operator);
    }

    public sys_query_pair(String name, String[] val, String operator) {
        this(name, new sys_variant(val), operator);
    }

    public sys_query_pair(String name, boolean val, String operator) {
        this(name, val
            ? "X"
            : " ", operator);
    }

    public sys_query_pair(String name, BigDecimal val, String operator) {
        this(name, (val != null)
            ? J2SECompatibilityUtils.bigDecimalToString(val)
            : null, operator);
    }

    public sys_query_pair(String name, char val, String operator) {
        this(name, String.valueOf(val), operator);
    }

    public sys_query_pair(String name, sys_variant val) {
        this(name, val, EQUAL);
    }

    public sys_query_pair(String name, String val) {
        this(name, val, EQUAL);
    }

    public sys_query_pair(String name, SAPDate val) {
        this(name, val, EQUAL);
    }

    public sys_query_pair(String name, SAPTimestamp val) {
        this(name, val, EQUAL);
    }

    public sys_query_pair(String name, int val) {
        this(name, val, EQUAL);
    }

    //tm 7-Aug-2000 operator is missing
    //public sys_query_pair(String name, Integer val) {
    //m_colName = name;
    //sys_setValue(val);
    //}
    public sys_query_pair(String name, Integer val) {
        this(name, val, EQUAL);
    }

    public sys_query_pair(String name, long val) {
        this(name, val, EQUAL);
    }

    //tm 7-Aug-2000 operator is missing
    //public sys_query_pair(String name, double val) {
    //m_colName = name;
    //sys_setValue(val);
    //}
    public sys_query_pair(String name, double val) {
        this(name, val, EQUAL);
    }

    public sys_query_pair(String name, int[] val) {
        this(name, val, EQUAL);
    }

    public sys_query_pair(String name, String[] val) {
        this(name, val, EQUAL);
    }

    public sys_query_pair(String name, boolean val) {
        this(name, val, EQUAL);
    }

    public sys_query_pair(String name, BigDecimal val) {
        this(name, val, EQUAL);
    }

    public sys_query_pair(String name, char val) {
        this(name, val, EQUAL);
    }

    public sys_query_pair() {
        this((String) null, (String) null, EQUAL);
    }

    public sys_query_pair(String bracket) {
    	this(null, null, null, bracket);
    }
    
    public String sys_getName() {
        return m_colName;
    }

    public String sys_getCondition() {
        return m_condition;
    }

    public sys_variant sys_getValue() {
        return m_colVal;
    }

    public String sys_getOperator() {
        return m_operator;
    }

    public void sys_setName(String name) {
        m_colName = name;
    }

	//20060925-tl: two sys_query_pairs to the same column must be joined 
    //             with a logical AND instead of an OR if condition is set to AND
    public void sys_setCondition(String condition) {
        m_condition = condition;
    }

    public void sys_setValue(String val) {
        m_colVal = new sys_variant(val);
    }

    public void sys_setValue(int val) {
        m_colVal = new sys_variant(val);
    }

    public void sys_setValue(long val) {
        m_colVal = new sys_variant(val);
    }

    public void sys_setValue(int[] val) {
        m_colVal = new sys_variant(val);
    }

    public void sys_setValue(double val) {
        m_colVal = new sys_variant(val);
    }

    public void sys_setValue(String[] val) {
        m_colVal = new sys_variant(val);
    }

    public void sys_setValue(Integer val) {
        m_colVal = new sys_variant(val);
    }

    public void sys_setValue(sys_variant val) {
        m_colVal = val;
    }

    public void sys_setOperator(String operator) {
        m_operator = operator;
    }
}
