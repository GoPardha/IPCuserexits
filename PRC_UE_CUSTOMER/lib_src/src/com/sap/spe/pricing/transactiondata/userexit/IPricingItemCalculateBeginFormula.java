/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit;

import com.sap.spe.condmgnt.customizing.userexit.IFormula;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IPricingItemCalculateBeginFormula extends IFormula {
    public final static String TYPE_NAME = "CAB";

    /**
     * This method is processed at the beginning of the method 'calculate' of the
     * pricing item. This method is processed e.g. when the method 'pricing' is used.
     */
    public void calculationBegin(IPricingDocumentUserExit prDocument, IPricingItemUserExit prItem);
}
