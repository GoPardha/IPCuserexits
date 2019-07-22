/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import java.sql.*;

import java.util.Properties;

import com.sap.sxe.sys.exc.*;
import com.sap.vmc.logging.Location;
import com.sap.vmc.runtime.RuntimeInformation;
import com.sap.vmc.runtime.info.DatabaseSystem;
 

public class connection_factory {

    /**
     * Microsoft SQLServer 7.0/2000
     */
    public final static String SYSTEM_SQL_SERVER = "Microsoft SQL Server";
    public final static String SYSTEM_SQL_SERVER_2 = "Microsoft SQL Server 2";
    public final static String JDBC_INET = "com.inet.tds.TdsDriver";
    public final static String JDBC_MS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static conn current_conn = null;
    
    private final static Location LOCATION = Location.getLocation(connection_factory.class);

    public final static conn get_current_connection() {
        return current_conn;
    }
    
//    public static void main(String[] args) {
//	
//		// used to test connection creation ...
//		
//		System.setProperty("vmc.runtimeInformationImpl", "com.sap.sxe.db.context");
//		System.setProperty("vmc.use.generic_configmanager", "something");
//		conn c = create_connection();
//	}
    
    public final static conn create_connection() {
        conn _conn = null;

        String type = getDatabaseSystemType();
        if ((type != null) && type.equals(SYSTEM_SQL_SERVER)) {
            DatabaseSystem databaseInfo = RuntimeInformation.getInstance().getDatabaseInfo();
            if (databaseInfo != null) {
                Properties databaseProperties = databaseInfo.getDatabaseConnectionProperties();
                if (databaseProperties != null) {
                    String server = databaseInfo.getHostname();
                    String database = databaseProperties.getProperty("database");
                    String port = databaseProperties.getProperty("port");
                    String user = databaseProperties.getProperty("user");
                    String password = databaseProperties.getProperty("password");
                    String url = getURLForInet(database, server, port);
                    _conn = create_jdbc_connection(url, user, password, JDBC_INET);
                }
            }
        }
        else if ((type != null) && type.equals(SYSTEM_SQL_SERVER_2)) {
            DatabaseSystem databaseInfo = RuntimeInformation.getInstance().getDatabaseInfo();
            if (databaseInfo != null) {
                Properties databaseProperties = databaseInfo.getDatabaseConnectionProperties();
                if (databaseProperties != null) {
                    String server = databaseInfo.getHostname();
                    String database = databaseProperties.getProperty("database");
                    String port = databaseProperties.getProperty("port");
                    String user = databaseProperties.getProperty("user");
                    String password = databaseProperties.getProperty("password");
                    String url = getURLForMSDriver(database, server, port);
                    _conn = create_jdbc_connection(url, user, password, JDBC_MS);
                }
            }
        }
        else
        {
            _conn = _newConnectionInstance("com.sap.sxe.db.imp.rfc.connRFC");
        }
        _conn.set_sql_string_factory();
        current_conn = _conn;
        return _conn;
    }

    private final static conn _newConnectionInstance(String connClassName) {
        conn connection = null;
        try {
            if (connClassName != null) {
                Class connectionClass = Class.forName(connClassName);
                Constructor connectionConstructor =
                    connectionClass.getConstructor(new Class[] { String.class, String.class, String.class, String.class });
                connection = (conn) connectionConstructor.newInstance(new Object[] { null, null, null, null });
            }
        }
        catch (databaseConnectionError d) {
            throw d;
        }
        catch (InvocationTargetException ex) {
            Throwable t = ex.getTargetException();
            if (t instanceof databaseConnectionError) {
                throw (databaseConnectionError) t;
            }
            else
            {
                throw new databaseConnectionError(t);
            }
        }
        catch (Exception e) {
            throw new databaseConnectionError(e);
        }
        return connection;
    }

