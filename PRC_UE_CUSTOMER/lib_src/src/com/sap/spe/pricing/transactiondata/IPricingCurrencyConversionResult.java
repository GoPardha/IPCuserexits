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
import com.sap.spe.conversion.IExchangeRate;


/** Represents the result of currency conversions in the pricing module.  This is an object rather
 * than a scalar number because multiple values have to be returned from
 * conversion: local factor, foreign factor, and the exchange rates. */
public interface IPricingCurrencyConversionResult {
    public ICurrencyValue getValue();

    public IExchangeRate getLocalExchangeRate();

    public IExchangeRate getForeignExchangeRate();

    public IExchangeRate getDirectExchangeRate();
}
