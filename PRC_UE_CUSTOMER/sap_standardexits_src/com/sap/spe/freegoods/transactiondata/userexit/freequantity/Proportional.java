/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.freegoods.transactiondata.userexit.freequantity;

import java.math.BigDecimal;

import com.sap.spe.freegoods.transactiondata.userexit.FreeQuantityCalculationFormulaAdapter;
import com.sap.spe.freegoods.transactiondata.userexit.IFreeGoodsConditionUserExit;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Proportional extends FreeQuantityCalculationFormulaAdapter {
    
	public BigDecimal calculateFreeQuantity(IFreeGoodsConditionUserExit freeGoodsCondition) {

        // PRO RATA
        BigDecimal freeQuantity =
            freeGoodsCondition.getSalesQuantityValue()
                                     .multiply(freeGoodsCondition.getAdditionalQuantityValue())
                                     .divide(freeGoodsCondition.getFreeGoodsQuantityValue(),
                    BigDecimal.ROUND_HALF_UP).setScale(0, BigDecimal.ROUND_HALF_UP);
        
        return freeQuantity;
    }
}
