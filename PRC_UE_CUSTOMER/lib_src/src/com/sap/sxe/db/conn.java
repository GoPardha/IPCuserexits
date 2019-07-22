/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

/*
   $Workfile: conn.java $
   $Revision: 2 $
   $Author: Fm $
   $Date: 9/30/98 4:06p $
 */
package com.sap.sxe.db;

import java.util.Enumeration;
import java.util.Vector;

import com.sap.sxe.sys.exc.exc_database_error;


//import java.sql.*;

/***********************************
 **
 **          CONNection
 **
 ************************************/
/**
 * Superclass for all connections in handled in the SCE and the SPE.
 * Defines the type of a connection.
 * Changes: 23.02.2000 tm db_modify_table can now expect null as query_pair parameter
 * which means update all rows (no where condition).
 */
public abstract class conn {
    protected String _driver = null;
    protected String m_user = null;
    protected sql_string_factory m_sql_string_factory = null;
    Vector m_sqlListeners = new Vector();

    /**
     * Constructor.
     * after creating a connection you have to invoke set_sql_string_factory()
     */
    protected conn() {
    }

    protected conn(String user, String driver) {
        m_user = user;
        _driver = driver;
    }

    /**
     * Allow to use specific sys_variant behavior based on connection type
     *
     */
    public sys_variant castSysVariant(sys_variant sysvar) {
        return sysvar;
    }

    /**
     * TRUE : is currently in transaction
     * FALSE: currently not in a transaction
     */
    abstract protected boolean db_is_in_transaction()
        throws exc_database_error;

    /**
     * The SQL statements are grouped into
     * transactions that are terminated by either commit() or
     * rollback().  See java.sql.Connection.
     */
    abstract protected void db_start_trans()
        throws exc_database_error;

    /**
     * Commit makes all changes made since the previous
     * commit/rollback permanent and releases any database locks
     * currently held by the Connection. This method should only be
     * used when auto commit has been disabled.
     * See java.sql.Connection.
     */
    abstract protected void db_commit()
        throws exc_database_error;

    /**
     * Rollback drops all changes made since the previous
     * commit/rollback and releases any database locks currently
     * held by the Connection. This method should only be used when
     * auto commit has been disabled.
     * See java.sql.Connection.
     */
    abstract protected void db_rollback()
        throws exc_database_error;

    /**
     * Returns the current schema for the connection.
     */
    public abstract schema db_get_current_schema()
        throws exc_database_error;

    /**
     * Returns the current database for the connection.
     */
    public abstract database db_get_current_database()
        throws exc_database_error;

    public abstract metadata db_get_meta_data()
        throws exc_database_error;

    public abstract Long getStartTime();

    public abstract Long getEndTime();

    public abstract Long getExecutionTime();

    public abstract prepared_statement db_prepare_statement(String stmnt)
        throws exc_database_error;

    protected prepared_statement db_prepare_statement(String table, sys_query_pair[] query_pair, String[] projection,
        String[] sorting) throws exc_database_error {
        String sql_string = m_sql_string_factory.get_sql_string_read_table(table, query_pair, projection, sorting);

        //                System.out.println("\nSQL = <" + sql_string + ">");
        return db_prepare_statement(sql_string);
    }

    protected prepared_statement db_prepare_statement(String table, sys_query_pair[] query_pair, String[] projection,
        Ranges[] ranges) throws exc_database_error {
        String sql_string = m_sql_string_factory.get_sql_string_read_table(table, query_pair, projection, ranges);

        //                System.out.println("\nSQL = <" + sql_string + ">");
        return db_prepare_statement(sql_string);
    }

    protected prepared_statement db_prepare_statement_row_count(String table, sys_query_pair[] query_pair)
        throws exc_database_error {
        String sql_string = m_sql_string_factory.get_sql_string_count_rows(table, query_pair);

        //                System.out.println("\nSQL = <" + sql_string + ">");
        return db_prepare_statement(sql_string);
    }

    protected abstract void db_close_connection()
        throws exc_database_error;

    private res db_execute_query_event(String sqlString, boolean rewindable) {
        fireSqlEvent(sqlString);

        //                System.out.println("\nSQL = <" + sqlString + ">");
        res resultSet = db_execute_query(sqlString, rewindable);
        return resultSet;
    }

    private int db_execute_action_query_event(String sqlString) {
        fireSqlEvent(sqlString);

        //                System.out.println("\nSQL = <" + sqlString + ">");
        int retVal = db_execute_action_query(sqlString);
        return retVal;
    }

