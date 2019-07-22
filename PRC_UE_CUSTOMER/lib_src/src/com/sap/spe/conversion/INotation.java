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
 * Resulting quotation for an exchange rate. Exchange rates can be given in
 * direct or indirect quotation. With this interface you get the wanted
 * quotation for an exchange rate independend from database entries for exchange
 * rate.
 *
 * For direct quotation, exchange rate is given in following way: 1 USD = 1,4851
 * CAD (for 1 USD I will get 1,4851 CAD)
 *
 * For indirect quotation, exchange rate is given in following way: 0,6733 USD =
 * 1 CAD (1 CAD costs 0,6733 USD)
 */
public interface INotation extends Serializable {

    /**
     * @return my source currency name. (R/3-field: TCURN-FCURR)
     */
    public String getFromCurrency();

    /**
     * @return my target currency name. (R/3-field: TCURN-TCURR)
     */
    public String getToCurrency();

    /**
     * @return the start date from which the quotation is valid. (R/3-field:
     *         TCURN-GDATU)
     */
    public SAPDate getDate();

    /**
     * @return wanted quotation for an exchange rate. Quotation is 1 for direct
     *         quoted exchange rates and 2 for indirect quoted exchange rates
     */
    public String getNotation();

    /**
     * @return true, if exchange rate should is direct quoted
     */
    public boolean isDirectNotation();

    /**
     * @return true, if exchange rate should be indirect quoted
     */
    public boolean isIndirectNotation();
}
