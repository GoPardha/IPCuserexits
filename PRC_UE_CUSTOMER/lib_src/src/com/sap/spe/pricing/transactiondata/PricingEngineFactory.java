/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata;

import com.sap.spe.condmgnt.finding.ConditionFindingEngineFactory;


public class PricingEngineFactory {

    /**
     * The singleton instance of the factory.
     */
    private static PricingEngineFactory factory = new PricingEngineFactory();

    /**
     * Gets an instance of the pricing customizing engine factory.
     *
     * @return the factory instance or <code>null</code> if the factory
     *         instance cannot be created
     */
    public static PricingEngineFactory getFactory() {
        return factory;
    }

    public IPricingEngine getPricingEngine(String usage) {
        return (IPricingEngine) ConditionFindingEngineFactory.getFactory().getConditionFindingEngine(usage);
    }
}
