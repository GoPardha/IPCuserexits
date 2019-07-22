/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.conversion;

import java.io.Serializable;


/**
 * Represents the result of currency conversions. This is an object rather than
 * a scalar number because multiple values have to be returned from conversion:
 * it returns the result of conversion and the exchangeRate object used for
 * conversion
 */
public interface ICurrencyConversionResult extends Serializable {

    /**
     * @return        currency value which is the result of currency conversion
     */
    public ICurrencyValue getValue();

    /**
     * @return        the local exchange rate which was used for currency conversion
     */
    public IExchangeRate getExchangeRate();
}
