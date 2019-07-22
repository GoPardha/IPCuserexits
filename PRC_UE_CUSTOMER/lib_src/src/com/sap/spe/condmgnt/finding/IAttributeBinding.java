/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.finding;

import java.io.Serializable;

import com.sap.spe.condmgnt.customizing.IApplicationField;


/**
 * Interface for attributeBinding-objects which can be created with the help of
 * the pricing or conditionfinding engine.
 * This components should be added to a container and the whole container should
 * then be available on the item (which must implement the Iitem interface)
 * of a document.
 */
public interface IAttributeBinding extends Serializable {
    public final static String[] INITIAL_ATTRIBUTE_VALUE = new String[0];

    /**
     * Get the name of the application field
     * @return name
     */
    public String getName();

    /**
     * Get the application field for this attribute binding
     * @return application field.
     */
    public IApplicationField getApplicationField();

    /**
     * Get all values for this attribute
     * @return string array with all values
     */
    public String[] getValues();
}
