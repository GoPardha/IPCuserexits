/*******************************************************************************

	Copyright (c) 2002 by SAP AG

	All rights to both implementation and design are reserved.

	Use and copying of this software and preparation of derivative works based
	upon this software are not permitted.

	Distribution of this software is restricted. SAP does not make any warranty
	about the software, its performance or its conformity to any specification.

*******************************************************************************/
package com.sap.spc.document.userexit;

import com.sap.spe.condmgnt.customizing.userexit.IFormula;
import com.sap.spe.pricing.transactiondata.IPricingCondition;

/**
 * Please implement this interface (the implementation class must be
 * "userexits.OneToOneKeyConnectionUserExit") if you want to overwrite the
 * standard behavior of the OneToOneKeyConnection class.
 */
public interface ICsticValueSurchargeFormula extends IFormula {
    public final static String TYPE_NAME = "CVS";
	/**
	 * Implement this method to determines the prices (pricing condition rates) 
	 * for a given set of variant keys.  
	 * @param oneToOneKeyConnection the one to one key connection. This object stores 
	 * the prices (pricing condition rates) you can associate with SCE's cstic values. 
	 * @param variantKeys the array of variant keys which are used to find 1:1 variant
	 * conditions.
	 * @param variantPricingConditions the array of variant conditions which are found
	 * for the above variant keys.
	 */
	public void determinePricingConditionRates(ICsticValueSurchargeUserExitAccess oneToOneKeyConnection,
											   String[] variantKeys, 
											   IPricingCondition[] variantPricingConditions);
}