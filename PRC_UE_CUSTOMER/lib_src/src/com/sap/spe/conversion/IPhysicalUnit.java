/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.conversion;

import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.sxe.util.math.Fraction;


/**
 * A physical unit such as KG, M without reference to a product.
 */
public interface IPhysicalUnit extends IDimensionalUnit {

    /**
     * @return my SI (System International) unit name.
     *
     * @exception ConversionInconsistentDBException thrown if my SI name is not
     *            in the database. (R/3-field: T006D-MSSIE)
     */
    public String getSIUnit()
        throws ConversionMissingDataException;

    /**
     * @return my dimension name. (R/3-field: T006-DIMID)
     */
    public String getDimensionName();

    /**
     * @return my dimension (R/3-table: T006D)
     */
    public IDimension getDimension()
        throws ConversionMissingDataException;

    /**
     * @return my fraction which is used for the conversion between me and the
     *         SI-baseunit. (R/3-field: T006-ZAEHL & T006-NENNER & T006-EXP10)
     */
    public Fraction getFraction();

    /**
     * @return my additive constant which is used for the conversion between me
     *         and the SI-baseunit. (R/3-field: T006-ADDKO)
     */
    public double getOffset();

    /**
     * @return my exponent which is used for the conversion between me and the
     *         SI-baseunit. (R/3-field: T006-EXPON)
     */
    public int getExponent();

    /**
     * @return my number of decimal places relevant for rounding.
     *         (R/3-field: T006-ANDEC)
     */
    public int getNumberOfDecimalsForRounding();
}
