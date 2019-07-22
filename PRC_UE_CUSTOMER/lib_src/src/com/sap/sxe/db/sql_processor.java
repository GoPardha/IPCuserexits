/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

/*
   $Workfile: createTables.java $
   $Revision: 2 $
   $Author: Fm $
   $Date: 10/08/98 9:00a $
 */
package com.sap.sxe.db;

import java.io.*;

import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;

import com.sap.sxe.sys.exc.*;


public final class sql_processor {
    private final static Vector C_EVENTLISTENER = new Vector();

    private final static BufferedReader getReader(String fileName) {
        try {  //mathias hoenig for the creation of tables from the administrator
            InputStream ioStream = ClassLoader.getSystemResourceAsStream(fileName);
            if (ioStream != null) {  // 290300-ak
                BufferedReader reader = new BufferedReader(new InputStreamReader(ioStream));
                return reader;
            }
        }
        catch (Exception e) {

            //e.printStackTrace(); tm 2 Nov 1999 This produces nasty output on the screen if the scripts are not in the classpath
            //                                   We have a second chance anyway.
        }

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            return reader;
        }
        catch (Exception e) {

            // TODO: exception handling
            //e.printStackTrace(log_api.log_get_output_stream());
        }

        throw new IllegalArgumentException("The file '" + fileName + "' does not exist.");
    }

    /**Returns the number of commands which will be executed by sql_script.
     */
    public final static int getCommandsCount(db my_db, String sql_script) {
        int count = 0;

        conn current_connection = my_db.db_get_connection();
        schema current_schema = current_connection.db_get_current_schema();
        tables current_tables = null;
        if (current_schema != null) {
            current_tables = current_schema.get_tables();
        }
        try {
            BufferedReader buf_reader = getReader(sql_script);
            String line = null;

            while ((line = buf_reader.readLine()) != null) {
                if (!line.equals("")) {
                    if (line.equals("/") | line.equalsIgnoreCase("go")) {
                        count++;
                    }
                }
            }
            buf_reader.close();
        }
        catch (IOException e) {

            // TODO: exception handling
            //System.err.println(e);
            //log_api.log_write_dump("sql_processor", "getCommandsCount", e);
        }
        catch (exc_database_error e) {

            // TODO: exception handling
            //log_api.log_write_dump("sql_processor", "getCommandsCount", e);
        }
        return count;
    }

    /**
     * Parses the file sql_script and creates the tables defined there.
     */
    public final static void start(db my_db, String sql_script) {
        StringBuffer sqlCommand = new StringBuffer();
        type_infos tis = my_db.db_meta_data().get_type_info();
        conn current_connection = my_db.db_get_connection();
        schema current_schema = current_connection.db_get_current_schema();
        tables current_tables = null;
        if (current_schema != null) {
            current_tables = current_schema.get_tables();
        }
        try {
            BufferedReader buf_reader = getReader(sql_script);
            String line = null;
            while ((line = buf_reader.readLine()) != null) {
                if (!line.equals("")) {
                    if ((line.charAt(0) == '-') | (line.charAt(0) == '#')) {

                        //comment line, ignore it
                    }
                    else if (line.equals("/") | line.equalsIgnoreCase("go")) {
                        String tableName = createTable(my_db, current_tables, sqlCommand.toString());
                        fireEvent(new sql_processor_event(sql_processor.class, tableName));
                        sqlCommand = new StringBuffer();
                        System.out.print(".");
                    }
                    else if (line.equals("(") | line.equals(")")) {
                        sqlCommand.append(line);
                    }
                    else
                    {
                        sqlCommand.append(convertToLocalDatatypes(tis, line));
                    }
                }
            }
            buf_reader.close();
        }
        catch (IOException e) {

            // TODO: exception handling
            //System.err.println(e);
            //log_api.log_write_dump("sql_processor", "start", e);
        }
        catch (Exception e) {

            // TODO: exception handling
            //log_api.log_write_dump("sql_processor", "start", e);
            throw new exc_internal_error("Syntax error in database script file! " + e);
        }
    }

    /**
     * Creates a table by executing the SQL statement passed as
     * parameter.
     * If the table already exists, the statement is ignored.
     * tm 10-Dec-1999 let the method process drop statements.
     * @return the name of the table created or null if no table was created
     */
    private final static String createTable(db my_db, tables current_tables, String sqlCommand)
        throws Exception {
        StringTokenizer tokenizer = new StringTokenizer(sqlCommand, "\t\n\r\f ()\",;");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.equalsIgnoreCase("create")) {
                String table = tokenizer.nextToken();
                if (table.equalsIgnoreCase("table")) {
                    String tableName = tokenizer.nextToken();
                    if (current_tables != null) {

                        //does the table already exist?
                        if (current_tables.get_table(tableName) == null) {
                            my_db.db_exec(sqlCommand);  //my_db.db_exec(sqlCommand.toString());                            
                            return tableName;
                        }
                        else
                        {
                            return tableName;
                        }
                    }  //(current_tables != null)
                }
                else if (table.equalsIgnoreCase("index")) {  //(table.equalsIgnoreCase("table"))
                    my_db.db_exec(sqlCommand);  // my_db.db_exec(sqlCommand.toString());
                    return sqlCommand;  // sqlCommand.toString();
                }
                else if (table.equalsIgnoreCase("view")) {  //(table.equalsIgnoreCase("table"))
                    my_db.db_exec(sqlCommand);  // my_db.db_exec(sqlCommand.toString());
                    return sqlCommand;  // sqlCommand.toString();
                }
            }
            else if (token.equalsIgnoreCase("drop")) {
                String table = tokenizer.nextToken();
                if (table.equalsIgnoreCase("table")) {
                    String tableName = tokenizer.nextToken();
                    if (current_tables != null) {

                        //does the table already exist?
                        if (current_tables.get_table(tableName) != null) {
                            current_tables.drop_table(tableName);
                            return tableName;
                        }
                        else {
                            return "";
                        }
                    }  //(current_tables != null)
                }
                else if (table.equalsIgnoreCase("index")) {  //(table.equalsIgnoreCase("table"))
                    my_db.db_exec(sqlCommand);  // my_db.db_exec(sqlCommand.toString());
                    return sqlCommand;  // sqlCommand.toString();
                }
            }
            else if (token.equalsIgnoreCase("insert")) {
                my_db.db_exec(sqlCommand);  // my_db.db_exec(sqlCommand.toString());
                return sqlCommand;  // sqlCommand.toString();
            }
        }
        return null;
    }

    public final static String convertToLocalDatatypes(type_infos tis, String odbcLine) {
        try {
            StringBuffer line = new StringBuffer();
            StringTokenizer stok = new StringTokenizer(odbcLine, "\t\n\r\f (),;");
            String firstToken = null;

            //String secondToken = null;
            firstToken = stok.nextToken();
            if (firstToken.equalsIgnoreCase("constraint") | firstToken.equalsIgnoreCase("drop")
                    | firstToken.equalsIgnoreCase("alter") | firstToken.equalsIgnoreCase("add")
                    | firstToken.equalsIgnoreCase("create") | firstToken.equalsIgnoreCase("as")
                    | firstToken.equalsIgnoreCase("from") | firstToken.equalsIgnoreCase("insert")
                    | firstToken.equalsIgnoreCase("references")) {
                return odbcLine;
            }
            else {
                if (!stok.hasMoreElements()) {
                    return odbcLine;
                }

                String type_string = stok.nextToken();  //this is the datatype

                //the type double precision needs particular treatment since it
                //consists of two words.
                if (type_string.equalsIgnoreCase("double")) {
                    if (stok.nextToken().equalsIgnoreCase("precision")) {
                        type_string = "DOUBLE PRECISION";
                    }
                }
                int type = type_info.map_type_name(type_string);
                if (type == 0) {
                    return odbcLine;
                }
                type_info ti = tis.get_type_info(type);
                if (ti != null) {  //type found in collection of supported types
                    line.append(firstToken);
                    line.append(" ");
                    String local_type_name = ti.get_type_name();
                    line.append(local_type_name);
                    if (local_type_name.equalsIgnoreCase("char() byte")
                            || local_type_name.equalsIgnoreCase("varchar() byte")) {

                        //		int lngth = line.length() - 6;
                        int i = 0;
                        while (line.charAt(i) != ')') {
                            ++i;
                        }
                        int lngth = i;
                        int index = 0;
                        while (odbcLine.charAt(index) != '(') {
                            ++index;
                        }

                        //	int index = odbcLine.lastIndexOf(type_string) + type_string.length() + 1;
                        StringBuffer sub = new StringBuffer();
                        while (odbcLine.charAt(index + 1) != ')') {
                            sub.append(odbcLine.substring(index + 1, index + 2));
                            ++index;
                        }
                        line.insert(lngth, sub);
                        StringBuffer sbr = new StringBuffer();
                        index += 2;
                        while (index != odbcLine.length()) {
                            sbr.append(odbcLine.charAt(index));
                            ++index;
                        }
                        line.append(sbr);

                        //	line.append(odbcLine.substring(odbcLine.lastIndexOf(type_string) + type_string.length() + 3));
                    }
                    else {

                        //append the rest of the line
                        //int len = odbcLine.lastIndexOf(type_string);
                        line.append(odbcLine.substring(odbcLine.lastIndexOf(type_string) + type_string.length()));
                    }
                    return line.toString();
                }
                else {  //type not supported, now we can hope the the db system maps the type correctly (ORACLE does)
                    return odbcLine;
                }
            }
        }
        catch (Exception e) {
            throw new exc_internal_error("Syntax error in database script file! " + e);
        }
    }

    /**
     * Maps the SQL-92 datatypes used in the SQL script to the database specific types.
     * When passing DDL(Data Definition Language) statements to the database this mapping
     * cannot be performed by the driver.
     */

    /*
       public  final static String convertToLocalDatatypes(type_infos tis, String odbcLine)
       {
               try
               {
                       StringBuffer line = new StringBuffer();
                       StringTokenizer stok = new StringTokenizer(odbcLine, "\t\n\r\f (),;");
                       String firstToken = null;
                       //String secondToken = null;
                       firstToken = stok.nextToken();
                       if (firstToken.equalsIgnoreCase("constraint") |
                               firstToken.equalsIgnoreCase("drop")       |
                               firstToken.equalsIgnoreCase("alter")      |
                               firstToken.equalsIgnoreCase("add")        |
                               firstToken.equalsIgnoreCase("create")     |
                               firstToken.equalsIgnoreCase("insert")     |
                               firstToken.equalsIgnoreCase("references") |
                               !firstToken.equalsIgnoreCase("char")      |
                               !firstToken.equalsIgnoreCase("nchar")     |
                               !firstToken.equalsIgnoreCase("int")       |
                               !firstToken.equalsIgnoreCase("smallint")  |
                               !firstToken.equalsIgnoreCase("date")      |
                               !firstToken.equalsIgnoreCase("real")      |
                               !firstToken.equalsIgnoreCase("double")    |
                               !firstToken.equalsIgnoreCase("float")     |
                               CHARACTER, CHAR, CHARACTER VARYING ,CHAR VARYING ,VARCHAR
                                                                                                                                         NATIONAL CHARACTER [ <left paren> <length> <right paren> ]
       | NATIONAL CHAR [ <left paren> <length> <right paren> ]
       | NCHAR [ <left paren> <length> <right paren> ]
       | NATIONAL CHARACTER VARYING <left paren> <length> <right paren>
       | NATIONAL CHAR VARYING <left paren> <length> <right paren>
       | NCHAR VARYING <left paren> <length> <right paren>
       BIT [ <left paren> <length> <right paren> ]
           | BIT VARYING <left paren> <length> <right paren>
       NUMERIC [ <left paren> <precision> [ <comma> <scale> ] <right paren> ]
           | DECIMAL [ <left paren> <precision> [ <comma> <scale> ] <right paren> ]
           | DEC [ <left paren> <precision> [ <comma> <scale> ] <right paren> ]
           | INTEGER
           | INT
           | SMALLINT
       FLOAT [ <left paren> <precision> <right paren> ]
           | REAL
           | DOUBLE PRECISION
       DATE
           | TIME [ <left paren> <time precision> <right paren> ]
                 [ WITH TIME ZONE ]
           | TIMESTAMP [ <left paren> <timestamp precision> <right paren> ]
                 [ WITH TIME ZONE ]
                                                                )
                                       return odbcLine;
                               else
                               {
                                       String type_string = stok.nextToken(); //this is the datatype
                                       //the type double precision needs particular treatment since it
                                       //consists of two words.
                                       if(type_string.equalsIgnoreCase("double"))
                                               if(stok.nextToken().equalsIgnoreCase("precision"))
                                                       type_string = "DOUBLE PRECISION";
                                       int type = type_info.map_type_name(type_string);
                                       type_info ti = tis.get_type_info(type);
                                       if (ti != null)
                                       { //type found in collection of supported types
                                               line.append(firstToken);
                                               line.append(" ");
                                               String local_type_name = ti.get_type_name();
                                               line.append(local_type_name);
                                               if(local_type_name.equalsIgnoreCase("char() byte") ||local_type_name.equalsIgnoreCase("varchar() byte"))
                                               {
                                       //                int lngth = line.length() - 6;
                                                       int i = 0;
                                                       while(line.charAt(i) != ')')
                                                               ++i;
                                                       int lngth = i;
                                                       int index = 0;
                                                       while(odbcLine.charAt(index) != '(')
                                                               ++index;
                                               //        int index = odbcLine.lastIndexOf(type_string) + type_string.length() + 1;
                                                       StringBuffer sub = new StringBuffer();
                                                       while(odbcLine.charAt(index+1) != ')')
                                                       {
                                                               sub.append(odbcLine.substring(index+1,index + 2));
                                                               ++index;
                                                       }
                                                       line.insert(lngth,sub);
                                                        StringBuffer sbr = new StringBuffer();
                                                        index+=2;
                                                        while(index != odbcLine.length()){
                                                                sbr.append(odbcLine.charAt(index));
                                                                ++index;
                                                        }
                                                   line.append(sbr);
                                               //        line.append(odbcLine.substring(odbcLine.lastIndexOf(type_string) + type_string.length() + 3));
                                               }
                                               else
                                               {
                                                       //append the rest of the line
                                                       int len = odbcLine.lastIndexOf(type_string);
                                                       line.append(odbcLine.substring(odbcLine.lastIndexOf(type_string) + type_string.length()));
                                               }
                                               return line.toString();
                                       }
                                       else {//type not supported, now we can hope the the db system maps the type correctly (ORACLE does)
                                               return odbcLine;
                                       }
                               }
                       }
                       catch (Exception e)
                       {
                               throw new exc_internal_error(ExceptionTypes.ERROR, "Syntax error in database script file! " + e);
                       }
               }
     */

    /**Adds an event listener.
     */
    public final static void addEventListener(sql_processor_listener l) {
        if (!C_EVENTLISTENER.contains(l)) {
            C_EVENTLISTENER.addElement(l);
        }
    }

    /**Removes an event listener.
     */
    public final static void removeEventListener(sql_processor_listener l) {
        if (C_EVENTLISTENER.contains(l)) {
            C_EVENTLISTENER.removeElement(l);
        }
    }

    /**Fires event to all my listeners.
     */
    private final static void fireEvent(sql_processor_event e) {
        Enumeration en = C_EVENTLISTENER.elements();
        while (en.hasMoreElements()) {
            ((sql_processor_listener) en.nextElement()).sql_processor_state_changed(e);
        }
    }
}
