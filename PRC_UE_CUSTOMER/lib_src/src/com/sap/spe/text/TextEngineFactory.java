/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.text;

import com.sap.vmc.logging.Location;


/**
 * The <code>TextEngineFactory</code> class is the facade and the user
 * interface for getting access to a <code>ITextEnginee</code>.
 */
public abstract class TextEngineFactory {

    /**
     * The location in which tracing information is stored.
     */
    private static final Location LOCATION = Location.getLocation(TextEngineFactory.class);

    /**
     * The implementation class for this abstract factory class.
     */
    private static final String IMPLEMENTATION_CLASS = "com.sap.spe.text.impl.TextEngineFactoryImpl";

    /**
     * The singleton instance of the factory.
     */
    private static TextEngineFactory rFactory = null;

    /**
     * Gets an instance of the text engine factory.
     *
     * @return the text engine factory instance or <code>null</code> if
     *         the text engine factory instance cannot be created
     */
    public static TextEngineFactory getFactory() {

        // check whether a cache region factory 
        // has already been created
        if (rFactory == null) {
            try {

                // lockup the implementation of the abstract cache
                // region factory class
                Class implClass = Class.forName(IMPLEMENTATION_CLASS);
                rFactory = (TextEngineFactory) implClass.newInstance();
            }
            catch (ClassNotFoundException ex) {
                LOCATION.catching(ex);
            }
            catch (InstantiationException ex) {
                LOCATION.catching(ex);
            }
            catch (IllegalAccessException ex) {
                LOCATION.catching(ex);
            }
        }

        return rFactory;
    }

    public abstract ITextEngine getTextEngine();
}
