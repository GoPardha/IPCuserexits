/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.conversion;

import com.sap.sxe.sys.SAPDate;


public interface IExpiringCurrency extends ICurrencyUnit {

    /**
     * @return the new currency unit of an expiring currency independent of
     *         scenario or date
     */
    public ICurrencyUnit getSubsequentCurrency();

    public SAPDate getErrorDate(String objectTypeForExpiringCurrencies);

    public SAPDate getWarnDate(String objectTypeForExpiringCurrencies);

    /**
     * @param objectTypeOfExpiringCurrency defines the bussines object where
     *        currency is used (scenario)
     * @param date date which should be tested against the expiring dates of the
     *        currency
     * @return a W(arning) or E(rror) if currency should or can not longer used,
     *         depending on scenario (object type) and date if currency is still
     *         valid method returns null
     */
    public String dateBelongsToWarningIntervall(String objectTypeForExpiringCurrencies, SAPDate date);

    public static class CheckResult {
        public static final String ERROR = "E";
        public static final String WARNING = "W";
    }
}
