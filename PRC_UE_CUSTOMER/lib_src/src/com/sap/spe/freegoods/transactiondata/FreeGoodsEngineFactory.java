/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.freegoods.transactiondata;

import com.sap.spe.condmgnt.finding.ConditionFindingEngineFactory;


/** Factory for creating instances of freegoods engine.
 */
public class FreeGoodsEngineFactory {

    /**
     * The singleton instance of the factory.
     */
    private static FreeGoodsEngineFactory factory = new FreeGoodsEngineFactory();

    /**
     * Gets an instance of the condition customizing engine factory.
     *
     * @return the factory instance or <code>null</code> if the factory
     *         instance cannot be created
     */
    public static synchronized FreeGoodsEngineFactory getFactory() {

        // check whether a factory has already been created
        return factory;
    }

    public IFreeGoodsEngine getEngine(String usage) {
        return (IFreeGoodsEngine) ConditionFindingEngineFactory.getFactory().getConditionFindingEngine(usage);
    }
}
