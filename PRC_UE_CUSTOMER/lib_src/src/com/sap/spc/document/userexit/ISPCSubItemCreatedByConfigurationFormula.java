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
import com.sap.spe.condmgnt.customizing.userexit.IFormula;

/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ISPCSubItemCreatedByConfigurationFormula extends IFormula {
    
    public final static String TYPE_NAME = "CFG";
    
	/**
	 * This method is processed when a new sub item is created by the configuration (SCE).
	 * @param subItem the sub item created by SCE
	 * @param instance the instance for which the sub item is created  
	 * @return true if the sub item is relevant for pricing otherwise false.
	 */
	public boolean isRelevantForPricing(ISPCItemUserExitAccess subItem, Instance instance);
}
