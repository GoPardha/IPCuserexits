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

import java.io.Serializable;

import com.sap.spc.product.exception.ProductInconsistentDBException;

/**
 * provides info about referenced sets
 */
public interface IProductSetTypeReference extends Serializable {
	public boolean isSetTypeReferenced(String fragmentTypeGUID);

	public boolean isSetTypeReferenced(IProductSetInfo productSetInfo) throws  ProductInconsistentDBException;
}
