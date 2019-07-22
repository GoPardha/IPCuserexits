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
 * A quantity unit such as KG, M or piece with reference to a product.
 */
public interface IQuantityUnit extends IPhysicalUnit {

    /**
     * @return the conversion product I am belonging to.
     */
    public IConversionProduct getProduct();
}
