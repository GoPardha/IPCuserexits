/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/
package com.sap.sxe.db;

import java.io.InputStream;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Timestamp;

import java.text.*;

import java.util.Calendar;
import java.util.Locale;

import com.sap.sxe.sys.SAPDate;
import com.sap.sxe.sys.SAPTimestamp;
import com.sap.sxe.sys.exc.exc_not_reached;
import com.sap.vmc.logging.Category;
import com.sap.vmc.logging.LocalizableCategory;
import com.sap.vmc.logging.Location;
import com.sap.vmc.logging.Severity;


public class sys_variant implements java.io.Serializable {
	
    public static final LocalizableCategory CATEGORY = LocalizableCategory.getCategory(Category.APPLICATIONS, "/AP/Base/Database");    
    public static final String MESSAGE_CLASS = "IPC_DB";
	private static Location LOCATION = Location.getLocation(sys_variant.class);
	
    public static char[][] hex_chars =
        {
            { '0', '0' },
            { '0', '1' },
            { '0', '2' },
            { '0', '3' },
            { '0', '4' },
            { '0', '5' },
            { '0', '6' },
            { '0', '7' },  //0
            { '0', '8' },
            { '0', '9' },
            { '0', 'A' },
            { '0', 'B' },
            { '0', 'C' },
            { '0', 'D' },
            { '0', 'E' },
            { '0', 'F' },
            { '1', '0' },
            { '1', '1' },
            { '1', '2' },
            { '1', '3' },
            { '1', '4' },
            { '1', '5' },
            { '1', '6' },
            { '1', '7' },  //1
            { '1', '8' },
            { '1', '9' },
            { '1', 'A' },
            { '1', 'B' },
            { '1', 'C' },
            { '1', 'D' },
            { '1', 'E' },
            { '1', 'F' },
            { '2', '0' },
            { '2', '1' },
            { '2', '2' },
            { '2', '3' },
            { '2', '4' },
            { '2', '5' },
            { '2', '6' },
            { '2', '7' },  //2
            { '2', '8' },
            { '2', '9' },
            { '2', 'A' },
            { '2', 'B' },
            { '2', 'C' },
            { '2', 'D' },
            { '2', 'E' },
            { '2', 'F' },
            { '3', '0' },
            { '3', '1' },
            { '3', '2' },
            { '3', '3' },
            { '3', '4' },
            { '3', '5' },
            { '3', '6' },
            { '3', '7' },  //3
            { '3', '8' },
            { '3', '9' },
            { '3', 'A' },
            { '3', 'B' },
            { '3', 'C' },
            { '3', 'D' },
            { '3', 'E' },
            { '3', 'F' },
            { '4', '0' },
            { '4', '1' },
            { '4', '2' },
            { '4', '3' },
            { '4', '4' },
            { '4', '5' },
            { '4', '6' },
            { '4', '7' },  //4
            { '4', '8' },
            { '4', '9' },
            { '4', 'A' },
            { '4', 'B' },
            { '4', 'C' },
            { '4', 'D' },
            { '4', 'E' },
            { '4', 'F' },
            { '5', '0' },
            { '5', '1' },
            { '5', '2' },
            { '5', '3' },
            { '5', '4' },
            { '5', '5' },
            { '5', '6' },
            { '5', '7' },  //5
            { '5', '8' },
            { '5', '9' },
            { '5', 'A' },
            { '5', 'B' },
            { '5', 'C' },
            { '5', 'D' },
            { '5', 'E' },
            { '5', 'F' },
            { '6', '0' },
            { '6', '1' },
            { '6', '2' },
            { '6', '3' },
            { '6', '4' },
            { '6', '5' },
            { '6', '6' },
            { '6', '7' },  //6
            { '6', '8' },
            { '6', '9' },
            { '6', 'A' },
            { '6', 'B' },
            { '6', 'C' },
            { '6', 'D' },
            { '6', 'E' },
            { '6', 'F' },
            { '7', '0' },
            { '7', '1' },
            { '7', '2' },
            { '7', '3' },
            { '7', '4' },
            { '7', '5' },
            { '7', '6' },
            { '7', '7' },  //7
            { '7', '8' },
            { '7', '9' },
            { '7', 'A' },
            { '7', 'B' },
            { '7', 'C' },
            { '7', 'D' },
            { '7', 'E' },
            { '7', 'F' },
            { '8', '0' },
            { '8', '1' },
            { '8', '2' },
            { '8', '3' },
            { '8', '4' },
            { '8', '5' },
            { '8', '6' },
            { '8', '7' },  //8
            { '8', '8' },
            { '8', '9' },
            { '8', 'A' },
            { '8', 'B' },
            { '8', 'C' },
            { '8', 'D' },
            { '8', 'E' },
            { '8', 'F' },
            { '9', '0' },
            { '9', '1' },
            { '9', '2' },
            { '9', '3' },
            { '9', '4' },
            { '9', '5' },
            { '9', '6' },
            { '9', '7' },  //9
            { '9', '8' },
            { '9', '9' },
            { '9', 'A' },
            { '9', 'B' },
            { '9', 'C' },
            { '9', 'D' },
            { '9', 'E' },
            { '9', 'F' },
            { 'A', '0' },
            { 'A', '1' },
            { 'A', '2' },
            { 'A', '3' },
            { 'A', '4' },
            { 'A', '5' },
            { 'A', '6' },
            { 'A', '7' },  //A
            { 'A', '8' },
            { 'A', '9' },
            { 'A', 'A' },
            { 'A', 'B' },
            { 'A', 'C' },
            { 'A', 'D' },
            { 'A', 'E' },
            { 'A', 'F' },
            { 'B', '0' },
            { 'B', '1' },
            { 'B', '2' },
            { 'B', '3' },
            { 'B', '4' },
            { 'B', '5' },
            { 'B', '6' },
            { 'B', '7' },  //B
            { 'B', '8' },
            { 'B', '9' },
            { 'B', 'A' },
            { 'B', 'B' },
            { 'B', 'C' },
            { 'B', 'D' },
            { 'B', 'E' },
            { 'B', 'F' },
            { 'C', '0' },
            { 'C', '1' },
            { 'C', '2' },
            { 'C', '3' },
            { 'C', '4' },
            { 'C', '5' },
            { 'C', '6' },
            { 'C', '7' },  //C
            { 'C', '8' },
            { 'C', '9' },
            { 'C', 'A' },
            { 'C', 'B' },
            { 'C', 'C' },
            { 'C', 'D' },
            { 'C', 'E' },
            { 'C', 'F' },
            { 'D', '0' },
            { 'D', '1' },
            { 'D', '2' },
            { 'D', '3' },
            { 'D', '4' },
            { 'D', '5' },
            { 'D', '6' },
            { 'D', '7' },  //D
            { 'D', '8' },
            { 'D', '9' },
            { 'D', 'A' },
            { 'D', 'B' },
            { 'D', 'C' },
            { 'D', 'D' },
            { 'D', 'E' },
            { 'D', 'F' },
            { 'E', '0' },
            { 'E', '1' },
            { 'E', '2' },
            { 'E', '3' },
            { 'E', '4' },
            { 'E', '5' },
            { 'E', '6' },
            { 'E', '7' },  //E
            { 'E', '8' },
            { 'E', '9' },
            { 'E', 'A' },
            { 'E', 'B' },
            { 'E', 'C' },
            { 'E', 'D' },
            { 'E', 'E' },
            { 'E', 'F' },
            { 'F', '0' },
            { 'F', '1' },
            { 'F', '2' },
            { 'F', '3' },
            { 'F', '4' },
            { 'F', '5' },
            { 'F', '6' },
            { 'F', '7' },  //F
            { 'F', '8' },
            { 'F', '9' },
            { 'F', 'A' },
            { 'F', 'B' },
            { 'F', 'C' },
            { 'F', 'D' },
            { 'F', 'E' },
            { 'F', 'F' }
        };
    public final static int DTNOTYPE = 0;  // sh20010626;
    public final static int DTINTEGER = 1;  // sh20010626;
    public final static int DTSTRING = 2;  // sh20010626;
    public final static int DTINTEGERSEQ = 3;  // sh20010626;
    public final static int DTSTRINGSEQ = 4;  // sh20010626;
    public final static int DTDOUBLE = 5;  // sh20010626;
    public final static int DTDOUBLESEQ = 6;  // sh20010626;
    public final static int DTSAPDATE = 7;  // sh20010626;
    public final static int DTSAPTIMESTAMP = 8;  // sh20010626;
    public final static int DTTIMESTAMP = 9;  // sh20010626;
    public final static int DTBOOLEAN = 10;  // sh20010626;
    public final static int DTDATE = 11;  // sh20010626;
    public final static int DTBIGDECIMAL = 12;  // sh20010626;
    public final static int DTUNISTRING = 13;  // sh20010626;
    public final static int DTBINARY = 14;  // sh20010626;
    protected Object m_thing = null;
    protected int m_type = DTNOTYPE;
    protected int m_length = 0;  // sh20010626;