    //        private int db_execute_update_event(String sqlString)
    //        {
    //                fireSqlEvent(sqlString);
    ////                System.out.println("\nSQL = <" + sqlString + ">");
    //                int retVal = db_execute_update(sqlString);
    //                return retVal;
    //        }
    protected abstract res db_execute_query(String sql_string, boolean rewindable)
        throws exc_database_error;

    public abstract int db_execute_action_query(String sql_string)
        throws exc_database_error;

    protected abstract int db_execute_update(String sql_string)
        throws exc_database_error;

    /**
     * Selects data from the specified table.
     * @param readFromBuffer indicates whether selection should read from buffer or bypass it
     * The present default implementation ignores readFromBuffer.
     * Subclasses should overwrite this method if bypassing buffer
     * makes sense for the corresponding database connection
     */
    public res db_read_table(String table, boolean readFromBuffer, sys_query_pair[] query_pair, String[] projection,
        String[] sorting) {
        return db_read_table(table, query_pair, projection, sorting, true);
    }

    public abstract res db_read_table(String table, boolean readFromBuffer, sys_query_pair[] query_pair,
        String[] projection, String[] sorting, boolean forwardRequest)
        throws exc_database_error;

    public res db_read_table(String table, sys_query_pair[] query_pair, String[] projection, String[] sorting,
        boolean NOT_IN_USE) throws exc_database_error {
        String sql_string = m_sql_string_factory.get_sql_string_read_table(table, query_pair, projection, sorting);

        //                System.out.println("\nSQL = <" + sql_string + ">");
        return db_execute_query_event(sql_string, NOT_IN_USE);
    }

    public abstract res db_read_table(boolean forwardRequest, String table, sys_query_pair[] query_pairs,
        String[] projection, String[] sorting, boolean NOT_IN_USE)
        throws exc_database_error;

    /**
     * Selects data from the specified table.
     * @param readFromBuffer indicates whether selection should read from buffer or bypass it
     * The present default implementation ignores readFromBuffer.
     * Subclasses should overwrite this method if bypassing buffer
     * makes sense for the corresponding database connection
     */
    public res db_read_table(String table, boolean readFromBuffer, sys_query_pair[] query_pair, String[] projection,
        Ranges[] ranges) throws exc_database_error {
        return db_read_table(table, query_pair, projection, ranges);
    }

    public abstract res db_read_table(String table, boolean readFromBuffer, sys_query_pair[] query_pair,
        String[] projection, Ranges[] ranges, boolean forwardRequest)
        throws exc_database_error;

    public res db_read_table(String table, sys_query_pair[] query_pair, String[] projection, Ranges[] ranges)
        throws exc_database_error {
        String sql_string = m_sql_string_factory.get_sql_string_read_table(table, query_pair, projection, ranges);

        //                System.out.println("\nSQL = <" + sql_string + ">");
        return db_execute_query_event(sql_string, false);
    }

    public abstract res db_read_table(boolean forwardRequest, String table, sys_query_pair[] query_pairs,
        String[] projection, Ranges[] ranges)
        throws exc_database_error;

    /**
     * Selects one single row from the specified table.
     * @param readFromBuffer indicates whether selection should read from buffer or bypass it
     * The present default implementation ignores readFromBuffer.
     * Subclasses should overwrite this method if bypassing buffer
     * makes sense for the corresponding database connection
     * @param sorting Array of fields by which the selection has to be ordered
     * If it is empty an arbitrary line satisfying the selection criteria is returned.
     * Otherwise the according lines are sorted by the given fields
     * and the resulting first line is returned.
     */
    public res db_read_first_row(String table, boolean readFromBuffer, sys_query_pair[] query_pair,
        String[] projection, String[] sorting)
        throws exc_database_error {
        return db_read_first_row(table, query_pair, projection, sorting);
    }

    public abstract res db_read_first_row(String table, boolean readFromBuffer, sys_query_pair[] query_pair,
        String[] projection, String[] sorting, boolean forwardRequest)
        throws exc_database_error;

    public res db_read_first_row(String table, sys_query_pair[] query_pair, String[] projection, String[] sorting)
        throws exc_database_error {
        String sql_string = m_sql_string_factory.get_sql_string_read_first_row(table, query_pair, projection, sorting);

        //                 System.out.println("\nSQL = <" + sql_string + ">");
        return db_execute_query_event(sql_string, false);
    }

