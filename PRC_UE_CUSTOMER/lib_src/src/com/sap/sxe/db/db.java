/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

import com.sap.sxe.sys.exc.exc_database_error;
import com.sap.vmc.logging.LocalizableCategory;
import com.sap.vmc.logging.Category;
import com.sap.vmc.logging.Location;
import com.sap.vmc.logging.Severity;

/***********************************
 * The db class holds a set of db objects,
 * for each set of connection parameter, one db object.
 * Multiple clients can use one db object which holds
 * a physical database connection. This connection is
 * closed only if the db object is not used by any clients
 * anymore.
 *
 ************************************/
public class db {
    public static final LocalizableCategory CATEGORY = LocalizableCategory.getCategory(Category.APPLICATIONS, "/AP/Base/Database");
    public static final String MESSAGE_CLASS = "IPC_DB";
    public static final Location LOCATION = Location.getLocation(db.class);
    protected static db c_instance;

    // wrapping the connection
    protected conn m_conn;

    // If m_user_count == 0 then close connection and release instance
    protected int m_user_count = 0;

    // CONSTRUCTORS:
    protected db(conn my_conn) {
        m_conn = my_conn;
    }

    /**  Open a connection to a datasource <br>
     ** if the connection could be established
     **    return true
     ** if not
     **    return false.
     */
    private static db db_open()
        throws exc_database_error {
        if (c_instance == null) {
            conn my_conn = connection_factory.create_connection();
            c_instance = new db(my_conn);
        }

        c_instance.dbx_ensure_open();
        c_instance.m_user_count++;

        return c_instance;
    }

    public static db getDb() {
        return db_open();
    }

