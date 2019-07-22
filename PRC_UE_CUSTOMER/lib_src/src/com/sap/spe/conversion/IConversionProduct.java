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

import com.sap.spe.conversion.exc.ConversionInconsistentDataException;
import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.sxe.util.math.Fraction;


/**
 * Interface to product master from the point of view of the Conversion Engine.
 */
public interface IConversionProduct extends Serializable {

    /**
     * @return the product ID.
     */
    public String getId();

    /**
     * @return gets the external identifier
     */
    public String getExternalId();

    /**
     * @return gets the logical system
     */
    public String getLogicalSystem();

    /**
     * @return gets the product type
     */
    public String getProductType();

    /**
     * @return gets the object family
     */
    public String getObjectFamily();

    /**
     * @param locale locale of user for language dependent description and also
     *        as language of error messages which might occur
     * @return a description for the product. This description is displayed on
     *         the graphical user interface for the pricing conditions.
     */
    public String getDescription();

    /**
     * @return the base unit of measure of the product. This is necessary for
     *         quantity conversion.
     *
     * @exception ConversionMissingDataException should be thrown if the base
     *            unit of measure is not found in the database. (It would make
     *            sense to use the method newQuantityUnit of the
     *            ConversionEngine).
     */
    public IQuantityUnit getBaseUnit()
        throws ConversionMissingDataException, ConversionInconsistentDataException;

    /**
     * @param targetUnit alternative unit related to a product
     *
     * @return the fraction for the conversion from base quantity unit to the
     *         target unit. It consists of numerator, denominator and exponent
     */
    public Fraction getAlternativeUnitConversionFraction(IQuantityUnit targetUnit);

    /**
     * Add an alternative unit of measure with the corresponding conversion
     * fraction to me.
     */
    public void addAlternativeUnitToFractionMapping(String alternativeUnitName, int numerator, int denominator,
        int exponent) throws ConversionMissingDataException;

    /**
     * @return a vector of IAlternativeUnitFractions( a map of alternative unit
     *         names and fractions) for conversions between the base unit and
     *         all alternative units.
     */
    public IAlternativeUnitFraction[] getAlternativeUnitConversionFractions();

    /**
     * @return a vector of IQuantityUnits which contains the base unit of
     *         measure and alternative units of measure for the conversion
     *         product.
     */
    public IPhysicalUnit[] getAvailableQuantityUnits();

    /**
     * @param unit a physical unit
     *
     * @return true, if unitName is the name of the base unit of measure or an
     *         alternative unit of measure of the product.
     */
    public boolean containsUnit(IPhysicalUnit unit);
}
