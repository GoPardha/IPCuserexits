/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

/*
 * @(#)statement.java        1.7 98/07/01
 */
package com.sap.sxe.db;

import com.sap.sxe.sys.exc.*;


/**
 * <P>A statement object is used for executing a static SQL statement
 * and obtaining the results produced by it.
 *
 * <P>Only one ResultSet per statement can be open at any point in
 * time. Therefore, if the reading of one ResultSet is interleaved
 * with the reading of another, each must have been generated by
 * different statements. All statement execute methods implicitly
 * close a statment's current ResultSet if an open one exists.
 *
 * @see Connection#createstatement
 * @see ResultSet
 */
public interface statement {

    /**
     * Execute a SQL statement that returns a single ResultSet.
     *
     * @param sql typically this is a static SQL SELECT statement
     * @return a ResultSet that contains the data produced by the
     * query; never null
     * @exception exc_database_error if a database-access error occurs.
     */
    res execute_query(String sql)
        throws exc_database_error;

    /**
     * Execute a SQL INSERT, UPDATE or DELETE statement. In addition,
     * SQL statements that return nothing such as SQL DDL statements
     * can be executed.
     *
     * @param sql a SQL INSERT, UPDATE or DELETE statement or a SQL
     * statement that returns nothing
     * @return either the row count for INSERT, UPDATE or DELETE or 0
     * for SQL statements that return nothing
     * @exception exc_database_error if a database-access error occurs.
     */
    int execute_update(String sql)
        throws exc_database_error;

    /**
     * In many cases, it is desirable to immediately release a
     * statements's database and JDBC resources instead of waiting for
     * this to happen when it is automatically closed; the close
     * method provides this immediate release.
     *
     * <P><B>Note:</B> A statement is automatically closed when it is
     * garbage collected. When a statement is closed, its current
     * ResultSet, if one exists, is also closed.
     *
     * @exception exc_database_error if a database-access error occurs.
     */
    void close() throws exc_database_error;

    //----------------------------------------------------------------------

    /**
     * The maxFieldSize limit (in bytes) is the maximum amount of data
     * returned for any column value; it only applies to BINARY,
     * VARBINARY, LONGVARBINARY, CHAR, VARCHAR, and LONGVARCHAR
     * columns.  If the limit is exceeded, the excess data is silently
     * discarded.
     *
     * @return the current max column size limit; zero means unlimited
     * @exception exc_database_error if a database-access error occurs.
     */

    //int get_max_field_size() throws exc_database_error;

    /**
     * The maxFieldSize limit (in bytes) is set to limit the size of
     * data that can be returned for any column value; it only applies
     * to BINARY, VARBINARY, LONGVARBINARY, CHAR, VARCHAR, and
     * LONGVARCHAR fields.  If the limit is exceeded, the excess data
     * is silently discarded. For maximum portability use values
     * greater than 256.
     *
     * @param max the new max column size limit; zero means unlimited
     * @exception exc_database_error if a database-access error occurs.
     */

    //void set_max_field_size(int max) throws exc_database_error;

    /**
     * The maxRows limit is the maximum number of rows that a
     * ResultSet can contain.  If the limit is exceeded, the excess
     * rows are silently dropped.
     *
     * @return the current max row limit; zero means unlimited
     * @exception exc_database_error if a database-access error occurs.
     */

    //int get_max_rows() throws exc_database_error;

    /**
     * The maxRows limit is set to limit the number of rows that any
     * ResultSet can contain.  If the limit is exceeded, the excess
     * rows are silently dropped.
     *
     * @param max the new max rows limit; zero means unlimited
     * @exception exc_database_error if a database-access error occurs.
     */

    //void set_max_rows(int max) throws exc_database_error;

    /**
     * If escape scanning is on (the default), the driver will do
     * escape substitution before sending the SQL to the database.
     *
     * Note: Since prepared statements have usually been parsed prior
     * to making this call, disabling escape processing for prepared
     * statements will like have no affect.
     *
     * @param enable true to enable; false to disable
     * @exception exc_database_error if a database-access error occurs.
     */

    //void set_escape_processing(boolean enable) throws exc_database_error;

    /**
     * The queryTimeout limit is the number of seconds the driver will
     * wait for a statement to execute. If the limit is exceeded, a
     * exc_database_error is thrown.
     *
     * @return the current query timeout limit in seconds; zero means unlimited
     * @exception exc_database_error if a database-access error occurs.
     */

