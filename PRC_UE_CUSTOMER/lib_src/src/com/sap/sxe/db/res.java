/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

/*
   $Workfile: res.java $
   $Revision: 1 $
   $Author: Fm $
   $Date: 17.08.98 10:46 $
 */
package com.sap.sxe.db;

import java.io.InputStream;

import java.math.BigDecimal;

import com.sap.sxe.sys.SAPDate;
import com.sap.sxe.sys.SAPTimestamp;
import com.sap.sxe.sys.exc.*;


/***********************************
 **
 **          RESultset
 **
 ************************************/
public abstract class res {
    public final static int C_CHAR_TYPE = type_info.CHAR;
    public final static int C_NCHAR_TYPE = type_info.NCHAR;
    public final static int C_VARCHAR_TYPE = type_info.VARCHAR;
    public final static int C_NVARCHAR_TYPE = type_info.NVARCHAR;
    public final static int C_INTEGER_TYPE = type_info.INTEGER;
    public final static int C_DECIMAL_TYPE = type_info.DECIMAL;
    public final static int C_FLOAT_TYPE = type_info.FLOAT;
    public final static int C_BOOLEAN_TYPE = type_info.BOOLEAN;
    public final static int C_DATE_TYPE = type_info.DATE;
    public final static int C_TIME_TYPE = type_info.TIME;
    public final static int C_TIMESTAMP_TYPE = type_info.TIMESTAMP;
    public final static int C_BINARY_TYPE = type_info.BINARY;
    public final static int C_NUMERIC_TYPE = type_info.NUMERIC;
    public final static int C_UNKNOWN_TYPE = type_info.NULL;
    public final static int C_REAL_TYPE = type_info.REAL;
    private conn m_conn;
    protected String[] m_column_names = null;

    //protected Character c_spacechar = new Character(' ');
    protected res() {
    }

    protected res(conn my_conn) {
        m_conn = my_conn;
    }

    /*        if the result is empty return true
     **                                          else return false
     */
    public static boolean db_empty_results_p(res result) {
        return result == null;
    }

    /* get the String representation of the column
     ** named colName
     **/
    public String db_get_row_element_string(String colName) {
        return db_get_row_element_string(db_get_col_idx(colName));
    }

    /* get the Index of the column from the Name
     **
     */
    public int db_get_col_idx(String colName)
        throws exc_illegal_type_arg  //@@@ change?
     {
        for (int i = 0; i < m_column_names.length; i++) {
            if (colName.equals(m_column_names[i])) {
                return i;
            }
        }
        throw new exc_illegal_type_arg("Invalid column name: " + colName);
    }

    /* determine how many columns are in the resultset
     **
     ** output : column count
     **/
    public abstract int db_get_col_cnt();

    /* information about rows count in the resultset
     **
     ** output : rows count
     **          if ==  0 --> resultset is empty
     **          if == -1 --> not empty, but impossible to determin
     **                                              how many
     **          else     --> actual rows count
     */

    // public abstract int db_get_row_cnt();

    /* resultset db_finish
     ** through this method all the resources hold by the
     ** resultset, will set free
     **/
    public abstract void db_finish();

    /* move the cursor to the next row in the resultset
     **
     ** output : true : if the cursor could be moved
     **          false:        if now next row to the actual exist
     */
    public abstract boolean db_next_row_p()
        throws exc_database_error;

    /* check whether behind the last row
     **
     ** output : true : if the cursor is behind the last row
     **          false:        cursor is inside the result set
     */
    public abstract boolean db_eof_p()
        throws exc_database_error;

    /* move the cursor to the first row in the resultset
     **
     ** output : true : if the cursor could be moved
     **          false:        if now next row to the actual exist
     */
    public abstract boolean db_first_row_p()
        throws exc_database_error;

    /* get the String representation of the colIdx columt
     **
     **/
    public abstract String db_get_row_element_string(int colIdx)
        throws exc_database_error;

    /* get the String representation of the column_index colum.
     ** If the db holds a null, return the empty string ("").
     **
     **/
    public abstract String db_get_row_element_string_no_nulls(int column_index)
        throws exc_database_error;

    /* get the String representation of the column_index colum.
     ** If the db holds an empty string (""), then return a null.
     **
     **/
    public abstract String db_get_row_element_string_nulls(int column_index)
        throws exc_database_error;

    /* get a String representation of the colIdx column,
     **  regardless of the actual data type.
     **
     **/
    public abstract String db_get_row_element_as_string(int colIdx)
        throws exc_database_error;

    /* get the char representation of the column_index colum
     **
     **/
    public abstract char db_get_row_element_char(int column_index)
        throws exc_database_error;

    /* get the colIdx column as a single char; @@@as a temp hack,
     * convert 'X' to true and ' ' to false.
     *
     */
    public abstract boolean db_get_row_element_boolChar(int colIdx)
        throws exc_database_error;

    /* get the int repraesentation of the colIdx columt
     **
     **/
    public abstract Integer db_get_row_element_int(int colIdx)
        throws exc_database_error;

    public abstract Boolean db_get_row_element_bool(int colIdx)
        throws exc_database_error;

    public abstract Double db_get_row_element_double(int colIdx)
        throws exc_database_error;

    public abstract BigDecimal db_get_row_element_decimal(int colIdx, int scale)
        throws exc_database_error;

    public abstract BigDecimal db_get_row_element_decimal(int colIdx)
        throws exc_database_error;

    public abstract InputStream db_get_row_element_stream(int colIdx)
        throws exc_database_error;

    public abstract SAPDate db_get_row_element_date(int colIdx)
        throws exc_database_error;

    public abstract SAPTimestamp db_get_row_element_timestamp(int colIdx)
        throws exc_database_error;

    /* get the Name of the column
     **
     */
    public abstract String db_get_col_name(int colIdx);

    /* get the Type of the column
     **
     */
    public abstract int db_get_col_type(int colIdx);

    protected conn db_get_connection() {
        return m_conn;
    }

    /**
     * Returns true iff the value in the specified column of the
     * current row is null (meaning SQL NULL or an equivalent).
     *
     * Note that some odd data sources treat the empty string as null,
     * or null as the empty string (for example: the Excel driver).
     */
    public abstract boolean db_is_null_p(int column_index)
        throws exc_database_error;
}
