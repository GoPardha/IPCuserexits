/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.document.impl.userexit.pricinginit;

import com.sap.spe.document.userexit.IDocumentUserExitAccess;
import com.sap.spe.document.userexit.PricingInitFormulaAdapter;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SetUnitsToBeRoundedTo extends PricingInitFormulaAdapter {
    public void initializeDocument(IDocumentUserExitAccess documentUserExitAccess) {

        // sets the unit which is used for rounding. For swisse it is set to 5, because the
        // smallest unit of the currency are 5 Rappen.
        setUnitToBeRoundedTo(documentUserExitAccess, "CHF", 5);
        setUnitToBeRoundedTo(documentUserExitAccess, "ITL", 10);
        setUnitToBeRoundedTo(documentUserExitAccess, "SKK", 10);
        setUnitToBeRoundedTo(documentUserExitAccess, "CZK", 10);
        setUnitToBeRoundedTo(documentUserExitAccess, "PTE", 100);
    }

    private void setUnitToBeRoundedTo(IDocumentUserExitAccess documentUserExitAccess, String currency,
        int unitToBeRoundedTo) {
        if (documentUserExitAccess.getDocumentCurrency().getUnitName().equals(currency)) {
            documentUserExitAccess.setUnitToBeRoundedTo(unitToBeRoundedTo);
        }
    }
}
