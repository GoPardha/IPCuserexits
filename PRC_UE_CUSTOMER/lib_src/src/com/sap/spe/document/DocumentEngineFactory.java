/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.document;

import com.sap.spe.base.util.ClassFinder;


public abstract class DocumentEngineFactory {

    /**
     * The implementation class for this abstract factory class.
     */
    private static final String IMPLEMENTATION_CLASS = "com.sap.spe.document.impl.DocumentEngineFactoryImpl";

    /**
     * The singleton instance of the factory.
     */
    private static DocumentEngineFactory factory = null;

    /**
     * Gets an instance of the masterdata engine factory.
     *
     * @return the masterdata engine factory instance or <code>null</code> if
     *         the masterdata engine  factory instance cannot be created
     */
    public static DocumentEngineFactory getFactory() {

        // check whether a factory has already been created
        if (factory == null) {
            factory = (DocumentEngineFactory) ClassFinder.newInstance(IMPLEMENTATION_CLASS);
        }
        return factory;
    }

    public abstract IDocumentEngine getDocumentEngine();
}
