/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.customizing;

import com.sap.spe.condmgnt.customizing.ConditionCustomizingEngineFactory;


public final class PricingCustomizingEngineFactory {

    /**
     * The singleton instance of the factory.
     */
    private static PricingCustomizingEngineFactory factory = new PricingCustomizingEngineFactory();

    /**
     * Gets an instance of the pricing customizing engine factory.
     *
     * @return the factory instance or <code>null</code> if
     *         the factory instance cannot be created
     */
    public static PricingCustomizingEngineFactory getFactory() {
        return factory;
    }

    public IPricingCustomizingEngine getPricingCustomizingEngine(String usage) {
        return (IPricingCustomizingEngine) ConditionCustomizingEngineFactory.getFactory().getCustomizingEngine(usage);
    }
}