    public sys_variant(int type, Object thing) {
        m_thing = thing;
        m_type = type;
    }

    public sys_variant(Object thing) {
        m_thing = thing;
    }

    public sys_variant() {
    }

    public sys_variant(String string) {
        m_thing = string;
        m_type = DTSTRING;
    }

    public sys_variant(int i) {
        this(new Integer(i));
    }

    public sys_variant(Integer i) {
        m_thing = i;
        m_type = DTINTEGER;
    }

    public sys_variant(int[] is) {
        m_thing = is;
        m_type = DTINTEGERSEQ;
    }

    public sys_variant(String[] is) {
        m_thing = is;
        m_type = DTSTRINGSEQ;
    }

    public sys_variant(InputStream is, int length) {
        m_thing = is;
        m_type = DTBINARY;
        m_length = length;
    }

    public sys_variant(double d) {
        this(new Double(d));
    }

    public sys_variant(Double d) {
        m_thing = d;
        m_type = DTDOUBLE;
    }

    public sys_variant(double[] double_seq) {
        m_thing = double_seq;
        m_type = DTDOUBLESEQ;
    }

    public sys_variant(SAPDate date) {
        m_thing = date;
        m_type = DTSAPDATE;
    }

    public sys_variant(SAPTimestamp timestamp) {
        m_thing = timestamp;
        m_type = DTSAPTIMESTAMP;
    }

