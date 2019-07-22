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

import java.math.BigDecimal;

import com.sap.sxe.sys.SAPDate;
import com.sap.sxe.sys.SAPLocale;


/**
 * The interface of an exchange rate object which is used to define how a
 * currency conversion is performed.
 */
public interface IExchangeRate extends Serializable {

    /**
     * @return my source currency name. (R/3-field: TCURR-FCURR)
     */
    public String getFromCurrency();

    /**
     * @return my target currency name. (R/3-field: TCURR-TCURR)
     */
    public String getToCurrency();

    /**
     * @return the start date from which the exchange rate is valid. (R/3-field:
     *         TCURR-GDATU)
     */
    public SAPDate getDate();

    /**
     * @return my exchange rate read from DB
     */
    public BigDecimal getRate();

    /**
     * @return the 'from factor' used for currency conversion between source and
     *         target currency. (R/3-field: TCURF-FFACT)
     */
    public int getFromFactor();

    /**
     * @return the 'to factor' used for currency conversion between source and
     *         target currency. (R/3-field: TCURF-TFACT)
     */
    public int getToFactor();

    /**
     * @return my external respresentation for displaying it
     */
    public String getExternalRepresentation();

    /**
     * @return the name of the exchange rate type. (R/3-field: TCURR-KURST)
     */
    public String getExchangeRateTypeName();

    /**
     * @return the exchange rate type. (R/3-table: TCURT)
     */
    public IExchangeRateType getExchangeRateType();

    // TODO: to be discussed, does not reflect a exchange rate which is stored in the database (TCURR)
    // result of conversion    

    /**
     * @return my resulting rate as a BigDecimal that is rounded to 5 decimals.
     *         (R/3-field: TCURR-UKURS)
     */
    public BigDecimal getResultingRate5Decimals();

    /**
     * @return my resulting rate as a String (R/3-field: TCURR-UKURS)
     */
    public String getResultingRate5DecimalsAsString();

    /**
     * @param locale locale of user for locale-dependent representation and also
     *        as language of error messages which might occur
     *
     * @return my resulting rate in a locale-dependent representation (e.g.
     *         number of decimals, separator).
     */
    public String getResultingRate5DecimalsAsString(SAPLocale locale);

    /**
     * @return my resulting rate as a BigDecimal that is rounded to 11 decimals.
     *         (R/3-field: TCURR-UKURS)
     */
    public BigDecimal getResultingRate11Decimals();
}
