/*******************************************************************************

	Copyright (c) 2002 by SAP AG

	All rights to both implementation and design are reserved.

	Use and copying of this software and preparation of derivative works based
	upon this software are not permitted.

	Distribution of this software is restricted. SAP does not make any warranty
	about the software, its performance or its conformity to any specification.

*******************************************************************************/
package com.sap.spc.document.userexit;

import com.sap.sce.front.base.Config;
import com.sap.spe.document.userexit.IItemUserExitAccess;

public interface ISPCItemUserExitAccess extends IItemUserExitAccess {
    
    /**
     * Returns the configuration.
	 */
	public Config getConfig();

    public boolean isRelevantForPricing();
}