    public sys_variant(Date date) {
        m_thing = date;
        m_type = DTDATE;
    }

    public sys_variant(Timestamp timestamp) {
        m_thing = timestamp;
        m_type = DTTIMESTAMP;
    }

    public sys_variant(boolean b) {
        m_thing = b
            ? Boolean.TRUE
            : Boolean.FALSE;
        m_type = DTBOOLEAN;
    }

    public sys_variant(Boolean b) {
        m_thing = b;
        m_type = DTBOOLEAN;
    }

    public sys_variant(BigDecimal b) {
        m_thing = b;
        m_type = DTBIGDECIMAL;
    }

    public final int getvt() {
        return m_type;
    }

    public final boolean isInt() {
        return (m_type == DTINTEGER)
        ? true
        : false;
    }

    public final boolean isString() {
        return (m_type == DTSTRING)
        ? true
        : false;
    }

    public final boolean isIntSeq() {
        return (m_type == DTINTEGERSEQ)
        ? true
        : false;
    }

    public final boolean isStringSeq() {
        return (m_type == DTSTRINGSEQ)
        ? true
        : false;
    }

    public final boolean isDoubleSeq() {
        return (m_type == DTDOUBLESEQ)
        ? true
        : false;
    }

    public final boolean isSAPDate() {
        return (m_type == DTSAPDATE)
        ? true
        : false;
    }

