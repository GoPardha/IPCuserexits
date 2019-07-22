/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata;

import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.conversion.IDimensionalValue;
import com.sap.spe.conversion.IPhysicalValue;


/**
 * The IExternalDataSource is an object which consists out of
 * the elements conditionRate, pricingUnit and conditionBase in order
 * to handle external data sources.
 */
public interface IExternalDataSource {

    /**
     * Returns the currency value of the condition rate.
     */
    public ICurrencyValue getConditionRate();

    /**
     * Returns the pricing unit of the externalDataSource object.
     */
    public IPhysicalValue getPricingUnit();

    /**
     * Returns the condition base of the externalDataSource object.
     */
    public IDimensionalValue getConditionBase();

    public char getCalculationType();
}
