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
 * Interface of an exchange rate type object describing the properties of
 * exchange rate type.
 *
 * The exchange rate type used for conversion describes eg. whether the
 * conversion should be carried out using a base currency, whether it is used
 * for european monetaty union (EMU), etc.
 */
public interface IExchangeRateType extends Serializable {

    /**
     * @return exchange rate type name
     */
    public String getName();

    /**
     * @return true, if exchange rate also can be read inverted. <br>
     *         Eg. first test to get exchange rate USD - CAD, if this fails and
     *         flag is true try to read CAD - USD.
     */
    public boolean getInversionIndicator();

    /**
     * @return true, if conversion carried out using a base currency. <br>
     *         Eg. trying to convert AUD - CAD. Base currency is USD, so read
     *         exchange rates from AUD - USD and CAD - USD and prepare a
     *         resulting exchange rate from both.
     *
     */
    public boolean getBaseCurrencyIndicator();

    /**
     * @return name of base currency
     */
    public String getBaseCurrencyName();

    /**
     * @return true, if exchange rate type is used for european monetary union
     *         (EMU) conversion
     */
    public boolean isPartOfEMU();

    /**
     * @return true, if exchange rate is a fixed exchange rate. This flag has no
     *         influence to conversion but used for screening.
     */
    public boolean isRateFixed();
}
