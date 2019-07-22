/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.conversion;

import java.math.BigDecimal;

import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.spe.conversion.exc.CurrencyConversionException;
import com.sap.sxe.sys.SAPDate;


/**
 * Represents an amount of currency, eg. 5.0 USD or 3,50 DEM, etc.
 */
public interface ICurrencyValue extends IDimensionalValue {

    /**
     * Converts me to local currency.
     *
     * @param conversionDate the date used to determine the exchange rate
     * @param localCurrency the output currency unit
     * @param exchangeRateTypeName name of exchange rate type used to get the
     *        valid exchange rate
     * @param externalRate external rate, which should be used for conversion
     *
     * @return a new CurrencyValue object whose value is the equivalent of my
     *         value, but in currency unit "localCurrency" and the exchange rate
     *         used for conversion.
     *
     * @exception ConversionInconsistentDBException thrown, if foreign or
     *            localCurrency is not found on database
     * @exception CurrencyConversionException thrown, if exchange rate or
     *            exchange rate factors are not found
     */
    public ICurrencyConversionResult convertToLocalCurrency(SAPDate conversionDate, ICurrencyUnit localCurrency,
        String exchangeRateTypeName, BigDecimal externalRate)
        throws ConversionMissingDataException, CurrencyConversionException;

    /**
     * Converts me to local currency. The external rate defaults to 0, and the
     * exRateTypeName defaults to M.
     *
     * @param conversionDate the date used to determine the exchange rate
     * @param localCurrency the output currency unit
     *
     * @return a new CurrencyValue object whose value is the equivalent of my
     *         value, but in currency unit "localCurrency".
     * @exception ConversionInconsistentDBException thrown, if foreign or
     *            localCurrency is not found on database
     * @exception CurrencyConversionException thrown, if exchange rate or
     *            exchange rate factors are not found
     */
    public ICurrencyConversionResult convertToLocalCurrency(SAPDate conversionDate, ICurrencyUnit localCurrency)
        throws ConversionMissingDataException, CurrencyConversionException;

    /**
     * Converts me to local currency. The external rate defaults to 0.
     *
     * @param conversionDate the date used to determine the exchange rate
     * @param localCurrency the output currency unit
     * @param exchangeRateTypeName name of exchange rate type used to get the
     *        valid exchange rate
     *
     * @return a new CurrencyValue object whose value is the equivalent of my
     *         value, but in currency unit "localCurrency".
     *
     * @exception ConversionInconsistentDBException thrown, if foreign or
     *            localCurrency is not found on database
     * @exception CurrencyConversionException thrown, if exchange rate or
     *            exchange rate factors are not found
     */
    public ICurrencyConversionResult convertToLocalCurrency(SAPDate conversionDate, ICurrencyUnit localCurrency,
        String exchangeRateTypeName)
        throws ConversionMissingDataException, CurrencyConversionException;

    /**
     * Convert me to "foreignCurrency".
     *
     * @param conversionDate the date used to determine the exchange rate
     * @param foreignCurrency the output currency unit
     * @param exchangeRateTypeName name of exchange rate type used to get the
     *        valid exchange rate
     * @param externalRate external rate, which should be used for conversion
     *
     * @return a new CurrencyValue object whose value is the equivalent of my
     *         value, but in currency unit "foreignCurrency".
     *
     * @exception ConversionInconsistentDBException thrown, if foreign or
     *            localCurrency is not found on database
     * @exception CurrencyConversionException thrown, if exchange rate or
     *            exchange rate factors are not found
     */
    public ICurrencyConversionResult convertToForeignCurrency(SAPDate conversionDate, ICurrencyUnit foreignCurrency,
        String exchangeRateTypeName, BigDecimal externalRate)
        throws ConversionMissingDataException, CurrencyConversionException;

    /**
     * Convert me to "foreignCurrency". The exRateTypeName defaults to M. The
     * rate defaults to 0.
     *
     * @param conversionDate the date used to determine the exchange rate
     * @param foreignCurrency the output currency unit
     *
     * @return a new CurrencyValue object whose value is the equivalent of my
     *         value, but in currency unit "foreignCurrency".
     *
     * @exception ConversionInconsistentDBException thrown, if foreign or
     *            localCurrency is not found on database
     * @exception CurrencyConversionException thrown, if exchange rate or
     *            exchange rate factors are not found
     */
    public ICurrencyConversionResult convertToForeignCurrency(SAPDate conversionDate, ICurrencyUnit foreignCurrency)
        throws ConversionMissingDataException, CurrencyConversionException;

    /**
     * Convert me to "foreignCurrency". The rate defaults to 0.
     *
     * @param conversionDate the date used to determine the exchange rate
     * @param foreignCurrency the output currency unit
     * @param exchangeRateTypeName name of exchange rate type used to get the
     *        valid exchange rate
     *
     * @return a new CurrencyValue object whose value is the equivalent of my
     *         value, but in currency unit "foreignCurrency".
     *
     * @exception ConversionInconsistentDBException thrown, if foreign or
     *            localCurrency is not found on database
     * @exception CurrencyConversionException thrown, if exchange rate or
     *            exchange rate factors are not found
     */
    public ICurrencyConversionResult convertToForeignCurrency(SAPDate conversionDate, ICurrencyUnit foreignCurrency,
        String exchangeRateTypeName)
        throws ConversionMissingDataException, CurrencyConversionException;
}