    private final static conn _newJDBCConnectionInstance(String connClassName, String url, String user, String driver,
        Connection jdbcConnection) {
        conn connection = null;
        try {
            if (connClassName != null) {
                Class connectionClass = Class.forName(connClassName);
                Constructor connectionConstructor =
                    connectionClass.getConstructor(new Class[] {
                            String.class, String.class, String.class, Connection.class
                        });
                connection =
                    (conn) connectionConstructor.newInstance(new Object[] { url, user, driver, jdbcConnection });
            }
        }
        catch (databaseConnectionError d) {
            throw d;
        }
        catch (InvocationTargetException ex) {
            Throwable t = ex.getTargetException();
            if (t instanceof databaseConnectionError) {
                throw (databaseConnectionError) t;
            }
            else
            {
                throw new databaseConnectionError(t);
            }
        }
        catch (Exception e) {
            throw new databaseConnectionError(e);
        }
        return connection;
    }

    /**
     * Creates a database product specific object connection. The method is base on the
     * method java.sql.DatabaseMetaData.getDatabaseProductName().
     * Depending on the result of this method it creates an object of a database specific
     * subclass of com.sap.sxe.db.imp.jdbc.conn_jdbc.
     * If java.sql.DatabaseMetaData.getDatabaseProductName() returns a name unknown in advance,
     * it creates an object of type jdbc_connection. In order to cover database specific behaviour
     * one must subclass this class.
     */
    public final static conn create_jdbc_connection(String url, String user, String password, String driver) {
        Connection connection = null;
        String db_product_name = null;
        try {

            // Load the database driver
            LOCATION.debugT("Loading database driver: " + driver);
            Class.forName(driver);

            // DriverManager.setLogStream(System.out);
            // Attempt to connect to a driver.  Each one
            // of the registered drivers will be loaded until
            // one is found that can process this URL
            LOCATION.debugT("url = <" + url + ">");
            connection = DriverManager.getConnection(url, user, password);

            // Example for Oracle 8
            //DriverManager.getConnection ("jdbc:oracle:thin:@P33883:1521:ORCL", "sce", "sql");
        }
        catch (databaseConnectionError d) {
            throw d;
        }
        catch (Exception ex) {
            throw new databaseConnectionError(ex.getMessage() + " " + url);
        }
        try {
            DatabaseMetaData meta_data = connection.getMetaData();
            db_product_name = meta_data.getDatabaseProductName();
            LOCATION.debugT("DatabaseProductName = " + db_product_name);
        }
        catch (databaseConnectionError d) {
            throw d;
        }
        catch (Exception ex) {
            throw new databaseConnectionError(ex);
        }
        String connClassName = null;
        if (db_product_name.equals("Microsoft SQL Server")) {
            connClassName = "com.sap.sxe.db.imp.jdbc.mssql7.conn_jdbc_sql7";
        }
        else if (db_product_name.equalsIgnoreCase("EXCEL")) {
            connClassName = "com.sap.sxe.db.imp.jdbc.excel.conn_jdbc_excel";
        }
        else
        {
            connClassName = "com.sap.sxe.db.imp.jdbc.conn_jdbc";
        }
        return _newJDBCConnectionInstance(connClassName, url, user, driver, connection);
    }

    private static String getDatabaseSystemType() {
        DatabaseSystem databaseInfo = RuntimeInformation.getInstance().getDatabaseInfo();
        return databaseInfo.getSystemType();
    }

    private final static String getURLForInet(String database, String server, String port) {

        /*
           jdbc:inetdae:hostname:portnumber
           jdbc:inetdae:hostname                        -> with default port 1433
           jdbc:inetdae:hostname:portnumber?database=MyDb&language=MyLanguage
           -> with properties
           jdbc:inetdae://servername/pipe/pipename        -> with named pipes
           ie.:
           jdbc:inetdae:localhost:1433?database=SPC
           jdbc:inetdae://127.0.0.1/sql/query?database=SPC
         */
        String url = "";
        try {

            //Integer p = new Integer(port);
            url = "jdbc:inetdae7:" + server + ":" + port + "?database=" + database + "";
        }
        catch (NumberFormatException ex) {
            url = "jdbc:inetdae7://" + server + "/pipe/" + port + "?database=" + database + "";
        }
        return url;
    }

    private final static String getURLForMSDriver(String database, String server, String port) {
    	
    	// General syntax:
    	// jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
    	
    	// Example:
    	// jdbc:sqlserver://localhost;user=MyUserName;password=*****;
    	// jdbc:sqlserver://localhost:1433;databaseName=AdventureWorks;integratedSecurity=true;
    	
        return "jdbc:sqlserver://" + server + ":" + port + ";databaseName=" + database + ";";
    }
}
