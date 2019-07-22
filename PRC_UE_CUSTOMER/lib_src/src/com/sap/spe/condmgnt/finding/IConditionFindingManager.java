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

import org.w3c.dom.Document;

import com.sap.sxe.sys.SAPTimestamp;


/**
 * Interface for the conditionFindingManager-object. This object is the main entry point
 * for the search of condition records. This functionality is used by the pricingItem (by subclassing)
 * for the search of pricing condition records.
 */
public interface IConditionFindingManager extends IConditionFindingManagerCommon, Serializable {

    /**
     * @return a vector of the ICondition-Objects which have been found.
     */
    public ICondition[] determineConditions();

    public IConditionFindingTracer getConditionFindingTracer();

    public boolean isTracing();

    /** Activates the pricing trace for condition determination. */
    public void setPerformTrace(boolean performAnalysis);

    /**
     * If set to true the determination stops after the first
     * condition record is found (like in the R/3). The default behaviour is to
     * get all the condition records and return it to the calling application
     */
    public void setStopAfterFirstConditionRecordFound(boolean stopAfterFirstConditionRecordFound);
    
    public boolean getStopAfterFirstConditionRecordFound();    
    

    public Document getTraceAsXML();

    public String getTraceAsXMLString(Document xmlDocument);

    public String getTraceAsXMLString();

    /**
     * @return the value which has been set for an attribute.
     */
    public IAttributeBinding getAttributeBinding(String name);

    public IAttributeBinding setAttributeBinding(String attributeName, String[] attributeValues);

    public IAttributeBinding setAttributeBinding(IAttributeBinding attributeBinding);

    public void setConditionAccessTimestamp(String conditionAccessTimestampName,
        SAPTimestamp conditionAccessTimestampValue);

    public void setConditionAccessTimestamp(IConditionAccessTimestamp conditionAccessTimestamp);
    
    public void setSimulation(boolean simulation);
}
