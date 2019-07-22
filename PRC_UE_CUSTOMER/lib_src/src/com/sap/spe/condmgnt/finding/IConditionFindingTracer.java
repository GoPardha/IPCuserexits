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
import java.util.Vector;

/**
 * Interface of the conditionFindingTracer-object. This object stores all information about
 * the search of condition records if a trace was turned on.
 */
public interface IConditionFindingTracer {

    /**
     * @return the usage (e.g. 'A' for pricing).
     */
    public String getUsage();

    /**
     * @return the application (e.g. 'V' for sales).
     */
    public String getApplication();

    /**
     * @return an enumeration of my trace-objects.
     */
    public Iterator getTracesIterator();

    /**
     * @return the current tracer
     */
    public ITracer getCurrentTracer();

    /**
     * @return the name of the procedure used for the search of condition records.
     */
    public String getProcedureName();

    /**
     * @return the description of the procedure.
     */
    public String getProcedureDescription();
    
    
    /**
     * @return error message
     */
    public Vector getProcedureErrorMessage();
}
