/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.finding;

import java.io.Serializable;


public interface IRelevantAttributes extends Serializable {

    /**
     * Get the relevant Attributes for a procedure
     * @return string array of relevant attributes
     */
    public String[] getRelevantAttributes();

    /**
     * Get the timestamps for condition access
     * @return string array of timestamps
     */
    public String[] getConditionAccessTimestampNames();
}
