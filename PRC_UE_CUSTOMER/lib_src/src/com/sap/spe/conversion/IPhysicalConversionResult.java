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

import java.math.BigDecimal;

import com.sap.sxe.util.math.Fraction;


/**
 * Represents the result for quantity conversion from an alternaitve to base or
 * from base to alternative unit of a product and for internal physical
 * conversion from a physical value into another physical value with the same
 * dimension.
 *
 * This is an object rather than a scalar number because multiple values have to
 * be returned from conversion: numerator and denominator with 5 digits and the
 * value
 */
public interface IPhysicalConversionResult extends Serializable {

    /**
     * @return my dimensional value which is the result of the conversion.
     */
    public IPhysicalValue getValue();

    /**
     * @return my fraction which was used for conversion from one unit to the
     *         other unit truncated to 5 digits for numerator and denominator
     */
    public Fraction getFraction5();

    /**
     * @return my offset which was used for the conversion.
     */
    public BigDecimal getOffset();
}
