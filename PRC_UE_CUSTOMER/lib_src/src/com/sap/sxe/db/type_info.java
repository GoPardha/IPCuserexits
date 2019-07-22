/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;


/**
 * At this level we only talk of generic datatypes.
 */
public abstract class type_info {
    public static final int BIT = 1;
    public static final int TINYINT = 2;
    public static final int SMALLINT = 3;
    public static final int INTEGER = 4;
    public static final int BIGINT = 5;
    public static final int FLOAT = 6;
    public static final int REAL = 7;
    public static final int DOUBLE = 8;
    public static final int NUMERIC = 9;
    public static final int DECIMAL = 10;
    public static final int CHAR = 11;
    public static final int VARCHAR = 12;
    public static final int LONGVARCHAR = 13;
    public static final int DATE = 14;
    public static final int TIME = 15;
    public static final int TIMESTAMP = 16;
    public static final int BINARY = 17;
    public static final int VARBINARY = 18;
    public static final int LONGVARBINARY = 19;
    public static final int NCHAR = 20;
    public static final int NVARCHAR = 21;
    public static final int NLONGVARCHAR = 22;
    public static final int BOOLEAN = 23;
    public static final int NULL = 0;

    //SQL7-datatypes: Do not use these datatypes if you want to stay multi database enabled.
    //public static final int GUID = 700;
    //public static final int NTEXT = 701; 
    //public static final int NVARCHAR = 702;
    //public static final int NCHAR = 703;  this is not an SQL-7 specific datatype
    //public static final int IMAGE = 704;   this is the equivalent of LONGVARBINARY
    //public static final int TEXT = 705; this is the equivalent of LONGVARCHAR
    //public static final int CURRENCY = 706;  
    //public static final int SMALLCURRENCY = 707;   
    //public static final int DATETIME = 708;
    //public static final int SMALLDATETIME = 709;
    // Sybase SQL Anywhere
    //public static final int LONGBINARY = 900; this is the equivalent of LONGVARBINARY
    protected String m_type_name = null;  //database specific type name
    protected int m_data_type = NULL;  //generic datatype

    //protected int m_db_data_type = NULL; //database specific data type
    public type_info(String type_name, int data_type) {
        m_type_name = type_name;
        m_data_type = data_type;
    }

    public type_info(int data_type) {
        m_type_name = map_ti_data_type(data_type);
        m_data_type = data_type;
    }

    /**
     * Maps the SQL-92 data type to the corresponding name.
     */
    public static String map_ti_data_type(int data_type) {
        switch (data_type) {

            case DATE:
                return "date";

            case TIMESTAMP:
                return "timestamp";

            case CHAR:
                return "char";

            case VARCHAR:
                return "varchar";

            case DECIMAL:
                return "decimal";

            case FLOAT:
                return "float";

            case INTEGER:
                return "int";

            case NUMERIC:
                return "numeric";

            case SMALLINT:
                return "smallint";

            case TINYINT:
                return "tinyint";

            case REAL:
                return "real";

            case BIT:
                return "bit";

            case BIGINT:
                return "bigint";

            case DOUBLE:
                return "double";

            case LONGVARCHAR:
                return "longvarchar";

            case TIME:
                return "time";

            case BINARY:
                return "binary";

            case VARBINARY:
                return "varbinary";

            case LONGVARBINARY:
                return "longvarbinary";

            case NCHAR:
                return "nchar";

            case NVARCHAR:
                return "nvarchar";

            case NLONGVARCHAR:
                return "nlongvarchar";

            default:
                return "";
        }
    }

    /**
     * Maps the SQL-92 data type name to the corresponding constant.
     */
    public static int map_type_name(String type_name) {
        if (type_name.equalsIgnoreCase("bit")) {
            return BIT;
        }
        else if (type_name.equalsIgnoreCase("tinyint")) {
            return TINYINT;
        }
        else if (type_name.equalsIgnoreCase("smallint")) {
            return SMALLINT;
        }
        else if (type_name.equalsIgnoreCase("int")) {
            return INTEGER;
        }
        else if (type_name.equalsIgnoreCase("bigint")) {
            return BIGINT;
        }
        else if (type_name.equalsIgnoreCase("float")) {
            return FLOAT;
        }
        else if (type_name.equalsIgnoreCase("real")) {
            return REAL;
        }
        else if (type_name.equalsIgnoreCase("double")) {
            return DOUBLE;
        }
        else if (type_name.equalsIgnoreCase("double precision")) {
            return DOUBLE;
        }
        else if (type_name.equalsIgnoreCase("numeric")) {
            return NUMERIC;
        }
        else if (type_name.equalsIgnoreCase("decimal")) {
            return DECIMAL;
        }
        else if (type_name.equalsIgnoreCase("char")) {
            return CHAR;
        }
        else if (type_name.equalsIgnoreCase("varchar")) {
            return VARCHAR;
        }
        else if (type_name.equalsIgnoreCase("longvarchar")) {
            return LONGVARCHAR;
        }
        else if (type_name.equalsIgnoreCase("date")) {
            return DATE;
        }
        else if (type_name.equalsIgnoreCase("time")) {
            return TIME;
        }
        else if (type_name.equalsIgnoreCase("timestamp")) {
            return TIMESTAMP;
        }
        else if (type_name.equalsIgnoreCase("binary")) {
            return BINARY;
        }
        else if (type_name.equalsIgnoreCase("varbinary")) {
            return VARBINARY;
        }
        else if (type_name.equalsIgnoreCase("longvarbinary")) {
            return LONGVARBINARY;
        }
        else if (type_name.equalsIgnoreCase("nchar")) {
            return NCHAR;
        }
        else if (type_name.equalsIgnoreCase("nvarchar")) {
            return NVARCHAR;
        }
        else if (type_name.equalsIgnoreCase("nlongvarchar")) {
            return NLONGVARCHAR;
        }
        return NULL;
    }

    public String get_type_name() {
        return m_type_name;
    }

    /**
     * Returns the generic data type. The member m_db_data_type is initialized in the
     * specific subclasses of type_info.
     * The method should return the same result as get_data_type.
     */

    /*
       public int get_db_data_type() {
             return m_db_data_type;
       }
     */

    /**
     * Get default value for columns which are not allowed to contain null.
     */
    public String get_default_definition() {
        switch (m_data_type) {

            case DATE:
            case TIMESTAMP:
                return "18000101";

            case CHAR:
            case VARCHAR:
                return "";

            case DECIMAL:
            case FLOAT:
            case INTEGER:
            case NUMERIC:
            case SMALLINT:
            case TINYINT:
            case REAL:
                return "0";

            default:
                return null;
        }
    }

    /**
     * Returns the generic datatype value as defined in this class;
     * Each subclass overwrites this method and maps the database specific datatype
     * to the generic one in that method.
     */
    public int get_data_type() {
        return m_data_type;
    }

    /*
       public int get_precision() {
       return m_precision;
       }
     */
    public boolean equals(Object o) {
        if (o instanceof type_info) {
            type_info ti = (type_info) o;
            return ti.get_type_name().equals(m_type_name) && (ti.get_data_type() == m_data_type);
        }
        else {
            return false;
        }
    }

    public int hashCode() {
        return m_type_name.hashCode() ^ new Integer(m_data_type).hashCode();
    }
}
