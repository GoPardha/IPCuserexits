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

import com.sap.sxe.sys.SAPDate;


/**
 * Represents the ratio between two currencies. For the conversion between two
 * currencies an exchange rate and a factor is needed. <br>
 * i.e. 10 CAD = 1 x 6,771 USD <br>
 * ==> exchangeRate = 6,771; conversionFactor: fromFactor = 10; toFactor = 1
 */
public interface IConversionFactor extends Serializable {

    /**
     * @return        name of exchange rate type
     */
    public String getExchangeRateTypeName();

    /**
     * @return        factor for foreign currency
     */
    public int getFromFactor();

    /**
     * @return        factor for local currency
     */
    public int getToFactor();

    /**
     * @return        alternative exchange rate type, if exchange rate is redirected
     * */
    public String getAlternativeExchangeRateTypeName();

    /**
     * @return        validity start date of exchange rate
     * */
    public SAPDate getValidityStartDate();

    /**
     * @return        from currency
     * */
    public String getFromCurrency();

    /**
     * @return        to currency
     * */
    public String getToCurrency();
}
