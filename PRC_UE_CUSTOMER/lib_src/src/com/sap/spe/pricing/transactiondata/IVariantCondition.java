/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata;


/**
 * Interface for varcond-objects which can be created with the help of the pricing or conditionfinding engine.
 * This components should be added to a container and the whole container should then be available on
 * the salesorder item (which must implement the Iitem interface).
 */
public interface IVariantCondition {

    /**
     * @return my name.
     */
    public String getName();

    /**
     * @return my factor.
     */
    public double getFactor();

    /**
     * @return my description.
     */
    public String getDescription();
}
