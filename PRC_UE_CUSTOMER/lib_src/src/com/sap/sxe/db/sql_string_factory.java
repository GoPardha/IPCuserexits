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

import java.util.HashMap;
import java.util.Map;


public class sql_string_factory {
    public final static String SELECT = "SELECT ";
    public final static String FROM = " FROM ";
    public final static String WHERE = " WHERE ";
    public final static String WHERE1 = " WHERE ( ";
    public final static String ORDER_BY = " ORDER BY ";
    public final static String OR = " OR ";
    public final static String OR_WITH_BRACKETS = " ) OR ( ";
    public final static String AND = " ) AND ( ";
    public final static String COUNT = "COUNT(*)";
    public final static String OPENING_AND_BRACKET = " ) AND ( ( ";
    public final static String CLOSING_BRACKET = " ) ";
    protected conn _connection;
    protected metadata _metadata;
    public String m_keywordEscapeSequenceStart = "";
    public String m_keywordEscapeSequenceEnd = "";
    private schema _sc = null;
    private Map _table_handles;
    
    
    protected sql_string_factory(){
    	
    }

    // private String RFC = "RFC";
    // private boolean rfc = false;
    public sql_string_factory(conn connection) {
        _connection = connection;
        _metadata = _connection.db_get_meta_data();
        _table_handles = new HashMap();
        if (_metadata != null) {
            m_keywordEscapeSequenceStart = _metadata.get_keyword_name_separator_start();
            m_keywordEscapeSequenceEnd = _metadata.get_keyword_name_separator_end();

            // if(_metadata.get_db_product_name().equalsIgnoreCase(RFC))
            // rfc = true;
        }
    }

    public String get_sql_string_read_table(String table, sys_query_pair[] query_pair, String[] projection,
        String[] sorting) {
        return get_sql_string_select_rows(table, query_pair, projection, sorting, 0);
    }

    public String get_sql_string_read_table(String table, sys_query_pair[] query_pair, String[] projection,
        Ranges[] ranges) {
        String sql_string = get_sql_string_select_rows(table, query_pair, projection, null, 0);

        StringBuffer query = new StringBuffer(sql_string);

        boolean appendAnd = false;
        if ((query_pair != null) && (query_pair.length > 0) && (ranges != null) && (ranges.length > 0)) {
            appendAnd = true;

        }
        for (int i = 0; i < ranges.length; i++) {
            String s = ranges[i].getSQLCondition(m_keywordEscapeSequenceStart, m_keywordEscapeSequenceEnd);
            if (!s.equals("")) {
                if (appendAnd) {
                    query.append(" AND ");
                }
                query.append(s);
            }
        }

        return query.toString();
    }

    public String get_sql_string_read_first_row(String table, sys_query_pair[] query_pair, String[] projection,
        String[] sorting) {
        return get_sql_string_select_rows(table, query_pair, projection, sorting, 1);
    }

    public String get_sql_string_count_rows(String table, sys_query_pair[] query_pair) {

        // SELECT
        StringBuffer sql_string = new StringBuffer(SELECT);
        sql_string.append(COUNT);

        // FROM
        sql_string.append(FROM);
        sql_string.append(m_keywordEscapeSequenceStart);
        sql_string.append(table);
        sql_string.append(m_keywordEscapeSequenceEnd);

        // WHERE
        if (query_pair != null) {
            sql_string.append(get_sql_string_where(query_pair));

        }
        return sql_string.toString();
    }

    /*
     * public String get_sql_string_append_table(String table, String[] vals) {
     *
     *
     * StringBuffer sql_string = new StringBuffer("INSERT INTO ");
     * sql_string.append(m_keywordEscapeSequenceStart);
     * sql_string.append(table);
     * sql_string.append(m_keywordEscapeSequenceEnd);
     * sql_string.append(" VALUES(");
     *
     * // values with commas
     * sql_string.append(vals[0]);
     * for (int i = 1; i < vals.length; i++) {
     * sql_string.append(",");
     * sql_string.append(vals[i]);
     * }
     *
     * sql_string.append(")");
     *
     * return sql_string.toString();
     * }
     */
    /*
     * Allow compatibility with other package
     */
    public String get_sql_string_append_table(String table, sys_query_pair[] query_pair) {

        //database db = _connection.db_get_current_database();
        if (_sc == null) {
            _sc = _connection.db_get_current_schema();
        }

        //schema sc = m_connection.db_get_current_schema();
        table tbl = (table) _table_handles.get(table);
        if (tbl == null) {
            tbl = _sc.get_tables().get_table(table);
            _table_handles.put(table, tbl);
        }

        return get_sql_string_append_table(tbl, query_pair);
    }

