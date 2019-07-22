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

/**Common product interface of SPE and SCE.
 */
public interface ISPCProduct extends ISPEProduct, ISCEProduct, Serializable {

	public static final String PRODUCT_GUID_NULL = "00000000000000000000000000000000";

    /**
	 * Returns true if this product is configurable. Note that this doesn't
	 * mean that a SCE knowledge base for this product exists. It only means
	 * that it is a KMAT in R/3.
	 * @return if this product is configurable
	 */
	public boolean isConfigurable();

    /**
	 * Returns true if only variant matching should be performed in configuration.
	 */
	public boolean isOnlyVariantMatching();

	/**
	 * * Returns the GUID of the standard product (configuration issue).
	 *
	 */
	public String getStandardProductId()  throws ProductInconsistentDBException;

	/**Gets a string representation for visualization purpose.
	 * @return a string representation
	 */
	public String toString();

	/**Gets the ID.
	 * @return the ID
	 */
	public String getId();

	/**Gets the external ID w/o conversion of the db format.
	 * @return the external ID using the db format
	 */
	public String getDbExternalId();

	/**Gets the product description in the default language (English).
	 * @return the default product description
	 */
	public String getDescription() ;

	/**Gets a unit
	 * @return the requested unit
	 */
	//TODO: entfernen? -> getVolume(unit)...
	public IProductUnitEntry getUnitEntry(String unit);

    /**Gets the External ID.
     * @return the ID
     */
    public String getExternalId();
}