    public abstract res db_read_first_row(boolean forwardRequest, String table, sys_query_pair[] query_pairs,
        String[] projection, String[] sorting)
        throws exc_database_error;

    /**
     * Counts number of table entries
     * @param readFromBuffer indicates whether selection should read from buffer or bypass it
     * The present default implementation ignores readFromBuffer.
     * Subclasses should overwrite this method if bypassing buffer
     * makes sense for the corresponding database connection
     */
    public int db_row_count(String table, boolean readFromBuffer, sys_query_pair[] query_pair) {
        return db_row_count(table, query_pair);
    }

    public int db_row_count(String table, sys_query_pair[] query_pair) {
        String sql_string = m_sql_string_factory.get_sql_string_count_rows(table, query_pair);

        //                System.out.println("\nSQL = <" + sql_string + ">");
        res result = db_execute_query_event(sql_string, false);
        int count = result.db_get_row_element_int(0).intValue();
        result.db_finish();
        return count;
    }

    /*
       public Integer db_append_table( String table, String[] vals)
               throws exc_database_error
       {
               int numVals = vals.length;
               if (numVals < 1)
                       return null;
               String sql_string = m_sql_string_factory.get_sql_string_append_table(table, vals);
               //System.out.println("\nSQL = <" + sql_string + ">");
               return new Integer(db_execute_action_query_event(sql_string));
       }
     */

    /** Insert new rows using INSERT INTO, using column names and values in query_pair.
     * @return ... */
    public Integer db_append_table(String table, sys_query_pair[] query_pair)
        throws exc_database_error {
        if ((query_pair == null) || (query_pair.length == 0)) {
            return null;
        }
        String sql_string = m_sql_string_factory.get_sql_string_append_table(table, query_pair);

        //                System.out.println("\nSQL = <" + sql_string + ">");
        return new Integer(db_execute_action_query_event(sql_string));
    }

    /** Insert new rows using INSERT INTO, using column names and values in query_pair.
     * @return ... */
    public Integer db_append_table(table table, sys_query_pair[] query_pair)
        throws exc_database_error {
        if ((query_pair == null) || (query_pair.length == 0)) {
            return null;
        }
        String sql_string = m_sql_string_factory.get_sql_string_append_table(table, query_pair);

        //                System.out.println("\nSQL = <" + sql_string + ">");
        return new Integer(db_execute_action_query_event(sql_string));
    }

    public Integer db_modify_table(String table, sys_query_pair[] query_pair, sys_query_pair[] newVal)
        throws exc_database_error {

        //if (query_pair == null || query_pair.length == 0)
        //	 return null;
        if ((newVal == null) || (newVal.length == 0)) {
            return null;
        }
        String sql_string = m_sql_string_factory.get_sql_string_modify_table(table, query_pair, newVal);

        //                System.out.println("\nSQL = <" + sql_string + ">");
        return new Integer(db_execute_update(sql_string));
    }

    public Integer db_delete_table(String table, sys_query_pair[] query_pair)
        throws exc_database_error {
        if ((query_pair == null) || (query_pair.length == 0)) {
            return null;
        }
        String sql_string = m_sql_string_factory.get_sql_string_delete_table(table, query_pair);

        //                System.out.println("\nSQL = <" + sql_string + ">");
        return new Integer(db_execute_update(sql_string));
    }

    public Integer db_delete_table(String table)
        throws exc_database_error {
        String sql_string = m_sql_string_factory.get_sql_string_delete_table(table);

        //                System.out.println("\nSQL = <" + sql_string + ">");
        return new Integer(db_execute_update(sql_string));
    }

    //
    protected void set_sql_string_factory() {
        m_sql_string_factory = new sql_string_factory(this);
    }

    public sql_string_factory get_sql_string_factory() {
        return m_sql_string_factory;
    }

    public void addSqlListener(SqlListener listener) {
        if (!m_sqlListeners.contains(listener)) {
            m_sqlListeners.addElement(listener);
        }
    }

    public void removeSqlListener(SqlListener listener) {
        if (m_sqlListeners.contains(listener)) {
            m_sqlListeners.removeElement(listener);
        }
    }

    private void fireSqlEvent(String sqlString) {
        SqlEvent e = new SqlEvent(this, sqlString);
        for (Enumeration en = m_sqlListeners.elements(); en.hasMoreElements();) {
            SqlListener l = (SqlListener) en.nextElement();
            l.executeSql(e);
        }
    }
//  To check the MSSQL Connectivity
    public boolean db_is_closed() {
    	return false;
    }    
}
