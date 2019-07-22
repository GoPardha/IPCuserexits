/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

import java.util.EventListener;


/**For listening events which indicate that the state of the
 * sql_processor has changed (e.g. another command is executed).
 */
public interface sql_processor_listener extends EventListener {
    public void sql_processor_state_changed(sql_processor_event e);
}
