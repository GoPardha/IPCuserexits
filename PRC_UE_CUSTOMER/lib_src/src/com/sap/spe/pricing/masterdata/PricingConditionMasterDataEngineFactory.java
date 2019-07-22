/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.masterdata;

import java.util.HashMap;
import java.util.Map;

import com.sap.spe.base.util.ClassFinder;


public class PricingConditionMasterDataEngineFactory {

    /**
     * The singleton instance of the factory.
     */
    private static PricingConditionMasterDataEngineFactory factory = new PricingConditionMasterDataEngineFactory();
    private Map engines = new HashMap();

    /**
     * Gets an instance of the pricing customizing engine factory.
     *
     * @return the factory instance or <code>null</code> if
     *         the factory instance cannot be created
     */
    public static PricingConditionMasterDataEngineFactory getFactory() {
        return factory;
    }

    public IPricingConditionMasterDataEngine getPricingConditionMasterDataEngine(String usage) {
        IPricingConditionMasterDataEngine engine = (IPricingConditionMasterDataEngine) engines.get(usage);
        if (engine == null) {
            engine =
                (IPricingConditionMasterDataEngine) ClassFinder.newInstance("com.sap.spe.pricing.masterdata.impl.PricingConditionMasterDataEngine",
                    null, null);
            engines.put(usage, engine);
        }

        return engine;
    }
}
