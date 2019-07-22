/************************************************************************

	Copyright (c) 2000 by SAP AG

	All rights to both implementation and design are reserved.

	Use and copying of this software and preparation of derivative works
	based upon this software are not permitted.

	Distribution of this software is restricted. SAP does not make any
	warranty about the software, its performance or its conformity to any
	specification.

**************************************************************************/
package com.sap.spc.product;

import java.io.Serializable;

/**
 * Interface to be implemented by a product id converter
 * e.g. to convert the external product id according to function module
 * CONVERSION_EXIT_PRID1_OUTPUT
 */
public interface IProductIdConverter extends Serializable{

	/**
	 * Will be called after cration of the product engine.
	 * The @param productEngine gives access to product related information
	 */
    public void init(IProductEngine productEngine);

	/**
	 * convert external format to db format
	 * This method will be used when processing the the createSPCProduct-methods of the product engine
	 */
    public String performInputConversion(String productId, String logicalSystem, String productType);

	/**
	 * convert db format to external format
	 * This method will be used when processing method ISPCProduct.getExternalId()
	 */
    public String performOutputConversion(String productId, String logicalSystem, String productType);

}