/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.freegoods.transactiondata;

import com.sap.spe.condmgnt.finding.IConditionFindingManager;
import com.sap.spe.conversion.IConversionProduct;
import com.sap.spe.conversion.IQuantityValue;


/**
 * An instance of this interface is created for each sales order line item. The
 * freeGoods item contains accumulated information of all freeGoods conditions.
 * This will be the interface to the freeGoods determination results for
 * external systems and will be container for the interaction information like
 * item quantity and other information required for freeGoods dertermination.
 */
public interface IFreeGoodsItem extends IConditionFindingManager {

    /**
     * Set the sales quantity of the item.
     */
    public void setSalesQuantity(IQuantityValue salesQuantity);

    /**
     * @return the product for which freegoods is being determined.
     */
    public IConversionProduct getProduct();

    /**
     * @return the sales quantity of the sales order item.
     */
    public IQuantityValue getSalesQuantity();

    public IFreeGoodsCondition[] determineFreeGoods();    
    
    /**
     * @return the freegoods conditions from the buffer. No search or
     *         calculations are performed. Use this method when u are sure that
     *         parameter for freegoods determination have not changed, for
     *         example when displaying conditions. Use
     *         #determineFreeGoodsConditions(false) to perform new calculations.
     *         Use #determineFreeGoodsConditions(true) to perform new search of
     *         conditions and new calculations.
     */
    public IFreeGoodsCondition[] getFreeGoodsConditions();
}
