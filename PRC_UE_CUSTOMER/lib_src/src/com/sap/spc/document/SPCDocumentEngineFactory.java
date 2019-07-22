/************************************************************************

Copyright (c) 2000 by SAP AG

All rights to both implementation and design are reserved.

Use and copying of this software and preparation of derivative works
based upon this software are not permitted.

Distribution of this software is restricted. SAP does not make any
warranty about the software, its performance or its conformity to any
specification.

**************************************************************************/
package com.sap.spc.document;

import com.sap.spe.base.util.ClassFinder;
import com.sap.vmc.logging.Location;

public abstract class SPCDocumentEngineFactory{

/**
 * The location in which tracing information is stored.
 */
private static final Location location = Location.getLocation(SPCDocumentEngineFactory.class);

/**
 * The implementation class for this abstract factory class.
 */
private static final String IMPLEMENTATION_CLASS = "com.sap.spc.document.impl.SPCDocumentEngineFactoryImpl";

/**
 * The singleton instance of the factory.
 */
private static SPCDocumentEngineFactory factory = null;

/**
 * Gets an instance of the conversion engine factory.
 * 
 * @return the conversion engine factory instance or <code>null</code> if 
 *         the conversion engine  factory instance cannot be created
 */
public static SPCDocumentEngineFactory getFactory() {
	// check whether a factory has already been created
	if (factory == null) {
	    factory = (SPCDocumentEngineFactory) ClassFinder.newInstance(IMPLEMENTATION_CLASS);
	}
	return factory;
}

public abstract ISPCDocumentEngine getDocumentEngine();
}
