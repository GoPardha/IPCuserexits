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

import com.sap.spc.product.exception.ProductInconsistentDBException;

/**The SCE-specific view of an product.
 * This interface is not completly implemented yet and is
 * currently not used.
 */
public interface ISCEProduct extends Serializable {

	public String getId();

	public String getDescription()  throws ProductInconsistentDBException;
}
