/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.conversion;


/**
 * A currency unit such as USD, DEM, FRF.
 */
public interface ICurrencyUnit extends IDimensionalUnit {

    /**
     * @return true, if the currency unit is the base currency unit of the
     *         european monetary union (EMU)
     */
    public boolean isEMUCurrency();
}
