/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.finding;

import com.sap.spe.condmgnt.customizing.IAccess;
import com.sap.spe.condmgnt.customizing.IStep;
import com.sap.spe.condmgnt.masterdata.IConditionRecord;
import com.sap.sxe.sys.SAPTimestamp;


/**
 * Interface for the condition-object. For pricing this object corresponds to an
 * A***-record in R/3.
 */
public interface ICondition {

    /**
     * Gets the access object for which the condition has been found
     * @return access object
     */
    public IAccess getAccess();

    /**
     * Gets the step object for which the record has been found
     * @return step object.
     */
    public IStep getStep();

    /**
     * Gets the timestamp which was used for the search of this condition
     * @return timestamp
     */
    public SAPTimestamp getConditionFindingTimestamp();

    /**
     * Gets the condition record this condition was created from
     * @return condition record or <code>null</null> if the condition was not created
     * by condition record, e.g. manual entry
     */
    public IConditionRecord getConditionRecord();

    /** @return the condition counter (R/3-field KOMK-ZAEHK). */
    public int getCounter();

    public int getStepNumber();
}
