/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

public interface metadata {
    public databases get_databases();

    //public void close();

    /**
     * Returns the name of the database product.
     */
    public String get_db_product_name();

    /**
     * Returns column datatype info.
     */
    public type_infos get_type_info();

    /**
     * Returns the name of the current catalog(database)
     */
    public String get_current_catalog();

    public String get_keyword_name_separator_start();

    public String get_keyword_name_separator_end();

    public String get_keyword_limit_resultset(int rows);
}
