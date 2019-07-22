/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.conversion;

import com.sap.sxe.util.math.Fraction;


/**
 * Represents the result for quantity conversion from an alternaitve to another
 * alternative unit of a product.
 *
 * This is an object rather than a scalar number because multiple values have to
 * be returned from conversion: a numerator and a denominator with 5 digits and
 * the value.
 */
public interface IQuantityConversionResult extends IPhysicalConversionResult {

    /**
     * @return my fraction which was used for the quantity conversion from base
     *         to alternative unit
     */
    public Fraction getFractionBaseToAlternative5();

    /**
     * @return my fraction which was used for the quantity conversion from
     *         alternative to base unit
     */
    public Fraction getFractionAlternativeToBase5();
}
