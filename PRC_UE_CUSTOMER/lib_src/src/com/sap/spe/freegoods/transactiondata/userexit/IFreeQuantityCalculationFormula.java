/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.freegoods.transactiondata.userexit;

import java.math.BigDecimal;

import com.sap.spe.condmgnt.customizing.userexit.IFormula;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IFreeQuantityCalculationFormula extends IFormula {
    public final static String TYPE_NAME = "FQC";

	/**
	 * Calculates the quantity to be given for free. Depending on inclusive or exclusive
	 * it is in base unit of measurement (exclusive) or in sales unit of measurement (inclusive).
	 * Corresponds to KONDN_FRM-NRMENGE (R/3)
	 * @param freeGoodsCondition the free goods condition to be calculated. Corresponds to KONDN_FRM (R/3)
	 * @return the quantity to be given for free
	 */
    public BigDecimal calculateFreeQuantity(IFreeGoodsConditionUserExit freeGoodsCondition);
}
