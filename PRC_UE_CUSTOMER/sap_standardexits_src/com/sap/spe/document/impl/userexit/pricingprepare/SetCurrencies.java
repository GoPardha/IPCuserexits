/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.document.impl.userexit.pricingprepare;

import com.sap.spe.document.userexit.IDocumentUserExitAccess;
import com.sap.spe.document.userexit.IItemUserExitAccess;
import com.sap.spe.document.userexit.PricingPrepareFormulaAdapter;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SetCurrencies extends PricingPrepareFormulaAdapter {
    public void addAttributeBindings(IItemUserExitAccess itemUserExitAccess) {
        IDocumentUserExitAccess document = itemUserExitAccess.getDocument();

        if (document.getDocumentCurrency() != null) {
            addAttribute(itemUserExitAccess, "DOCUMENT_CURRENCY", document.getDocumentCurrency().getUnitName());
        }

        if (document.getLocalCurrency() != null) {
            addAttribute(itemUserExitAccess, "LOCAL_CURRENCY", document.getLocalCurrency().getUnitName());
        }
    }
}