    /**
     * TRUE : is currently in transaction
     * FALSE: currently not in a transaction
     */
    public boolean db_is_in_trans()
        throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_is_in_transaction();
    }

    /**
     * Starts a transaction IF currently not in transaction
     * If the transaction is started it can be terminated
     * with db_commit or db_rollback.
     * If currently a transaction is running, this command
     * will be IGNORED
     */
    public void db_start_trans()
        throws exc_database_error {
        dbx_ensure_open();
        m_conn.db_start_trans();
    }

    /**
     * Commit makes all changes made since the previous
     * commit/rollback permanent and releases any database locks
     * currently held by the Connection. This method should only be
     * used when auto commit has been disabled.
     * See java.sql.Connection.
     */
    public void db_commit() throws exc_database_error {
        dbx_ensure_open();
        m_conn.db_commit();
    }

    /**
     * Rollback drops all changes made since the previous
     * commit/rollback and releases any database locks currently
     * held by the Connection. This method should only be used when
     * auto commit has been disabled.  See java.sql.Connection.
     */
    public void db_rollback()
        throws exc_database_error {
        dbx_ensure_open();
        m_conn.db_rollback();
        m_conn.db_get_current_schema().get_tables().refresh();
    }

    /**
     * Close the database if it is not used anymore.
     */
    public void db_close() throws exc_database_error {
        dbx_ensure_open();

        //if(m_conn == null)
        //	 return;
        if (--m_user_count == 0) {
            c_instance = null;
            m_conn.db_close_connection();
            m_conn = null;
        }
    }

    /**
     * Returns true, if the db is still used
     */
    public boolean db_is_in_use() {
        return m_user_count != 0;
    }

    public res db_read_table(String table)
        throws exc_database_error {
        return db_read_table(table, null, null, null, false);
    }

    public res db_read_table(String table, sys_query_pair[] query_pair)
        throws exc_database_error {
        return db_read_table(table, query_pair, null, null, false);
    }

    public res db_read_table(String table, sys_query_pair[] query_pair, String[] projection)
        throws exc_database_error {
        return db_read_table(table, query_pair, projection, null, false);
    }

    public res db_read_table(String table, sys_query_pair[] query_pair, String[] projection, String[] sorting)
        throws exc_database_error {
        return db_read_table(table, query_pair, projection, sorting, false);
    }

    public res db_read_table(String table, sys_query_pair[] query_pair, String[] projection, String[] sorting,
        boolean not_in_use) throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_read_table(table, query_pair, projection, sorting, not_in_use);
    }

    public res db_read_table(String table, sys_query_pair[] query_pair, String[] projection, Ranges[] ranges)
        throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_read_table(table, query_pair, projection, ranges);
    }

    public res db_read_table(String table, boolean readFromBuffer, sys_query_pair[] query_pair, String[] projection) {
        dbx_ensure_open();
        return m_conn.db_read_table(table, readFromBuffer, query_pair, projection, (String[]) null);
    }

    public res db_read_table(String table, boolean readFromBuffer, sys_query_pair[] query_pair, String[] projection,
        String[] sorting) {
        dbx_ensure_open();
        return m_conn.db_read_table(table, readFromBuffer, query_pair, projection, sorting);
    }

    public res db_read_table(String table, boolean readFromBuffer, sys_query_pair[] query_pair, String[] projection,
        Ranges[] ranges) throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_read_table(table, readFromBuffer, query_pair, projection, ranges);
    }

    public res db_read_first_row(String table, sys_query_pair[] query_pair, String[] projection, String[] sorting)
        throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_read_first_row(table, query_pair, projection, sorting);
    }

    public res db_read_first_row(String table, boolean readFromBuffer, sys_query_pair[] query_pair,
        String[] projection, String[] sorting)
        throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_read_first_row(table, readFromBuffer, query_pair, projection, sorting);
    }

    public res db_read_table(boolean forwardRequest, String table)
        throws exc_database_error {
        return db_read_table(forwardRequest, table, null, null, null, false);
    }

    public res db_read_table(boolean forwardRequest, String table, sys_query_pair[] query_pair)
        throws exc_database_error {
        return db_read_table(forwardRequest, table, query_pair, null, null, false);
    }

    public res db_read_table(boolean forwardRequest, String table, sys_query_pair[] query_pair, String[] projection)
        throws exc_database_error {
        return db_read_table(forwardRequest, table, query_pair, projection, null, false);
    }

    public res db_read_table(boolean forwardRequest, String table, sys_query_pair[] query_pair, String[] projection,
        String[] sorting) throws exc_database_error {
        return db_read_table(forwardRequest, table, query_pair, projection, sorting, false);
    }

    public res db_read_table(boolean forwardRequest, String table, sys_query_pair[] query_pair, String[] projection,
        String[] sorting, boolean not_in_use)
        throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_read_table(forwardRequest, table, query_pair, projection, sorting, not_in_use);
    }

    public res db_read_table(boolean forwardRequest, String table, sys_query_pair[] query_pair, String[] projection,
        Ranges[] ranges) throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_read_table(forwardRequest, table, query_pair, projection, ranges);
    }

    public res db_read_table(boolean forwardRequest, String table, boolean readFromBuffer, sys_query_pair[] query_pair,
        String[] projection) {
        dbx_ensure_open();
        return m_conn.db_read_table(table, readFromBuffer, query_pair, projection, (String[]) null, forwardRequest);
    }

    public res db_read_table(boolean forwardRequest, String table, boolean readFromBuffer, sys_query_pair[] query_pair,
        String[] projection, String[] sorting) {
        dbx_ensure_open();
        return m_conn.db_read_table(table, readFromBuffer, query_pair, projection, sorting, forwardRequest);
    }

    public res db_read_table(boolean forwardRequest, String table, boolean readFromBuffer, sys_query_pair[] query_pair,
        String[] projection, Ranges[] ranges)
        throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_read_table(table, readFromBuffer, query_pair, projection, ranges, forwardRequest);
    }

    public res db_read_first_row(boolean forwardRequest, String table, sys_query_pair[] query_pair,
        String[] projection, String[] sorting)
        throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_read_first_row(forwardRequest, table, query_pair, projection, sorting);
    }

    public res db_read_first_row(boolean forwardRequest, String table, boolean readFromBuffer,
        sys_query_pair[] query_pair, String[] projection, String[] sorting)
        throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_read_first_row(table, readFromBuffer, query_pair, projection, sorting, forwardRequest);
    }

    public int db_row_count(String table_name) {
        return db_row_count(table_name, null);
    }

    public int db_row_count(String table, sys_query_pair[] query_pair) {
        dbx_ensure_open();
        return m_conn.db_row_count(table, query_pair);
    }

    /** Insert new rows using INSERT INTO, using column names and values in query_pair.
     * @return ... */
    public Integer db_append_table(String table, sys_query_pair[] query_pair)
        throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_append_table(table, query_pair);
    }

    /** Insert new rows using INSERT INTO, using column names and values in query_pair.
     * @return ... */
    public Integer db_append_table(table table, sys_query_pair[] query_pair)
        throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_append_table(table, query_pair);
    }

    public Integer db_modify_table(String table, sys_query_pair[] query_pair, sys_query_pair[] newVal)
        throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_modify_table(table, query_pair, newVal);
    }

    public Integer db_delete_table(String table, sys_query_pair[] query_pair)
        throws exc_database_error {
        dbx_ensure_open();
        return m_conn.db_delete_table(table, query_pair);
    }

    /**
     * delete rows of a table with only one pair in the 'Where" clause
     */
    public Integer db_delete_table(String table, sys_query_pair data) {
        sys_query_pair[] pairs = new sys_query_pair[1];
        pairs[0] = data;
        return db_delete_table(table, pairs);
    }

    /**
     * delete all table rows
     */
    public Integer db_delete_table(String table) {
        dbx_ensure_open();
        return m_conn.db_delete_table(table);
    }

    public void db_exec(String cmdSQL)
        throws exc_database_error {
        dbx_ensure_open();

        //System.out.println("SQL = <" + cmdSQL + ">");
        m_conn.db_execute_action_query(cmdSQL);
    }

    public res db_exec_result(String cmdSQL)
        throws exc_database_error {
        dbx_ensure_open();

        //System.out.println("\nSQL = <" + cmdSQL + ">");
        return m_conn.db_execute_query(cmdSQL, false);
    }

    /**
     * Returns the current connection to this database.
     */
    public conn db_get_connection() {
        return m_conn;
    }

    public metadata db_meta_data()
        throws exc_database_error {
        dbx_ensure_open();
        return (m_conn.db_get_meta_data());
    }

    public prepared_statement db_prepare_statement(String stmnt)
        throws exc_database_error {
        dbx_ensure_open();
        return (m_conn.db_prepare_statement(stmnt));
    }

    public prepared_statement db_prepare_statement(String table, sys_query_pair[] query_pair, String[] projection,
        String[] sorting) throws exc_database_error {
        dbx_ensure_open();
        return (m_conn.db_prepare_statement(table, query_pair, projection, sorting));
    }

    public prepared_statement db_prepare_statement(String table, sys_query_pair[] query_pair, String[] projection)
        throws exc_database_error {
        dbx_ensure_open();
        return db_prepare_statement(table, query_pair, projection, null);
    }

    public prepared_statement db_prepare_statement_row_count(String table, sys_query_pair[] query_pair)
        throws exc_database_error {
        dbx_ensure_open();
        return (m_conn.db_prepare_statement_row_count(table, query_pair));
    }

    /* error Output
     **
     */
    public static void db_error_out(exc_database_error ex) {
    	CATEGORY.logThrowable(Severity.ERROR, LOCATION, MESSAGE_CLASS, 000,
    			"DB Exception", new Object[] {  }, ex);
    }

    protected static Object dbx_hash_key(String data_source, String user, String password, String driver) {
        return data_source + "#" + user + "#" + password + "#" + driver;
    }

    protected void dbx_ensure_open()
        throws exc_database_error {
        if (m_conn == null) {
            throw new exc_database_error("db is not open");
        }
        //To check the MSSQL Connectivity
        if (m_conn.db_is_closed()){
        	m_conn = connection_factory.create_connection();
        }        
    }
}
