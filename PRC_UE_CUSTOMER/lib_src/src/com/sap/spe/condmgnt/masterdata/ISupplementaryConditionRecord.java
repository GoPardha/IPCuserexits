/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.masterdata;


/**
 * Interface for the conditionRecord-object. This object is a part of the result of
 * the search performed by the condition technique.
 */
public interface ISupplementaryConditionRecord extends IConditionRecord {
    public int getSupplementaryConditionId();

    public IConditionRecord getMainConditionRecord();
}
