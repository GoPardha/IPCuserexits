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

import com.sap.spe.condmgnt.customizing.IConditionType;
import com.sap.sxe.sys.SAPTimestamp;


/** Interface of the tracerStep-object. This object stores information about the search
 * of condition records for one (pricing) step. A list of tracerSteps is stored by the
 * tracer-object.
 */
public interface ITracerStep {

    /** @return the tracer-object I am assigned to.
     */
    public ITracer getTracer();

    /** @return the description of the step.
     */
    public String getStepDescription();

    /** @return the number of the step.
     */
    public int getStepNo();

    /** @return my counter.
     */
    public int getCounter();

    /** @return the conditionType of the step.
     */
    public IConditionType getConditionType();

    /** @return the date which was used for the search of condition records.
     */
    public SAPTimestamp getConditionFindingTimestamp();

    /** @return my text.
     */
    public String getText();

    /**
     * @return a text which shows whether one of the accesses for this step was successfully
     *         returns only an value if access was clearly successfully or not, not in
     *         iterim cases
     */
    public boolean getStepWasSuccessful();

    public Iterator getAccessesIterator();

    /**
     * @return the current tracer access
     */
    public ITracerAccess getCurrentTracerAccess();

    /** @return the requirement number of the step.
     */
    public int getRequirementNo();

    /**
     * @return the message number of the step
     */
    public int getMessageNo();
}
