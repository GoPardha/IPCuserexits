/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

/*
   $Workfile: sql_quoter.java $
   $Revision: 2 $
   $Author: Jcarey $
   $Date: 9/30/99 10:53p $
 */
package com.sap.sxe.db;


/**
 * An object which can quote SQL identifiers and strings
 * in the manner appropriate to a specific database.
 *
 * @author John Carey, IntelliCorp
 * @version 1.0
 */
public interface sql_quoter {

    /**
     * Quotes an SQL identifier.
     *
     * <P>The underlying database can influence exactly what characters
     * are produced to represent the input string value.</P>
     *
     * <P>Special case: converts <CODE>null</CODE> to <CODE>null</CODE>.
     * </P>
     *
     * <P>The quoting rules for identifiers may differ from
     * the quoting rules for string constants.</P>
     *
     * @see db_quote_string
     */
    public String db_quote_identifier(String str);

    /**
     * <P>Builds an SQL string constant representing
     * the specified string value.</P>
     *
     * <P>The underlying database can influence exactly what characters
     * are produced to represent the input string value.</P>
     *
     * <P>Special case: converts <CODE>null</CODE> to <CODE>null</CODE>.
     * </P>
     *
     * <P>The quoting rules for identifiers may differ from
     * the quoting rules for string constants.</P>
     *
     * @see db_quote_identifier
     */
    public String db_quote_string(String str);
}
