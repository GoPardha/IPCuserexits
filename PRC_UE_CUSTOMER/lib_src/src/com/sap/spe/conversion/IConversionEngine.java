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
import java.util.Collection;

import com.sap.spe.base.cache.Engine;
import com.sap.spe.base.util.IF4Help;
import com.sap.spe.conversion.exc.ConversionArithmeticException;
import com.sap.spe.conversion.exc.ConversionInconsistentDataException;
import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.spe.conversion.exc.ConversionParseException;
import com.sap.spe.conversion.exc.CurrencyConversionException;
import com.sap.sxe.sys.SAPDate;
import com.sap.sxe.util.math.Fraction;


public interface IConversionEngine extends Engine {

    /**
     * Creates an <code>ICurrencyUnit</code>
     *
     * @param unitName name of currency unit, eg. USD
     * @return <code>ICurrencyUnit</code>
     * @throws <code>ConversionMissingDataException</code> if unit does not
     *         exist in database
     */
    public ICurrencyUnit getCurrencyUnit(String unitName)
        throws ConversionMissingDataException;

    /**
     * Creates an <code>ICurrencyValue</code> from a value and the name of a
     * unit.
     *
     * @param value amount, eg. 100.00
     * @param unitName name of currency unit, eg. USD
     * @return <code>ICurrencyValue</code>
     * @throws <code>ConversionMissingDataException</code> if the unit does
     *         not exist in database
     */
    public ICurrencyValue getCurrencyValue(BigDecimal value, String unitName)
        throws ConversionMissingDataException;

    /**
     * Creates an <code>ICurrencyValue</code> from a value and a unit
     *
     * @param value amount, eg. 100.00
     * @param unit currency unit, eg. USD
     * @return <code>ICurrencyValue</code>
     */
    public ICurrencyValue getCurrencyValue(BigDecimal value, ICurrencyUnit unit);

    /**
     * Creates an <code>ICurrencyValue</code> from a value string and the name
     * of a unit.
     *
     * @param value amount (String) in parsable form (Format #.##0.)
     * @param unitName name of currency unit, eg. USD
     * @return <code>ICurrencyValue</code>
     * @throws <code>ConversionMissingDataException</code> if the unit does
     *         not exist in database
     * @exception <code>ConversionParseException</code> if the parsing of
     *            value fails.
     */
    public ICurrencyValue getCurrencyValue(String value, String unitName)
        throws ConversionMissingDataException, ConversionParseException;

    /**
     * Creates an <code>ICurrencyValue</code> from a value and a unit
     *
     * @param value amount, eg. 100.00
     * @param unit currency unit, eg. USD
     * @param considerFixedPointArithmetic TODO: ?
     * @param numberOfDecimalsInDatabase TODO: ?
     * @return <code>ICurrencyValue</code>
     */
    public ICurrencyValue getCurrencyValue(BigDecimal value, ICurrencyUnit unit, boolean considerFixedPointArithmetic,
        int numberOfDecimalsInDatabase);

    /**
     * Creates an <code>ICurrencyValue</code> that corrensponds to isoCode
     *
     * @param isoCode
     * @return <code>ICurrencyValue</code>
     * @throws <code>ConversionMissingDataException</code> if isoCode does not
     *         exist in the database
     * @throws <code>ConversionInconsistentDataException</code> if isoCode is
     *         assigned to more then one currency unit
     */
    public ICurrencyUnit getCurrencyUnitForIsoCode(String isoCode)
        throws ConversionMissingDataException, ConversionInconsistentDataException;

    /**
     * Returns the list of currency units from database
     *
     * @return <code>IF4Help</code> (list of currency units)
     */
    public IF4Help getF4HelpCurrencyUnits();

    /// QUANTITY ///////////////////////////////////////////////////////////////

