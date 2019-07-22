/************************************************************************

	Copyright (c) 2003 by SAP AG

	All rights to both implementation and design are reserved.

	Use and copying of this software and preparation of derivative works
	based upon this software are not permitted.

	Distribution of this software is restricted. SAP does not make any
	warranty about the software, its performance or its conformity to any
	specification.

**************************************************************************/
package com.sap.spc.product;

import com.sap.spc.product.exception.ProductInconsistentDBException;

/**
 * provides info about an product set - e.g. guid of fragment type
 */
public interface IProductSetInfo {

	public String getFragmentTypeGUID()   throws ProductInconsistentDBException;

}
