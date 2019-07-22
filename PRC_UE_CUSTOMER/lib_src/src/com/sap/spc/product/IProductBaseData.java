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
 * Interface for accessing the product base data.
 */
public interface IProductBaseData  extends Serializable {
	
//	public String getProductID();

	public String getProductGUID();

	public String getLogicalSystem();

	public String getProductType();

	public String getObjectFamily();

	public String getReferencedGUID();

	public boolean isIndividualObject();

	public boolean isConfigurable();

	public boolean isOnlyVariantMatching();

	public String getDbProductID();

}