    /**
     * Creates an <code>IQuantityUnit</code> from an internal unit Name and a
     * product
     *
     * @param internalUnitName SAP internal (language-neutral) unit name e, eg.
     *        "ST"
     * @param product product used to check if unit is valid
     * @return <code>IQuantityUnit</code>
     * @exception throws <code>ConversionMissingDataException</code>, if unit
     *            is not found in the database; throws
     *            <code>ConversionInconsistentDataException</code> if unit
     *            name is not the name of the base unit of measure or not an
     *            alternative unit of measure of the product.
     */
    public IQuantityUnit getQuantityUnit(String internalUnitName, IConversionProduct product)
        throws ConversionMissingDataException, ConversionInconsistentDataException;

    public IQuantityUnit getQuantityUnitToBeDiscussed(String externalUnitName, IConversionProduct product)
        throws ConversionMissingDataException, ConversionInconsistentDataException;

    /**
     * Creates an <code>IQuantityValue</code> from a value, a unit name, and a
     * product.
     *
     * @param value amount
     * @param unitName name of quantity unit
     * @param product product used to check if unit is valid
     * @return <code>IQuantityValue</code>
     * @exception throws <code>ConversionMissingDataException</code>, if unit
     *            is not found in the database; throws
     *            <code>ConversionInconsistentDataException</code> if unit
     *            name is not the name of the base unit of measure or not an
     *            alternative unit of measure of the product
     */
    public IQuantityValue getQuantityValue(BigDecimal value, String unitName, IConversionProduct product)
        throws ConversionMissingDataException, ConversionInconsistentDataException;

    /**
     * Creates an <code>IQuantityValue</code> from a value and unit
     *
     * @param value amout of quantity value
     * @param unit quantity unit
     * @return <code>IQuantityValue</code>
     */
    public IQuantityValue getQuantityValue(BigDecimal value, IQuantityUnit unit);

    /**
     * Returns all available quantity units related to a product from database
     *
     * @param product product, from which we want the information
     * @return <code>IF4Help</code> (list of quantity units)
     */
    public IF4Help getF4HelpQuantityUnits(IConversionProduct product);

    /// PHYSICAL ///////////////////////////////////////////////////////////////

    public Collection getAllPhysicalUnits()
    throws ConversionMissingDataException ;
    
    /**
     * Creates an <code>IPhysicalUnit</code> that corresponds to unit name
     *
     * @param internalUnitName SAP internal unit name (language neutral)
     * @return <code>IPhysicalUnit</code>
     * @exception throws <code>ConversionMissingDataException</code>, if unit
     *            is not found in the database
     */
    public IPhysicalUnit getPhysicalUnit(String internalUnitName)
        throws ConversionMissingDataException;

    /**
     * Creates an <code>IPhysicalUnit</code> that corresponds to ISO code
     *
     * @param isoCode ISO code of physical unit
     * @return <code>IPhysicalUnit</code>
     * @exception throws <code>ConversionMissingDataException</code>, if ISO
     *            code is not found in the database; throws
     *            <code>ConversionInconsistentDataException</code>, ISO code
     *            is assigned to several units.
     */
    public IPhysicalUnit getPhysicalUnitForIsoCode(String isoCode)
        throws ConversionMissingDataException, ConversionInconsistentDataException;

    /**
     * Creates an <code>IPhysicalUnit</code> from a value and an internal unit
     * name
     *
     * @param value amout of quantity value
     * @param internalUnitName SAP internal unit name (language neutral)
     * @return <code>IPhysicalUnit</code>
     * @exception throws <code>ConversionMissingDataException</code>, if unit
     *            is not found in the database
     */
    public IPhysicalValue getPhysicalValue(BigDecimal value, String internalUnitName)
        throws ConversionMissingDataException;

    /**
     * Creates an <code>IPhysicalUnit</code> from a value and a unit
     *
     * @param value amout of quantity value
     * @param unit physical unit
     * @return <code>IPhysicalUnit</code>
     */
    public IPhysicalValue getPhysicalValue(BigDecimal value, IPhysicalUnit unit);

