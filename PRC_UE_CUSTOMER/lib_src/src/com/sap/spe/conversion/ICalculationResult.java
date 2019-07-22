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


public interface ICalculationResult extends Serializable {
    public BigDecimal getResult();

    /**
     * @return my numerator
     */
    public double getNumerator();

    /**
     * @return my denominator
     */
    public double getDenominator();

    /**
     * @return my offset
     */
    public BigDecimal getOffset();

    /**
     * @return the fraction, truncated to 5 digits.
     */
    public Fraction getFraction5();
}