    public final boolean isBoolean() {
        return (m_type == DTBOOLEAN)
        ? true
        : false;
    }

    public final boolean isBigDecimal() {
        return (m_type == DTBIGDECIMAL)
        ? true
        : false;
    }

    public int getLength() {
        return m_length;
    }

    public final Integer getInt() {
        if (getvt() == DTINTEGER) {
            return (Integer) m_thing;
        }
        return null;
    }

    public final Double getDouble() {
        if (getvt() == DTDOUBLE) {
            return (Double) m_thing;
        }
        return null;
    }

    public final String getString() {
        if (getvt() == DTSTRING) {
            return (String) m_thing;
        }
        return null;
    }

    public final Boolean getBoolean() {
        if (getvt() == DTBOOLEAN) {
            return (Boolean) m_thing;
        }
        return null;
    }

    public final BigDecimal getBigDecimal() {
        if (getvt() == DTBIGDECIMAL) {
            return (BigDecimal) m_thing;
        }
        return null;
    }

    public final int[] getIntSeq() {
        if (getvt() == DTINTEGERSEQ) {
            return (int[]) m_thing;
        }
        return null;
    }

    public final String[] getStringSeq() {
        if (getvt() == DTSTRINGSEQ) {
            return (String[]) m_thing;
        }
        return null;
    }

    public final double[] getDoubleSeq() {
        if (getvt() == DTDOUBLESEQ) {
            return (double[]) m_thing;
        }
        return null;
    }

    /**
     * Returns the value as a quoted String 'value'.
     */
    public String getSQLString() {
        switch (m_type) {

            case DTSTRING:
                return replaceQuote("'" + (String) m_thing + "'");

            case DTUNISTRING:
                return toUnicode(replaceQuote("'" + (String) m_thing + "'"));

            case DTSTRINGSEQ: {
                String[] string_array = (String[]) m_thing;
                String values = toUnicode(replaceQuote("'" + string_array[0] + "'"));
                for (int j = 1; j < string_array.length; j++) {
                    values = values + "," + toUnicode(replaceQuote("'" + string_array[j] + "'"));
                }
                return values;
            }

            // date format: {d 'yyyy-mm-dd'}
            case DTSAPDATE:
                StringBuffer buf = new StringBuffer("{d '");
                SAPDate date = (SAPDate) m_thing;
                int year = date.getYear();
                int month = date.getMonth();
                int day = date.getDayOfMonth();
                if (year < 1000) {
                    buf.append("0");  // pad 0's on left
                }
                if (year < 100) {
                    buf.append("0");
                }
                if (year < 10) {
                    buf.append("0");
                }
                buf.append(String.valueOf(year) + "-");
                if (month < 10) {
                    buf.append("0");
                }
                buf.append(String.valueOf(month) + "-");
                if (day < 10) {
                    buf.append("0");
                }
                buf.append(String.valueOf(day) + "'}");
                return buf.toString();

            case DTDATE:
                StringBuffer buf1 = new StringBuffer("{d '");
                java.sql.Date date1 = (Date) m_thing;
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(date1);
                int year1 = cal1.get(Calendar.YEAR);

                // if year = 9999 timestamp.getYear() returns 8100 and not 8099
                // and hence this special implementation
                if (year1 > 9999) {
                    year1 = 9999;
                }
                int month1 = cal1.get(Calendar.MONTH) + 1;
                int day1 = cal1.get(Calendar.DAY_OF_MONTH);
                if (year1 < 1000) {
                    buf1.append("0");  // pad 0's on left
                }
                if (year1 < 100) {
                    buf1.append("0");
                }
                if (year1 < 10) {
                    buf1.append("0");
                }
                buf1.append(String.valueOf(year1) + "-");
                if (month1 < 10) {
                    buf1.append("0");
                }
                buf1.append(String.valueOf(month1) + "-");
                if (day1 < 10) {
                    buf1.append("0");
                }
                buf1.append(String.valueOf(day1) + "'}");
                return buf1.toString();

            // sap timestamp format: yyyyMMddHHmmss
            case DTSAPTIMESTAMP:
                SAPTimestamp timestampSAP = (SAPTimestamp) m_thing;
                return "'" + timestampSAP.formatyyyyMMddHHmmss() + "'";

            case DTTIMESTAMP:
                StringBuffer buffer1 = new StringBuffer("{ts '");
                Timestamp timestamp = (Timestamp) m_thing;
                Calendar cal = Calendar.getInstance();
                cal.setTime(timestamp);
                int t_year = cal.get(Calendar.YEAR);

                // if year = 9999 timestamp.getYear() returns 8100 and not 8099
                // and hence this special implementation
                if (t_year > 9999) {
                    t_year = 9999;
                }
                int t_month = cal.get(Calendar.MONTH) + 1;
                int t_day = cal.get(Calendar.DAY_OF_MONTH);
                int t_hour = cal.get(Calendar.HOUR_OF_DAY);
                int t_minutes = cal.get(Calendar.MINUTE);
                int t_seconds = cal.get(Calendar.SECOND);
                if (t_year < 1000) {
                    buffer1.append("0");  // pad 0's on left
                }
                if (t_year < 100) {
                    buffer1.append("0");
                }
                if (t_year < 10) {
                    buffer1.append("0");
                }
                buffer1.append(String.valueOf(t_year) + "-");
                if (t_month < 10) {
                    buffer1.append("0");
                }
                buffer1.append(String.valueOf(t_month) + "-");
                if (t_day < 10) {
                    buffer1.append("0");
                }
                buffer1.append(String.valueOf(t_day) + " ");
                if (t_hour < 10) {
                    buffer1.append("0");
                }
                buffer1.append(String.valueOf(t_hour) + ":");
                if (t_minutes < 10) {
                    buffer1.append("0");
                }
                buffer1.append(String.valueOf(t_minutes) + ":");
                if (t_seconds < 10) {
                    buffer1.append("0");
                }
                buffer1.append(String.valueOf(t_seconds) + "'}");
                return buffer1.toString();

            case DTBOOLEAN:

                // Return a literal for an SQL BIT value.
                return ((Boolean) m_thing).booleanValue()
                ? "1"
                : "0";

            case DTBINARY:
                return " ?";  // @sh20010628: fill through prepared statements

            default:
                return m_thing.toString();
        }
    }

