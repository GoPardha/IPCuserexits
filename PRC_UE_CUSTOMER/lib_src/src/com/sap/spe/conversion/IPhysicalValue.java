/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.conversion;

import com.sap.spe.conversion.exc.ConversionArithmeticException;
import com.sap.spe.conversion.exc.ConversionInconsistentDataException;
import com.sap.spe.conversion.exc.ConversionMissingDataException;


/**
 * Represents a dimensioned numerical value, eg. a value with a physical unit
 * such as 1.5 kg.
 */
public interface IPhysicalValue extends IDimensionalValue {

    /**
     * Convert me to an other physical unit.
     *
     * @param unitName the name of the target physical unit.
     *
     *
     * @return an IQuantityConversionResult object whose value is the equivalent
     *         of my value, but in a unit with the name "newUnitname".
     *
     * @exception ConversionMissingDataException thrown, if unit is not found
     *            on database
     */
    public IPhysicalConversionResult convertTo(String unitName)
        throws ConversionMissingDataException, ConversionInconsistentDataException, ConversionArithmeticException;

    /**
     * Convert me to a new physical unit.
     *
     * @param unit the target physical unit.
     *
     * @return an IQuantityConversionResult object whose value is the equivalent
     *         of my value, but in a new physical unit "newUnit".
     *
     * @exception ConversionMissingDataException thrown, if unit is not found
     *            on database
     */
    public IPhysicalConversionResult convertTo(IPhysicalUnit unit)
        throws ConversionMissingDataException, ConversionArithmeticException;
}
