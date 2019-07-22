/*******************************************************************************

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*******************************************************************************/
package com.sap.spc.document.userexit;

import com.sap.sce.front.base.Instance;

/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SPCSubItemCreatedByConfigurationFormulaAdapter implements
        ISPCSubItemCreatedByConfigurationFormula {

    /* (non-Javadoc)
     * @see com.sap.spc.document.userexit.ISPCSubItemCreatedByConfigurationFormula#isRelevantForPricing(com.sap.spc.document.userexit.ISPCItemUserExitAccess, com.sap.sce.front.base.Instance)
     */
    public boolean isRelevantForPricing(ISPCItemUserExitAccess subItem,
            Instance instance) {
        return subItem.isRelevantForPricing();
    }
}