    /**
     * Returns the value as String.
     */
    public String toString() {

        // handle null pointer
        if (null == m_thing) {
            return null;

        }
        switch (m_type) {

            case DTINTEGER:
                return String.valueOf((Integer) m_thing);

            case DTSTRING:
                return (String) m_thing;

            case DTUNISTRING:
                return toUnicode((String) m_thing);

            case DTINTEGERSEQ: {
                int[] int_array = (int[]) m_thing;
                String values = String.valueOf(int_array[0]);
                for (int j = 1; j < int_array.length; j++) {
                    values = values + "," + String.valueOf(int_array[j]);
                }

                return values;
            }

            case DTSTRINGSEQ: {
                String[] string_array = (String[]) m_thing;
                String values = string_array[0];
                for (int j = 1; j < string_array.length; j++) {
                    values = values + "," + string_array[j];
                }

                return values;
            }

            case DTDOUBLE:
                return doubleToString(m_thing);

            case DTDOUBLESEQ:
                Double[] double_array = (Double[]) m_thing;
                String values = doubleToString(double_array[0]);
                for (int j = 1; j < double_array.length; j++) {
                    values = values + "," + doubleToString(double_array[j]);
                }

                return values;

            case DTSAPDATE:
                SAPDate date = (SAPDate) m_thing;
                return date.formatYYYYMMDD();

            case DTSAPTIMESTAMP:
                SAPTimestamp timestamp = (SAPTimestamp) m_thing;
                return timestamp.formatyyyyMMddHHmmss();

            case DTBOOLEAN:
                return ((Boolean) m_thing).toString();

            default:

                // throw new exc_not_reached();
                return m_thing.toString();
        }
    }

