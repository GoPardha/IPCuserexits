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

import com.sap.spe.conversion.IPhysicalValue;
import com.sap.spe.conversion.IQuantityUnit;
import com.sap.spe.pricing.transactiondata.IPricingProduct;
import java.io.Serializable;


/**The SPE-specific view of an product.
 */
public interface ISPEProduct extends IPricingProduct, Serializable {

	/** @return the gross weight */
	public IPhysicalValue getGrossWeight();

	/** @return the netweight */
	public IPhysicalValue getNetWeight();

	/** @return the volume */
	public IPhysicalValue getVolume();

/*	public String getDivision();

	public String getSalesOrganisation();

	public String getDistributionChannel();

	public String getRebateGroup();

	public String getCommissionGroup();

	public boolean getCashDiscountIndicator();*/

	public IQuantityUnit getSalesUnit();

/*	public String getPricingReferenceProduct();

	public String getPricingGroup();

	public String getPricingGroup1();

	public String getPricingGroup2();

	public String getPricingGroup3();

	public String getPricingGroup4();

	public String getPricingGroup5();

	public String getDepartureCountry();

	public String getDepartureRegion();*/

	public IPhysicalValue getGrossWeight(String unit);

	public IPhysicalValue getNetWeight(String unit);

	public IPhysicalValue getVolume(String unit);


}