    /**
     * Returns the list of physical units of one dimension from database
     *
     * @param dimensionName name of dimension
     * @return <code>IF4Help</code> (list of physical units)
     */
    public IF4Help getF4HelpPhysicalUnits(String dimensionName);

    /**
     * Returns the list of physical units from the database
     *
     * @return <code>IF4Help</code> (list of physical units)
     */
    public IF4Help getF4HelpPhysicalUnits();

    /// EXCHANGE RATE //////////////////////////////////////////////////////////

    /**
     * Returns <code>IExchangeRate</code> matching an exchange rate type, a
     * source currency, a target currency and a conversion date; an external
     * exchange rate can be specified for conversion
     *
     * @param exchangeRateTypeName exchange rate type name
     * @param fromCurrency source currency
     * @param toCurrency target currency
     * @param date conversion date
     * @param externalRate external rate which should be used for conversion
     *
     * @return <code>IExchangeRate</code>
     *
     * @exception throws <code>ConversionMissingDataException</code> if source
     *            currency, target currency, exchange rate type, exchange rate
     *            or conversion factor do not exists in the database; throws
     *            <code>CurrencyConversionException</code> if an be found
     */
    public IExchangeRate getExchangeRate(String exchangeRateTypeName, String fromCurrency, String toCurrency,
        SAPDate date, BigDecimal externalRate)
        throws ConversionMissingDataException, CurrencyConversionException;

    /** includes evaluation of customizing */
    public IExchangeRate getExchangeRateForExactDate(String exchangeRateTypeName, String fromCurrency,
        String toCurrency, SAPDate date)
        throws ConversionMissingDataException, CurrencyConversionException;

    /** does not includes evaluation of customizing - plain access to DB */
    public IExchangeRate getExchangeRateForExactDatePlain(String exchangeRateTypeName, String fromCurrency,
        String toCurrency, SAPDate date)
        throws ConversionMissingDataException, CurrencyConversionException;

    /**
     * Creates an <code>IExchangeRate</code> with rate 1 and identical source
     * and target currency
     *
     * @param exchangeRateTypeName exchange rate type name
     * @param fromAndToCurrency source and target currency unit
     * @param exchangeRateDate exchange rate date
     * @throws throws <code>ConversionMissingDataException</code> if currency
     *         unit or exchange rate type do not exists in the database
     */

    // TODO: no exception yet, because of no checks yet
    public IExchangeRate getIdentityExchangeRate(String exchangeRateTypeName, String fromAndToCurrency,
        SAPDate exchangeRateDate)
        throws ConversionMissingDataException;

    /**
     * Creates an <code>IExchangeRate</code> with rate 0 and identical source
     * and target currency
     *
     * @param exchangeRateTypeName exchange rate type name
     * @param fromAndToCurrency source and target currency unit
     * @throws throws <code>ConversionMissingDataException</code> if currency
     *         unit or exchange rate type do not exists in the database
     */

    // TODO: no exception yet, because of no checks yet
    public IExchangeRate getZeroExchangeRate(String exchangeRateTypeName, String fromAndToCurrency)
        throws ConversionMissingDataException;

    /**
     * Returns <code>IExchangeRateType</code> that corresponds to type name
     *
     * @param name exchange rate type name
     * @return <code>IExchangeRate</code>
     * @throws <code>ConversionMissingDataException</code> throw if exchange
     *         rate type is not found in the database
     */
    public IExchangeRateType getExchangeRateType(String name)
        throws ConversionMissingDataException;

    /**
     * Creates a fraction for an alternative unit of measure of a conversion
     * product
     *
     * @param alternativeUnitName name of alternative unit of measure
     * @param fraction fraction for conversion between alternative base unit of
     *        product
     * @return <code>IAlternativeUnitFraction</code>
     * @throws <code>ConversionMissingDataException</code> throw alternative
     *         unit is not found in the database
     */

