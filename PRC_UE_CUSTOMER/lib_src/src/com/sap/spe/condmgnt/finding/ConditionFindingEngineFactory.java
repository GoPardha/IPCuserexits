/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.finding;

import com.sap.spe.base.util.ClassFinder;


/**
 * Abstract class to get an instance of a usage specific condition finding engine.
 */
public abstract class ConditionFindingEngineFactory {

    /**
     * The implementation class for this abstract factory class.
     */
    private static final String IMPLEMENTATION_CLASS =
        "com.sap.spe.condmgnt.finding.impl.ConditionFindingEngineFactoryImpl";

    /**
     * The singleton instance of the factory.
     */
    private static ConditionFindingEngineFactory factory = null;

    /**
     * Gets an instance of the condition finding engine factory.
     *
     * @return the condition finding engine factory instance or <code>null</code> if
     *         the condition finding engine factory instance cannot be created
     */
    public static ConditionFindingEngineFactory getFactory() {

        // check whether a factory has already been created
        if (factory == null) {
            factory = (ConditionFindingEngineFactory) ClassFinder.newInstance(IMPLEMENTATION_CLASS);
        }
        return factory;
    }

    /**
     * Gets an instance of the condition finding engine for a specific usage
     * @param usage of the condition finding engine
     * @return instance of condition finding engine
     */
    public abstract IConditionFindingEngine getConditionFindingEngine(String usage);
}
