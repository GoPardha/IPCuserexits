/*******************************************************************************

	Copyright (c) 2003 by SAP AG

	All rights to both implementation and design are reserved.

	Use and copying of this software and preparation of derivative works based
	upon this software are not permitted.

	Distribution of this software is restricted. SAP does not make any warranty
	about the software, its performance or its conformity to any specification.

*******************************************************************************/
package com.sap.spc.document.userexit;

import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.conversion.IDimensionalValue;
import com.sap.spe.document.IItem;

public interface ICsticValueSurchargeUserExitAccess {
	
	/**
	 * Interface which represents a pricing condition rate.
	 * A pricing condition rate contains information about the 
	 * condition rate (e.g. 10 EUR) and the pricing unit (e.g. 1 PC): 10 EUR per 1 piece
	 */
	public interface IPricingConditionRate {
		
		/**
		 * Sets the condition rate.
		 * @param conditionRate the condition rate to be set, e.g. 10 EUR (in 10 EUR / 1 PC)
		 */
		public void setRate(ICurrencyValue conditionRate);
		
		/**
		 * Sets the pricing unit.
		 * @param pricingUnit the pricing unit to be set, e.g. 1 PC (in 10 EUR / 1 PC)
		 */
		public void setPricingUnit(IDimensionalValue pricingUnit);

		/**
		 * Sets the calculation tyoe
		 * @param calculationType the calculation type, e.g. C for quantity dependent or A for percentage
		 */
		public void setCalculationType(char calculationType);				
	}
	
	/**
	 * Creates a new pricing condition rate object
	 * @return a pricing condition rate
	 */
	public IPricingConditionRate createPricingConditionRate();
	
	/**
	 * Add a pricing condition rate for a variant key
	 * @param variantKey the variant key
	 * @param pricingConditionRate the pricing condition rate to be added
	 */
	public void addPricingConditionRate(String variantKey, IPricingConditionRate pricingConditionRate);

	/**
	 * @return the item (note: do not modifiy it!)
	 */
	public IItem getItem();
}
