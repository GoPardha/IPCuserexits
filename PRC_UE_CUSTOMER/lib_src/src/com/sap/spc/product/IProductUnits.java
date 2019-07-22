/************************************************************************

	Copyright (c) 2000 by SAP AG

	All rights to both implementation and design are reserved.

	Use and copying of this software and preparation of derivative works
	based upon this software are not permitted.

	Distribution of this software is restricted. SAP does not make any
	warranty about the software, its performance or its conformity to any
	specification.

**************************************************************************/
package com.sap.spc.product;

import java.util.*;
import java.math.BigDecimal;

import com.sap.sxe.util.math.Fraction;
import com.sap.spe.conversion.exc.*;

import java.io.Serializable;


/**
 * Interface for accessing the unit data of a product.
 */
public interface IProductUnits extends Serializable {
	/**
	 * Gets the unit data for the unit @param unit.
	 * @return the unit data
	 */
	public IProductUnitEntry getUnitEntry(String unit);

	/**
	 * Gets the unit data for the unit @param unit
	 * @return all units related to the product
	 */
	public Enumeration getUnits();

	/**
	 * Gets an conversion fraction for the unit @param unit
	 * @return the fraction	 */
	public Fraction getUnitFraction(String unit);

	/**
	 * Tests if the product contains the unit @param unit.
	 * @return if the unit exists
	 */
	public boolean containsUnit(String unit);

	/**
	 * Adds a new unit entry to the product
	 * @param conversionEngine the engine used to convert the new unit to the base unit
	 * @param unit the name of the new unit
	 * @param numerator conversion to base unit numerator
	 * @param denominator conversion to base unit denominator
	 * @param exponent10 conversion to base unit exponent10
	 * @param grossWeight gross weight in relation to the new unit
	 * @param netWeight net weight in relation to the new unit
	 * @param weightUnit the weight unit
	 * @param volume volume in relation to the new unit
	 * @param volumeUnit the volume unit
	 * @param isBaseUnit indicator whether the new unit is base unit if the product
	 * @exception ConversionInconsistentDBException if the unit of measure is not found in the database
	 */
	public void addUnitEntry(	String unit,
								int numerator,
								int denominator,
								int exponent10,
								BigDecimal grossWeight,
								BigDecimal netWeight,
								String weightUnit,
								BigDecimal volume,
								String volumeUnit,
								boolean isBaseUnit) throws ConversionMissingDataException;

    /**
     * @return	the name base unit of measure.
     */
	public String getBaseUOM();

}
