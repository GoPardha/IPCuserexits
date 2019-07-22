/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.freegoods.masterdata;

import java.io.Serializable;

import com.sap.spe.condmgnt.masterdata.IScaleLevel;
import com.sap.spe.conversion.IPhysicalUnit;
import com.sap.spe.conversion.IPhysicalValue;


public interface IFreeGoodsScaleLevel extends IScaleLevel, Serializable {
    public IPhysicalUnit getMinimumFreeGoodsQuantityUnit();

    public IPhysicalValue getMinimumFreeGoodsQuantity();
}