    //int get_query_timeout() throws exc_database_error;

    /**
     * The queryTimeout limit is the number of seconds the driver will
     * wait for a statement to execute. If the limit is exceeded, a
     * exc_database_error is thrown.
     *
     * @param seconds the new query timeout limit in seconds; zero means unlimited
     * @exception exc_database_error if a database-access error occurs.
     */

    //void set_query_timeout(int seconds) throws exc_database_error;

    /**
     * Cancel can be used by one thread to cancel a statement that
     * is being executed by another thread.
     *
     * @exception exc_database_error if a database-access error occurs.
     */

    //void cancel() throws exc_database_error;

    /**
     * The first warning reported by calls on this statement is
     * returned.  A Statment's execute methods clear its SQLWarning
     * chain. Subsequent statement warnings will be chained to this
     * SQLWarning.
     *
     * <p>The warning chain is automatically cleared each time
     * a statement is (re)executed.
     *
     * <P><B>Note:</B> If you are processing a ResultSet then any
     * warnings associated with ResultSet reads will be chained on the
     * ResultSet object.
     *
     * @return the first SQLWarning or null
     * @exception exc_database_error if a database-access error occurs.
     */

    //exc_database_warning get_warnings() throws exc_database_error;

    /**
     * After this call, getWarnings returns null until a new warning is
     * reported for this statement.
     *
     * @exception exc_database_error if a database-access error occurs.
     */

    //void clear_warnings() throws exc_database_error;

    /**
     * setCursorname defines the SQL cursor name that will be used by
     * subsequent statement execute methods. This name can then be
     * used in SQL positioned update/delete statements to identify the
     * current row in the ResultSet generated by this statement.  If
     * the database doesn't support positioned update/delete, this
     * method is a noop.
     *
     * <P><B>Note:</B> By definition, positioned update/delete
     * execution must be done by a different statement than the one
     * which generated the ResultSet being used for positioning. Also,
     * cursor names must be unique within a Connection.
     *
     * @param name the new cursor name.
     * @exception exc_database_error if a database-access error occurs.
     */

    //void set_cursor_name(String name) throws exc_database_error;
    //----------------------- Multiple Results --------------------------

    /**
     * Execute a SQL statement that may return multiple results.
     * Under some (uncommon) situations a single SQL statement may return
     * multiple result sets and/or update counts.  Normally you can ignore
     * this, unless you're executing a stored procedure that you know may
     * return multiple results, or unless you're dynamically executing an
     * unknown SQL string.  The "execute", "getMoreResults", "getResultSet"
     * and "getUpdateCount" methods let you navigate through multiple results.
     *
     * The "execute" method executes a SQL statement and indicates the
     * form of the first result.  You can then use getResultSet or
     * getUpdateCount to retrieve the result, and getMoreResults to
     * move to any subsequent result(s).
     *
     * @param sql any SQL statement
     * @return true if the next result is a ResultSet; false if it is
     * an update count or there are no more results
     * @exception exc_database_error if a database-access error occurs.
     * @see #getResultSet
     * @see #getUpdateCount
     * @see #getMoreResults
     */

    //boolean execute(String sql) throws exc_database_error;

    /**
     *  getResultSet returns the current result as a ResultSet.  It
     *  should only be called once per result.
     *
     * @return the current result as a ResultSet; null if the result
     * is an update count or there are no more results
     * @exception exc_database_error if a database-access error occurs.
     * @see #execute
     */

    //res get_result_set() throws exc_database_error; 

    /**
     *  getUpdateCount returns the current result as an update count;
     *  if the result is a ResultSet or there are no more results, -1
     *  is returned.  It should only be called once per result.
     *
     * @return the current result as an update count; -1 if it is a
     * ResultSet or there are no more results
     * @exception exc_database_error if a database-access error occurs.
     * @see #execute
     */

    //int get_update_count() throws exc_database_error; 

    /**
     * getMoreResults moves to a statement's next result.  It returns true if
     * this result is a ResultSet.  getMoreResults also implicitly
     * closes any current ResultSet obtained with getResultSet.
     *
     * There are no more results when (!getMoreResults() &&
     * (getUpdateCount() == -1)
     *
     * @return true if the next result is a ResultSet; false if it is
     * an update count or there are no more results
     * @exception exc_database_error if a database-access error occurs.
     * @see #execute
     */

    //boolean get_more_results() throws exc_database_error; 
}
