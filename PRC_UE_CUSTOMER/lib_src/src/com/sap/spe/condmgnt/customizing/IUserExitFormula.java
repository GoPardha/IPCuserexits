/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.customizing;

import com.sap.spe.condmgnt.customizing.userexit.IFormula;
import com.sap.spe.condmgnt.exception.FormulaIsMissingException;
import com.sap.vmc.logging.LocalizableCategory;
import com.sap.vmc.logging.Location;
import com.sap.vmc.monitor.jarm.IMonitor;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IUserExitFormula {
    public String getUsage();

    public String getApplication();

    public int getNumber();

    public String getName();

    public IUserExitType getType();

    public String getClassName();

    public String getAdapterClassName();

    public String getApplicationFieldName(String alias);

    public String[] getApplicationFieldNames();

    public IFormula getFormula() throws FormulaIsMissingException;
    
    public void logException(LocalizableCategory category, Location location, Exception exception);
    
    public IMonitor startJARM();
    
    public void endJARM(IMonitor monitor);
}
 