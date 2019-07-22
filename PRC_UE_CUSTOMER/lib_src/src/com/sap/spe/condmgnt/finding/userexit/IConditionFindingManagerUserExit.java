/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.finding.userexit;

import com.sap.spe.condmgnt.finding.IConditionFindingManagerCommon;


/**
 * Interface for the conditionFindingManager-object. This object is the main entry point
 * for the search of condition records. This functionality is used by the pricingItem (by subclassing)
 * for the search of pricing condition records.
 * This interface contains the methods which can be used in userexits.
 */
public interface IConditionFindingManagerUserExit extends IConditionFindingManagerCommon {

    /**
     * @return the value which has been set for an attribute. It is ensured that the attribute value is never null.
     * In case of a multi-valued attribute the first value is returned. 
     * In case no value is set an empty string is returned.
     */
    public String getAttributeValue(String name);

    /**
     * @return the values which has been set for an attribute. 
     * In case that no values are set a zero-length string array is returned.
     */
    public String[] getAttributeValues(String name);
}
