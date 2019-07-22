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

import com.sap.sxe.util.math.Fraction;


/**
 * Represents a container for a fraction between an alternative unit of measure
 * and a base unit of measure of a product. It is needed for quantity conversion
 * between alternative and base unit of a material. A fraction consists of
 * numerator, denominator and exponent.
 */
public interface IAlternativeUnitFraction extends Serializable {

    /**
     * @return name of the alternative unit of measure
     */
    public String getAlternativeUnitName();

    /**
     * @return fraction between alternative and base unit of measure.
     */
    public Fraction getFraction();
}
