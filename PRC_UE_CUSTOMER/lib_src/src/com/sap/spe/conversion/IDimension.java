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


/**
 * Represents a dimension such as MASS, VOLUME, LENGTH etc.
 *
 * All physical and all quantity units have a dimension. The physical unit kg
 * i.e have the dimension MASS. Quantity units like PIECE (PC) are related to
 * dimension DIMENSIONLESS
 */
public interface IDimension extends Serializable {

    /**
     * @return        the dimension name.
     */
    public String getName();

    /**
     * @return        the name of my SI unit of measure.
     */
    public String getSIUnit();

    /**
     * @return        Length exponent
     */
    public int getLengthExponent();

    /**
     * @return Time exponent
     */
    public int getTimeExponent();

    /**
     * @return        Electric current exponent
     */
    public int getElectricCurrentExponent();

    /**
     * @return        Temperature exponent
     */
    public int getTemperatureExponent();

    /**
     * @return        Mole quantity exponent
     */
    public int getMoleQuantityExponent();

    /**
     * @return        Luminosity exponent
     */
    public int getLuminosityExponent();

    /**
     * @return        Mass exponent
     */
    public int getMassExponent();
}
