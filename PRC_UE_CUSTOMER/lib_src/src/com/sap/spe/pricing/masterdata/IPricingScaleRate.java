/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.masterdata;

import com.sap.spe.condmgnt.masterdata.IScaleRate;
import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.conversion.IPhysicalValue;


/**
 * Interface for accessing a pricing scale rate.
 */
public interface IPricingScaleRate extends IScaleRate {
    public char getCalculationType();

    /**
     * @return the rate value.
     */
    public ICurrencyValue getConditionRate();

    /**
     * @return the condition pricing unit.
     */
    public IPhysicalValue getPricingUnit();
}
