/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.customizing;

import com.sap.spe.base.util.ClassFinder;


public abstract class ConditionCustomizingEngineFactory {

    /**
     * The implementation class for this abstract factory class.
     */

    // should be moved to vmc configuration management
    private static final String IMPLEMENTATION_CLASS =
        "com.sap.spe.condmgnt.customizing.impl.ConditionCustomizingEngineFactoryImpl";

    /**
     * The singleton instance of the factory.
     */
    private static ConditionCustomizingEngineFactory factory = null;

    /**
     * Gets an instance of the condition customizing engine factory.
     *
     * @return the factory instance or <code>null</code> if
     *         the factory instance cannot be created
     */
    public static ConditionCustomizingEngineFactory getFactory() {

        // check whether a factory has already been created
        if (factory == null) {
            factory = (ConditionCustomizingEngineFactory) ClassFinder.newInstance(IMPLEMENTATION_CLASS);
        }
        return factory;
    }

    public abstract IConditionCustomizingEngine getCustomizingEngine(String usage);
}