    public Object getObject() {
        return m_thing;
    }

    /**
     * Converts the value of this object into an SQL literal
     * or (if appropriate) a comma-separated list of SQL literals.
     *
     * Different databases have different rules on how to escape
     * (or not to escape) various characters within string literals;
     * specify the type of quoting with <CODE>quoter</CODE>.
     */
    public String getSQLString(sql_quoter quoter) {
        if (null == m_thing) {
            return "NULL";

        }
        switch (m_type) {

            default:
                throw new exc_not_reached();

            case DTINTEGER:
                return String.valueOf((Integer) m_thing);

            case DTSTRING:
                return quoter.db_quote_string((String) m_thing);

            case DTUNISTRING:
                return toUnicode(quoter.db_quote_string((String) m_thing));

            case DTINTEGERSEQ: {
                int[] int_array = (int[]) m_thing;
                String values = String.valueOf(int_array[0]);
                for (int j = 1; j < int_array.length; j++) {
                    values = values + "," + String.valueOf(int_array[j]);
                }

                return values;
            }

            case DTSTRINGSEQ: {
                String[] string_array = (String[]) m_thing;
                String values = quoter.db_quote_string(string_array[0]);
                for (int j = 1; j < string_array.length; j++) {
                    String value = string_array[j];
                    values = values + "," + ((null == value)
                        ? "NULL"
                        : quoter.db_quote_string(value));
                }
                return values;
            }

            // date format: {d 'yyyy-mm-dd'}
            case DTSAPDATE:
                StringBuffer buf = new StringBuffer("{d '");
                SAPDate date = (SAPDate) m_thing;
                int year = date.getYear();
                int month = date.getMonth();
                int day = date.getDayOfMonth();
                if (year < 1000) {
                    buf.append("0");  // pad 0's on left
                }
                if (year < 100) {
                    buf.append("0");
                }
                if (year < 10) {
                    buf.append("0");
                }
                buf.append(String.valueOf(year) + "-");
                if (month < 10) {
                    buf.append("0");
                }
                buf.append(String.valueOf(month) + "-");
                if (day < 10) {
                    buf.append("0");
                }
                buf.append(String.valueOf(day) + "'}");
                return buf.toString();

            case DTDOUBLE:
                return doubleToString(m_thing);

            case DTDOUBLESEQ:
                Double[] double_array = (Double[]) m_thing;
                String values = doubleToString(double_array[0]);
                for (int j = 1; j < double_array.length; j++) {
                    values = values + "," + doubleToString(double_array[j]);
                }

                return values;

            case DTBOOLEAN:

                // Return a literal for an SQL BIT value.
                return ((Boolean) m_thing).booleanValue()
                ? "1"
                : "0";
        }
    }

    /**
     * Converts the value of this object into an ABAP literal.
     *
     */
    public final String getABAPString() {
        switch (m_type) {

            default:
                throw new exc_not_reached();

            case DTINTEGER:
                if (null == m_thing) {
                    return "0";
                }
                else {
                    return String.valueOf((Integer) m_thing);
                }

            case DTSTRING:
                if (null == m_thing) {
                    return "";
                }
                else {
                    return (String) m_thing;
                }

            case DTDOUBLE:
                if (null == m_thing) {
                    return "0";
                }
                else {
                    return String.valueOf((Double) m_thing);
                }

            case DTSAPDATE:
                if (null == m_thing) {
                    return "00000000";
                }
                else {
                    SAPDate date = (SAPDate) m_thing;
                    return date.formatYYYYMMDD();
                }

            case DTSAPTIMESTAMP:
                if (null == m_thing) {
                    return "00000000000000";
                }
                else {
                    SAPTimestamp timestamp = (SAPTimestamp) m_thing;
                    return timestamp.formatyyyyMMddHHmmss();
                }

            case DTBOOLEAN:
                if (m_thing == null) {
                    return " ";
                }
                else {
                    return ((Boolean) m_thing).booleanValue()
                    ? "T"
                    : "F";
                }

            case DTBINARY:
                if (null == m_thing) {
                    return "";
                }
                else {
                    try {
                        char[] char_value;
                        boolean has_byte_p = true;
                        StringBuffer string_buffer = new StringBuffer();

                        while (has_byte_p) {
                            int value = ((InputStream) m_thing).read();

                            if (value == -1) {
                                has_byte_p = false;
                            }
                            else {
                                char_value = hex_chars[value];
                                string_buffer.append(char_value);
                            }
                        }

                        return string_buffer.toString();
                    }
                    catch (Exception e) {
                        throw new exc_not_reached();
                    }
                }
        }
    }