    /*
     * schema.get_tables() is performance consuming. This Method allow to insert row using cached tables.
     */
    public String get_sql_string_append_table(table table, sys_query_pair[] query_pair) {
        StringBuffer sql_string = new StringBuffer("INSERT INTO ");
        sql_string.append(m_keywordEscapeSequenceStart);
        sql_string.append(table.get_name());
        sql_string.append(m_keywordEscapeSequenceEnd);

        StringBuffer sCols = new StringBuffer();
        StringBuffer sVals = new StringBuffer();

        for (int i = 0; i < query_pair.length; i++) {

            // column name
            String columnName = query_pair[i].sys_getName();
            sCols.append(m_keywordEscapeSequenceStart);
            sCols.append(columnName);
            sCols.append(m_keywordEscapeSequenceEnd);

            // column value
            String columnValue = null;
            sys_variant variant = _connection.castSysVariant(query_pair[i].sys_getValue());

            // test
            // alternative solution for unicode : not used
            // if (ti.m_data_type == ti.NCHAR || ti.m_data_type == ti.NVARCHAR || ti.m_data_type == ti.NLONGVARCHAR) {
            // variant.forceUnicode();
            // }
            if (variant.getObject() == null) {
                column col = table.get_columns().get_column(columnName);
                if (col != null) {
                    type_info ti = col.get_data_type();

                    // column col = table.get_columns().get_column(columnName);
                    col = table.get_columns().get_column(columnName);
                    if (!col.is_nullable()) {

                        // type_info ti = col.get_data_type();
                        ti = col.get_data_type();
                        columnValue = ti.get_default_definition();
                    }
                }
            }
            else {
                columnValue = variant.getSQLString();
            }

            sVals.append(columnValue);

            if (i != (query_pair.length - 1)) {
                sCols.append(",");
                sVals.append(",");
            }
        }

        sql_string.append(" (");
        sql_string.append(sCols.toString());
        sql_string.append(") VALUES ( ");
        sql_string.append(sVals.toString());
        sql_string.append(")");

        return sql_string.toString();
    }

    public String get_sql_string_modify_table(String table, sys_query_pair[] query_pair, sys_query_pair[] newVal) {

        //    database db = _connection.db_get_current_database();
        if (_sc == null) {
            _sc = _connection.db_get_current_schema();
        }

        //schema sc = m_connection.db_get_current_schema();
        table tbl = (table) _table_handles.get(table);
        if (tbl == null) {
            tbl = _sc.get_tables().get_table(table);
            _table_handles.put(table, tbl);
        }

        //table tbl = m_sc.get_tables().get_table(table);
        return get_sql_string_modify_table(tbl, query_pair, newVal);
    }

    public String get_sql_string_modify_table(table table, sys_query_pair[] query_pair, sys_query_pair[] newVal) {
        StringBuffer sql_string = new StringBuffer("UPDATE ");
        sql_string.append(m_keywordEscapeSequenceStart);
        sql_string.append(table.get_name());
        sql_string.append(m_keywordEscapeSequenceEnd);
        sql_string.append(" SET ");

        for (int i = 0; i < newVal.length; i++) {
            String columnName = newVal[i].sys_getName();

            // column value
            String columnValue = null;
            sys_variant variant = _connection.castSysVariant(newVal[i].sys_getValue());

            if (variant.getObject() == null) {
                column col = table.get_columns().get_column(columnName);
                if (!col.is_nullable()) {
                    type_info ti = col.get_data_type();
                    columnValue = ti.get_default_definition();
                }
            }
            else {
                columnValue = variant.getSQLString();
            }

            sql_string.append(m_keywordEscapeSequenceStart);
            sql_string.append(columnName);
            sql_string.append(m_keywordEscapeSequenceEnd);
            sql_string.append("=");
            sql_string.append(columnValue);

            if (i != (newVal.length - 1)) {
                sql_string.append(",");
            }
        }

        if ((query_pair != null) && (query_pair.length != 0)) {
            sql_string.append(get_sql_string_where(query_pair));
        }

        return sql_string.toString();

    }

    public String get_sql_string_delete_table(String table, sys_query_pair[] query_pair) {
        StringBuffer sql_string = new StringBuffer("DELETE FROM ");
        sql_string.append(m_keywordEscapeSequenceStart);
        sql_string.append(table);
        sql_string.append(m_keywordEscapeSequenceEnd);

        sql_string.append(get_sql_string_where(query_pair));

        return sql_string.toString();
    }

    public String get_sql_string_delete_table(String table) {
        StringBuffer sql_string = new StringBuffer("DELETE FROM ");
        sql_string.append(m_keywordEscapeSequenceStart);
        sql_string.append(table);
        sql_string.append(m_keywordEscapeSequenceEnd);

        return sql_string.toString();
    }

