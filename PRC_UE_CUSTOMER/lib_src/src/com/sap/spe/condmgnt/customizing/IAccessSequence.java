/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.customizing;

import java.io.Serializable;
import java.util.Vector;


/**
 * Interface of an access sequence. An access sequence contains one or more access-objects and defines a
 * search strategy for condition records.
 */
public interface IAccessSequence extends Serializable {

    /**
     * @return the application.
     * Corresponds to the database field <code>/SAPCND/T682-KAPPL</code>.
     */
    public String getApplication();

    /**
     * @return the usage.
     * Corresponds to the database field <code>/SAPCND/T682-KVEWE</code>.
     */
    public String getUsage();

    /**
     * @return the name of the access sequence.
     * Corresponds to the database field <code>/SAPCND/T682-KOZGF</code>.
     */
    public String getName();

    /**
     * @return an array of accesses.
     */
    public IAccess[] getAccesses();

    /**
     * @return my description for the @param language.
     */
    public String getDescription();
    
    /**
     * @return my error message.
     */
    public Vector getErrorMessage();
}