    // TODO: no exception yet, because of no checks yet
    public IAlternativeUnitFraction getAlternativeUnitFraction(String alternativeUnitName, Fraction fraction)
        throws ConversionMissingDataException;

    /**
     * Creates a fraction for an alternative unit of measure of a conversion
     * product
     *
     * @param alternativeUnitName name of alternative unit of measure
     * @param fraction fraction for conversion between alternative base unit of
     *        product
     * @return <code>IAlternativeUnitFraction</code>
     */
    public IAlternativeUnitFraction getAlternativeUnitFraction(IQuantityUnit alternativeUnitName, Fraction fraction);

    /// DIMENSION //////////////////////////////////////////////////////////////

    /**
     * Returns the SI unit for a dimension for example 'KG' for the dimension
     * name "MASS" or M3 for the dimension name "VOLUME"
     *
     * @param dimensionName name of dimension
     * @return <code>IPhysicalUnit</code>, the base SI unit for the dimension
     * @exception throws <code>ConversionMissingDataException</code> if
     *            dimension or the corresponding unit is not found in the
     *            database
     */
    public IPhysicalUnit getSiUnitForDimension(String dimensionName)
        throws ConversionMissingDataException;

    /**
     * Returns the mass dimension
     *
     * @return <code>IDimension</code>
     * @throws throws <code>ConversionMissingDataException</code> if the mass
     *         dimension is not found in the database
     */
    public IDimension getMassDimension()
        throws ConversionMissingDataException;

    /**
     * Returns the value dimension
     *
     * @return <code>IDimension</code>
     * @throws throws <code>ConversionMissingDataException</code> if the value
     *         dimension is not found in the database
     */
    public IDimension getVolumeDimension()
        throws ConversionMissingDataException;

    /**
     * Returns the points dimension
     *
     * @return <code>IDimension</code>
     * @throws throws <code>ConversionMissingDataException</code> if the points
     *         dimension is not found in the database
     */
    public IDimension getPointsDimension()
        throws ConversionMissingDataException;

    /**
     * Returns the dimension that corresponds to name
     *
     * @param name dimension name
     * @return <code>IDimension</code>
     * @throws throws <code>ConversionMissingDataException</code> if dimension
     *         is not found in the database
     */
    public IDimension getDimension(String name)
        throws ConversionMissingDataException;

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Converts value via scaling and translation rather than with a unit
     * The formula is:
     * double result = (((value * numerator) / denominator) + offset) * Math.exp(exponent * C_LOG10);
     *
     * @param valueIn value to convert
     * @param numerator numerator
     * @param denominator denominator
     * @param exponent exponent
     * @param offset offset
     * @return <code>ICalculationResult</code>, the conversion result
     * @throws throws <code>ConversionArithmeticException</code> if denominator is 0
     */

    // TODO: check in pricing if neccessary
    public ICalculationResult convertWithFactorsAndExponent(double value, double numerator, double denominator,
        int exponent, double offset)
        throws ConversionArithmeticException;

    /**
     * Creates a conversion product
     *
     * @param productID
     * @param externalProductId
     * @param logicalSystem
     * @param productType
     * @param objectFamily
     * @param description
     * @param internalBaseUnitName
     * @return <code>IConversionProduct</code>
     * @throws throws <code>ConversionMissingDataException</code> if internal
     *         base is not found in the databse unit
     */
    public IConversionProduct getConversionProduct(String productID, String externalProductId, String logicalSystem,
        String productType, String objectFamily, String description, String internalBaseUnitName)
        throws ConversionMissingDataException;
    
    /**
     * enables the use of Identity Exchange Rate buffer
     *
     * @param useIdExRateBuffer
     */
    public void useIdentityExchangeRateBuffer(boolean useIdExRateBuffer);
    
    /**
     * clears the Identity Exchange Rate buffer
     */
    public void clearIdentityExchangeRateBuffer();

}
