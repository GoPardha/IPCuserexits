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

import com.sap.sxe.sys.SAPLocale;


/**
 * Represents a dimensioned numerical value, eg. a value with a dimensional unit
 * such as 1.5 kg, $3.19, 3 pieces, etc. This is the super class of
 * IPhysicalValue, IQuantityValue and ICurrencyValue.
 */
public interface IDimensionalValue extends Serializable {

    /**
     * @return my value as a number (without my unit).
     */
    public BigDecimal getValue();

    /**
     * @return my unit (without my value).
     */
    public IDimensionalUnit getUnit();

    /**
     * @return my internal unit's name.
     */
    public String getUnitName();
    
    /**
     * @return my external unit's name.
     */
    public String getExternalUnitName();

    /**
     * @return my value converted to string using a standard output format.
     */
    public String getValueAsString();
    
    /**
     * @return returns my value converted to a string in the locale-dependent representation.
     */
    public String getValueAsString(SAPLocale locale);
}
