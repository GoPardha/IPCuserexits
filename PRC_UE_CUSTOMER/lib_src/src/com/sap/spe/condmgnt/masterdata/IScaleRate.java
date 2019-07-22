/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.masterdata;

import java.io.Serializable;

import java.util.Map;


/**
 * Interface for accessing a scale rate.
 */
public interface IScaleRate extends Serializable {

    /**
     * @return the evaluation type.
     */
    public String getEvaluationType();

    /**
     * @return the value of a usage specific field
     */
    public String getUsageFieldValue(String usageFieldName);

    public Map getUsageFields();

    public int[] getLineIds();
}
