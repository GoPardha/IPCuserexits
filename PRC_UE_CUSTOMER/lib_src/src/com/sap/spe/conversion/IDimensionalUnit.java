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
 * A dimensional unit such as KG, USD, piece, etc.
 *
 * This is the super class of IPhysicalUnit, IQuantityUnit and ICurrencyUnit.
 * Each non-abstract subclass remembers its instances, and does not allow
 * multiple instances with the same name.
 */
public interface IDimensionalUnit extends Serializable {

    /**
     * @return the name of my unit (language-neutral)
     */
    public String getUnitName();

    /**
     * @return the external standard representation of my unit depending on
     *         language
     */
    public String getExternalUnitName();
    
    /**
     * @return the external technical representation of my unit depending on
     *         language
     */
    public String getExternalUnitNameTech();

    /**
     * @return my ISO code or null (if not maintained)
     */
    public String getIsoCode();

    /**
     * @return true if the unit is flagged as a primary SAP unit for an ISO code
     */
    public boolean isPrimaryUnitForIsoCodeConversion();

    /**
     * @return one of the external representations of my unit.
     */
    public String getLongDescription();

    public String getShortDescription();

    /**
     * Checks if I am equal to the unit.
     *
     * @param unit unit to which I should be compared
     *
     * @return true, if the name of unit is the same as mine.
     */
    public boolean equals(Object unit);

    /**
     * @return the number of decimals of a unit. This is 3 for physical and
     *         quantity units. For currency units it is typically 2 if the
     *         customizing of the currency unit is not set up diffently.
     */
    public int getNumberOfDecimals();
}
