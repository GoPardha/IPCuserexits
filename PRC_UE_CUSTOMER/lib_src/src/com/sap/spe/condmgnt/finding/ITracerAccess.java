/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.finding;

import java.util.Iterator;


/**
 * Interface of the tracerAccess-object. This object stores information about the search
 * of condition records for an access. A list of these objects is stored by the tracerStep.
 */
public interface ITracerAccess {

    /**
     * @return the tracerStep I am assigned to.
     */
    public ITracerStep getTracerStep();

    /**
     * @return my counter.
     */
    public int getCounter();

    /**
     * @return the description of the condition table which is assigned to the access.
     */
    public String getConditionTableDescription();

    /**
     * @return my text.
     */
    public String getText();

    /**
     * @return a text which shows whether the access was successfully
     *         returns only an value if access was clearly successfully or not, not in
     *         iterim cases
     */
    public boolean wasSuccessful();

    /**
     * @return an Iterator for the attributeMaps assigned to the access.
     */
    public Iterator getAccessAttributeIterator();

    /**
     * @return the requirement number of the access.
     */
    public int getRequirementNumber();

    /**
     * @return the name of the condition table assigned to the access.
     */
    public String getConditionTableName();
}
