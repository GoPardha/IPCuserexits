/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.masterdata;

import com.sap.spe.condmgnt.masterdata.IScale;
import com.sap.spe.condmgnt.masterdata.IScaleDimensionAmount;
import com.sap.spe.conversion.IDimensionalUnit;
import com.sap.spe.conversion.IDimensionalValue;


/**
 * @author d036612
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IPricingScale extends IScale {
    public char getScaleType(int dimensionNumber);

    public IScaleDimensionAmount getScaleDimensionAmount(int dimensionNumber, IDimensionalValue value);

    public IDimensionalUnit getScaleUnit(int dimensionNumber);

    public IPricingScaleRate getScaleRate(IScaleDimensionAmount[] scaleDimensionAmounts);

    public IPricingScaleRate getScaleRate(IPricingScaleLevel level);
}
