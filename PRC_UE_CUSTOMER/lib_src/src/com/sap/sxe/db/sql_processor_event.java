/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

import java.util.EventObject;


/**Indicates that the state of the sql_processor has changed.
 */
public class sql_processor_event extends EventObject {
    private String m_message = null;

    public sql_processor_event(Object source, String message) {
        super(source);
        m_message = message;
    }

    public String getMessage() {
        return m_message;
    }
}
