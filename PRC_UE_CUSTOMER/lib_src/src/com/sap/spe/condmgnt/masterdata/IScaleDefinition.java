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
 * Interface for accessing the definition of a scale.
 */
public interface IScaleDefinition extends Serializable {

    /**
     * @return the unique identifier of the scale definition
     */
    public String getScaleId();

    /**
     * @return the scale base type.
     */
    public String getScaleBaseType();

    public Map getFixedPart();

    public String getFixedPart(String name);
    
    public IScaleLevel[] getScaleLevels();    
}
