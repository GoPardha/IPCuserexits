/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit;

import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.pricing.transactiondata.ILastPrice;


/**
 * Interface for last price object. The last price is determined for each
 * pricing item and available in some SPE userexits. It corresponds to the
 * pricing condition which holds the last price on the pricing item.
 */
public interface ILastPriceUserExit extends ILastPrice {
    public void setRate(ICurrencyValue rate);
    public void setValue(ICurrencyValue value);
}
