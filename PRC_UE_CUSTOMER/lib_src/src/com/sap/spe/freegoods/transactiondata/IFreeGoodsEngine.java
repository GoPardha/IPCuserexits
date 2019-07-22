/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.freegoods.transactiondata;

import com.sap.spe.condmgnt.customizing.IProcedure;
import com.sap.spe.condmgnt.finding.IConditionFindingEngine;


/**
 * Facade for the freegoods component. The behaviour of the freegoods
 * determination can be set. Factory methods for creating freegoods items.
 */
public interface IFreeGoodsEngine extends IConditionFindingEngine {

    /**
     * Creates a new FreeGoodsItem. Typical use: When a new sales document line
     * item is created.
     */
    public IFreeGoodsItem createFreeGoodsItem(IProcedure freeGoodsProcedure);
}
