/*
 * Created on 13 mai 2005
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package com.sap.spc.product;

/**
 * @author i018282
 */
/*

Copyright (c) 2005 by SAP AG

All rights to both implementation and design are reserved.

Use and copying of this software and preparation of derivative works based
upon this software are not permitted.

Distribution of this software is restricted. SAP does not make any warranty
about the software, its performance or its conformity to any specification.

*/


import com.sap.spe.base.util.SystemInfo;
import com.sap.spc.product.Configuration;
import com.sap.vmc.internationalization.MessageResourceManager;
import com.sap.vmc.internationalization.ResourceException;
import com.sap.vmc.internationalization.ResourceManagerFactory;
import com.sap.vmc.logging.Args;
import com.sap.vmc.logging.Location;
import com.sap.vmc.logging.Severity;


/**
* @author d020787
*
* TODO To change the template for this generated type comment go to
* Window - Preferences - Java - Code Generation - Code and Comments
*/
public class ResourceAccessor {
private static final String EMPTY_STRING = "";

/**
 * The location in which tracing information is stored.
 */
private static final Location LOCATION = Location.getLocation(ResourceAccessor.class);

/**
 * The singleton resource manager instance used for the conversion
 * functionality.
 */
private static MessageResourceManager resManager = null;

/**
 * Gets a resource manager instance to be used for the conversion
 * functionality.
 *
 * @return the resource manager to be used for the conversion
 *         functionality or <code>null</code>
 */
public static MessageResourceManager get() {

    // check whether a resource manager instance has been
    // already created
    if (resManager == null) {
        try {
            ResourceManagerFactory factory = ResourceManagerFactory.createFactory();
            resManager = factory.getMessageResourceManager(Configuration.MESSAGE_CLASS);
        }
        catch (ResourceException ex) {
            logException(ex);
        }
    }

    return resManager;
}

private static void logException(ResourceException ex) {
	Configuration.CATEGORY.logThrowable(Severity.FATAL, LOCATION, Configuration.MESSAGE_CLASS, 1, "", ex);
}

public static String getMessage(int messageNumber, Object[] args) {
    try {
        return ResourceAccessor.get().getMessageResource(SystemInfo.defaultlocale.getLocale()).getMessage(messageNumber,
            args);
    }
    catch (ResourceException e) {
        logException(e);
        return EMPTY_STRING;
    }
}

public static String getMessage(int messageNumber) {
    try {
        return ResourceAccessor.get().getMessageResource(SystemInfo.defaultlocale.getLocale()).getMessage(messageNumber);
    }
    catch (ResourceException e) {
        logException(e);
        return EMPTY_STRING;
    }
}

public static String getMessage(String messageClass, int messageNumer) {
    try {
        return ResourceManagerFactory.createFactory().getMessageResourceManager(messageClass)
                                         .getMessageResource(SystemInfo.defaultlocale.getLocale()).getMessage(messageNumer);
    }
    catch (ResourceException ex) {
        logException(ex);
        return EMPTY_STRING;
    }
}

public static String getMessage(String messageClass, int messageNumer, Object[] args) {
    try {
        return ResourceManagerFactory.createFactory().getMessageResourceManager(messageClass)
                                         .getMessageResource(SystemInfo.defaultlocale.getLocale()).getMessage(messageNumer,
            args);
    }
    catch (ResourceException ex) {
        logException(ex);
        return EMPTY_STRING;
    }
}
}