    public static String doubleToString(Object m_thing) {
        Number num = (Double) m_thing;
        DecimalFormat format = new DecimalFormat();
        format.setMinimumFractionDigits(1);
        format.setMaximumIntegerDigits(127);
        format.setMinimumIntegerDigits(1);
        format.setMaximumFractionDigits(10);
        format.setGroupingUsed(false);
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));

        // TODO HANDLE DB LOCALES
        // WORKAROUND FOR DOWNLOAD ISSUES PUT A '.' AS SEPARATOR
        return format.format(num);
    }

    /**
     * Replaces a single quote by a two single quotes if
     * the single quote is not at the first or last
     * position.
     */
    public static String replaceQuote(String value) {
        int len = value.length();
        if (len == 0) {
            return value;
        }
        StringBuffer strBuf = new StringBuffer(value);
        int index = 1;
        int insertedChar = 0;
        int length = value.length();
        try {
            while ((index > 0) && (index < (length - 1))) {
                index = value.indexOf("'", index);
                if ((index > 0) && (index < (length - 1))) {
                    strBuf = strBuf.insert(index + insertedChar, "'");
                    insertedChar++;
                }
                index++;

            }
        }
        catch (Exception ex) {
        	CATEGORY.logThrowable(Severity.ERROR, LOCATION, MESSAGE_CLASS, 000,
        			"Sys_variant:replaceQuote: " + value + " error: " + ex.getMessage(), new Object[] {  }, ex);
            System.out.println("Sys_variant:replaceQuote: " + value + " error: " + ex.getMessage());
        }
        return strBuf.toString();
    }

    protected String toUnicode(String value) {
        return "N" + value;
    }

    public void forceUnicode() {
        if (m_type == DTSTRING) {
            m_type = DTUNISTRING;
        }
    }

    //	private static char[] bytes_to_chars(byte[] bytes){	
    //		char[] result = new char[bytes.length * 2 ];
    //
    //		for(int i = 0; i < bytes.length; i++) {
    //			char[] chars = hex_chars[(bytes[i] + 256) % 256];
    //			
    //			result[i]     =	chars[0];
    //			result[i + 1] =	chars[1];
    //		}
    //
    //		return result;
    //	}
    public static void main(String[] args) {
        String test1 = "'wert'wert'";
        String test2 = "'wert'";
        String test3 = "'wert'we'rt'";
        String test4 = "''wertwert'";
        String test5 = "'wertwert''";
        String test6 = "''w'e'r't'w'e'r't''";

        System.out.println("vorher: " + test1 + "   nachher: " + replaceQuote(test1));
        System.out.println("vorher: " + test2 + "   nachher: " + replaceQuote(test2));
        System.out.println("vorher: " + test3 + "   nachher: " + replaceQuote(test3));
        System.out.println("vorher: " + test4 + "   nachher: " + replaceQuote(test4));
        System.out.println("vorher: " + test5 + "   nachher: " + replaceQuote(test5));
        System.out.println("vorher: " + test6 + "   nachher: " + replaceQuote(test6));
    }
}

/*--- formatting done in "Custo-Sun Java Convention" style on 12-18-2001 ---*/
