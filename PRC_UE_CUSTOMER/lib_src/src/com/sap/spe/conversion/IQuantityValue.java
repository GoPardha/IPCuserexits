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

import com.sap.spe.conversion.exc.ConversionArithmeticException;
import com.sap.spe.conversion.exc.ConversionInconsistentDataException;
import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.sxe.util.math.Fraction;


/**
 * Represents a dimensioned numerical value, eg. a value with a quantity unit
 * such as 1.5 kg related to a product
 */
public interface IQuantityValue extends IPhysicalValue {

    /**
     * Convert me to a new quantity unit. Only used internally by the pricing
     * engine. Conversion factors which are already known are taken into
     * account.
     *
     * @param fractionIn fraction for conversion between unitIn and base unit of
     *        product
     * @param unitOut target unit
     * @param fractionOut fraction for conversion between base unit and unitOut
     * @param baseUnit base unit of product
     * @param baseValue value in base unit
     *
     * @return IQuantityConversionResult result of quantity conversion, consists
     *         of value, fractions and offset etc.
     *
     * @exception ConversionInconsistentDBException thrown, if no unit is found
     *            in the database which is valid for the product.
     */
    public IQuantityConversionResult convertTo(Fraction fractionIn, IPhysicalUnit unitOut, Fraction fractionOut,
        IQuantityUnit baseUnit, BigDecimal baseValue)
        throws ConversionMissingDataException, ConversionInconsistentDataException, ConversionArithmeticException;

    /**
     * Convert me to a new quantity unit. Only used internally by the pricing
     * engine. Conversion factors which are already known are taken into
     * account.
     *
     * @param fractionIn fraction for conversion between unitIn and base unit of
     *        product
     * @param unitOut target unit
     * @param fractionOut fraction for conversion between base unit and unitOut
     *
     * @return IQuantityConversionResult result of quantity conversion, consists
     *         of value, fractions and offset etc.
     *
     * @exception ConversionInconsistentDBException thrown, if no unit is found
     *            in the database which is valid for the product.
     */
    public IQuantityConversionResult convertTo(Fraction fractionIn, IPhysicalUnit unitOut, Fraction fractionOut)
        throws ConversionMissingDataException, ConversionInconsistentDataException, ConversionArithmeticException;

    /**
     * Convert me to base unit of product. Only used internally by the pricing
     * engine.
     *
     * @return IQuantityConversionResultSimple result of quantity conversion,
     *         consists of value, fractions and offset etc.
     *
     * @exception ConversionInconsistentDBException thrown, if no unit is found
     *            in the database which is valid for the product.
     */
    public IPhysicalConversionResult convertBaseToAlternativeQuantity(IPhysicalUnit unitOut)
        throws ConversionMissingDataException, ConversionInconsistentDataException, ConversionArithmeticException;

    /**
     * Convert me (in alternative unit of product) to the base unit of product.
     * Only used internally by the pricing engine.
     *
     * @return IQuantityConversionResultSimple result of quantity conversion,
     *         consists of value, fractions and offset etc.
     *
     * @exception ConversionInconsistentDBException thrown, if no unit is found
     *            in the database which is valid for the product.
     */
    public IPhysicalConversionResult convertAlternativeToBaseQuantity()
        throws ConversionMissingDataException, ConversionInconsistentDataException, ConversionArithmeticException;
}