    protected String get_sql_string_select_rows(String table, sys_query_pair[] query_pair, String[] projection,
        String[] sorting, int rows) {

        // rows == 0: result set contains all rows
        // SELECT
        StringBuffer sql_string = new StringBuffer(SELECT);

        if (rows > 0) {
            if (_metadata != null) {
                sql_string.append(_metadata.get_keyword_limit_resultset(rows));
                sql_string.append(" ");
            }
        }

        if ((projection == null) || (projection.length == 0)) {
            sql_string.append("* ");
        }
        else {
            for (int i = 0; i < projection.length; i++) {
                if (i > 0) {
                    sql_string.append(", ");
                }
                sql_string.append(m_keywordEscapeSequenceStart);
                sql_string.append(projection[i]);
                sql_string.append(m_keywordEscapeSequenceEnd);
            }
        }

        // FROM
        sql_string.append(FROM);
        sql_string.append(m_keywordEscapeSequenceStart);
        sql_string.append(table);
        sql_string.append(m_keywordEscapeSequenceEnd);

        // WHERE
        if (query_pair != null) {
            sql_string.append(get_sql_string_where(query_pair));

            // ORDER BY
        }
        if (sorting != null) {
            sql_string.append(ORDER_BY);

            for (int i = 0; i < sorting.length; i++) {
                if (i > 0) {
                    sql_string.append(", ");
                }
                sql_string.append(m_keywordEscapeSequenceStart);
                sql_string.append(sorting[i]);
                sql_string.append(m_keywordEscapeSequenceEnd);
            }
        }

        return sql_string.toString();
    }

    public StringBuffer get_sql_string_where(sys_query_pair[] query_pair) {
        if (query_pair.length > 0) {
            StringBuffer sql_string = new StringBuffer(WHERE);
            return sql_string.append(get_sql_string_where_conditions(query_pair));
        }
        else {
            return new StringBuffer("");
        }
    }

    public StringBuffer get_sql_string_where_conditions(sys_query_pair[] query_pair) {
        StringBuffer sql_string = new StringBuffer("");
        String previous_key = "";  // artificially set to ""
        if (query_pair.length > 0) {
            sql_string.append("( ");

        }
        for (int i = 0; i < query_pair.length; i++) {
            sys_variant variant = _connection.castSysVariant(query_pair[i].sys_getValue());
            String operator = " " + query_pair[i].sys_getOperator() + " ";

            if (!previous_key.equals(query_pair[i].sys_getName())) {
                if (i != 0) {
                    if (query_pair[i].sys_getCondition().equalsIgnoreCase("OR")) {
                        sql_string.append(OR_WITH_BRACKETS);
                    }
                    else {
                        sql_string.append(AND);
                    }
                }
                previous_key = query_pair[i].sys_getName();
            }
            else {
            	if(query_pair[i].sys_getCondition().equals(sys_query_pair.AND)){
                    sql_string.append(AND);
            	}
            	else{
            		sql_string.append(OR);
            	}
            }

            switch (variant.getvt()) {

                case 4: {
                    String[] sArray = variant.getStringSeq();

                    for (int j = 0; j < sArray.length; j++) {
                        if (j != 0) {
                            sql_string.append(OR);
                        }
                        sql_string.append(m_keywordEscapeSequenceStart);
                        sql_string.append(previous_key);
                        sql_string.append(m_keywordEscapeSequenceEnd);
                        sql_string.append(operator);
                        sql_string.append("'");
                        sql_string.append(sArray[j]);
                        sql_string.append("'");
                    }
                }
                break;

                case 3: {
                    int[] iArray = variant.getIntSeq();

                    for (int j = 0; j < iArray.length; j++) {
                        if (j != 0) {
                            sql_string.append(OR);
                        }
                        sql_string.append(m_keywordEscapeSequenceStart);
                        sql_string.append(previous_key);
                        sql_string.append(m_keywordEscapeSequenceEnd);
                        sql_string.append(operator);
                        sql_string.append(iArray[j]);
                    }
                }
                break;

                case 6: {
                    double[] dArray = variant.getDoubleSeq();

                    for (int j = 0; j < dArray.length; j++) {
                        if (j != 0) {
                            sql_string.append(OR);
                        }
                        sql_string.append(m_keywordEscapeSequenceStart);
                        sql_string.append(previous_key);
                        sql_string.append(m_keywordEscapeSequenceEnd);
                        sql_string.append(operator);
                        sql_string.append(dArray[j]);
                    }
                }
                break;

                case 7:
                case 8:
                case 9:
                case 11:default:
                    String values = variant.getSQLString();
                    sql_string.append(m_keywordEscapeSequenceStart);
                    sql_string.append(previous_key);
                    sql_string.append(m_keywordEscapeSequenceEnd);
                    sql_string.append(operator);
                    sql_string.append(values);
            }
        }
        if (query_pair.length > 0) {
            sql_string.append(" )");
        }
        return sql_string;
    }

    /**
     * Method declaration
     *
     *
     * @param variant
     * @param table
     * @param columnName
     *
     * @return
     *
     * @see
     */
    public sys_variant castUnicodeSysVariant(sys_variant variant, String table, String columnName) {
        return variant;
    }

    /**
     * Method declaration
     *
     *
     * @param variant
     * @param table
     * @param columnName
     *
     * @return
     *
     * @see
     */
    public sys_variant castUnicodeSysVariant(sys_variant variant, table table, String columnName) {
        return variant;
    }
}

/*--- formatting done in "Custo-Sun Java Convention" style on 12-19-2001 ---*/
