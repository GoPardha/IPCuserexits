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

import com.sap.spe.conversion.IAlternativeUnitFraction;
import com.sap.spe.conversion.IPhysicalValue;
import com.sap.sxe.util.math.Fraction;
import java.io.Serializable;


/** 
 * Interface for accessing the data of a single unit assigned to a product. 
 */
public interface IProductUnitEntry extends Serializable {
	/** @return the gross weight */
	public IPhysicalValue getGrossWeight();

	/** @return the netweight */
	public IPhysicalValue getNetWeight();

	/** @return the volume */
	public IPhysicalValue getVolume();

	/** @return the alternative unit fraction */
	public IAlternativeUnitFraction getAlternativeUnitFraction();

	/** @return the fraction */
	public Fraction getFraction();
}
