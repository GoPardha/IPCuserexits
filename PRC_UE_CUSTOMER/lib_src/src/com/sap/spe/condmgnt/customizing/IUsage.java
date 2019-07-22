/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.customizing;

import java.io.Serializable;

import java.util.List;
import java.util.Map;


public interface IUsage extends Serializable {
    public String getName();

    public String getDescription();

    public int getDimenstionNumber();

    public IUsage getSuperordinateUsage();

    public IUsageField[] getFields();

    public IUsageField getField(String fieldName);

    public Map getScaleLevelFields();

    public List getScaleDefFields();
}
