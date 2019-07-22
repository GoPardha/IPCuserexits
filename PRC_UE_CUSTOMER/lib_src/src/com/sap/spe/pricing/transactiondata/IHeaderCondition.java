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
 * A header condition is a pricing condition which has been typed in manually on the
 * document level. It contains information copied from the pricing knowledge base
 * (from the customizing of the condition type and the pricing procedure), the rate manually typed
 * in, and from the result of the calculation.
 */
public interface IHeaderCondition extends IPricingCondition {

    /**
     * Returns a value which identifies a header condition in a document.
     */
    public int getHeaderCounter();
}
