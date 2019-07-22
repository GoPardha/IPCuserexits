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
import com.sap.spe.conversion.IQuantityValue;
import com.sap.spe.pricing.customizing.ICopyType;
import com.sap.spe.pricing.customizing.IPricingType;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IPricingCopyFormula extends IFormula {
    public final static String TYPE_NAME = "CPY";

    /**
     * This method is called when pricing conditions are copied.
     * If the formulaNumber is smaller than 600 or greater than 999
     * coding in a standard class is processed else the code of the class provided
     * by the customer is processed (600 <= formulaNumber <= 999).
     * The formulaNumber should be used to specify which logic will be processed.
     * This number is provided by the customizing of the copy type.
     * NB: copyType also contains the formulaNumber
     */
    public void pricingCopy(IPricingDocumentUserExit pricingDocument, IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition, IPricingType pricingType, ICopyType copyType,
        IQuantityValue sourceSalesQuantity);
}
