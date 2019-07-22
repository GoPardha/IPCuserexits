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
 * Interface for accessing dimension/level pairs.
 */

// TODO: tm
public interface IScaleDimensionAmount extends Serializable {

    /**
     * @return the dimension.
     */
    public IScaleDimension getDimension();

    /**
     * @return the level.
     */
    public IScaleLevel getLevel();
}
