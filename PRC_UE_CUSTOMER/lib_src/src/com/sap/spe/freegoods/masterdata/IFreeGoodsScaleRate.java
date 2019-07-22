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

import com.sap.spe.condmgnt.masterdata.IScaleRate;
import com.sap.spe.conversion.IPhysicalUnit;
import com.sap.spe.conversion.IPhysicalValue;


public interface IFreeGoodsScaleRate extends IScaleRate, Serializable {

    /**
     * Return the quantity of additional free goods that will be given.
     */
    public IPhysicalValue getAdditionalQuantity();

    /**
     * Return the additional productID that will be given as free goods.
     */
    public String getAdditionalProductID();

    /**
     * Return the calculation type that will be used for the calculation of free
     * goods quantity. Predefined calculation types in R/3 for example are 1.
     * Pro rata 2. Unit reference 3. Whole units
     */
    public int getCalculationType();

    /**
     * Whether the free goods item quantity will affect the parent item quantity
     * or not.
     */
    public char getInclusiveExclusiveIndicator();

    public IPhysicalValue getFreeGoodsQuantity();

    public IPhysicalUnit getFreeGoodsQuantityUnit();

    public IPhysicalUnit getAdditionalQuantityUnit();

    /**
     * Return the minimum quantity after which the free goods scale will be
     * applied.
     */

    // TODO required?
    // public IQuantityValue getMinimumFreegoodsQuantity();
}
