/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

public class parameter_query_pair extends sys_query_pair {
    public parameter_query_pair(String name) {
        super(name, new parameter_variant("?"), EQUAL);
    }

    public parameter_query_pair(String name, String operator) {
        super(name, new parameter_variant("?"), operator);
    }
}
