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
 * Interface of the tracer-object. The conditionFindingTracer-object stores a list
 * of tracer-objects. This object stores the information how the search of condition records
 * for a procedure has been done.
 */
public interface ITracer {

    /**
     * @return the conditionFindingTracer I am assigned to.
     */
    public IConditionFindingTracer getConditionFindingTracer();

    /**
     * @return my counter.
     */
    public int getCounter();

    /**
     * @return the time when the search of condition records was performed.
     */
    public String getTime();

    /**
     * @return an enumeration of my tracerSteps.
     */
    public Iterator getStepsIterator();

    /**
     * @return the current tracer step
     */
    public ITracerStep getCurrentTracerStep();
}
