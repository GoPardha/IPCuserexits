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
 * Interface for the conditionFindingManager-object. This object is the main entry point
 * for the search of condition records. This functionality is used by the pricingItem (by subclassing)
 * for the search of pricing condition records.
 */
public interface IConditionFindingManagerCommon {

    /**
     * @return the application which is used for the search of condition records.
     */
    public String getApplication();

    /**
     * @return the usage which is used for the search of condition records.
     */
    public String getUsage();
}
