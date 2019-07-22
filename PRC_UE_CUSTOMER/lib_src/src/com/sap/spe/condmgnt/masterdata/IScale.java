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


/**
 * Interface for accessing scale data. The scale data is linked to an condition-record object.
 */
public interface IScale extends Serializable {

    /**
     * @return an array of all scale dimension object related to the scale.
     */
    public IScaleDimension[] getScaleDimensions();

    /**
     * @return an array of all scale levels for dimension @param scaleDimension.
     */
    public IScaleLevel[] getScaleLevels();

    public IScaleLevel[] getScaleLevels(IScaleDimension scaleDimension);

    /**
     * @return a new dimension/level pair.
     * @param scaleDimension the requested dimension object.
     * @param scaleLevel the scale level.
     */
    public IScaleDimensionAmount newScaleDimensionAmount(IScaleDimension scaleDimension, IScaleLevel scaleLevel);

    /**
     * @return the scale base type for dimension @param scaleDimension.
     */
    public String getScaleBaseType();

    public String getScaleBaseType(IScaleDimension scaleDimension);
}
