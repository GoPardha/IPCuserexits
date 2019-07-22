/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.finding;


/**
 * Interface of the tracerAttributeMap-object. This object stores information about the
 * mapping between conditionTable-keyfields and document attributes.
 * A list of the tracerAttributeMap-objects is stored by the tracerAccess.
 */
public interface ITracerAttributeMap {

    /**
     * @return the tracerAccess I am assigned to.
     */
    public ITracerAccess getTracerAccess();

    /**
     * @return my tableAttributeClassName.
     */
    public String getTableAttributeClassName();

    public String getTableAttributeClassDescription(byte textlength);

    /**
     * @return the value of my document attribute.
     */
    public String getDocumentAttributeClassValue();

    /**
     * @return the name of my document attribute.
     */
    public String getDocumentAttributeClassName();

    /**
     * @return the description of my document attribute.
     */
    public String getDocumentAttributeClassDescription(byte textlength);

    /**
     * @return true if the flag 'directValue' is true for my attributeMap.
     */
    public boolean AttributeClassIsConstant();
}
