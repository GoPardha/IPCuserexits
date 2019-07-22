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
 * Interface for accessing the product type (e.g. material, service) specific data.
 */
public interface IProductTypeData extends IProductBaseSet, Serializable {
	/**
    * @deprecated  use IProductUnits.getBaseUOM() instead.
	*/
	public String getBaseUOM();

	public String getDivision();

}
