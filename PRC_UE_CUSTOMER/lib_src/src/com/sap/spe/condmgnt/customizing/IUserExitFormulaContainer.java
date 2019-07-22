/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.customizing;

import com.sap.spe.condmgnt.exception.FormulaIsMissingException;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IUserExitFormulaContainer {
    public String getApplication();

    //public String getUsage();
    public IUserExitFormula getFormula(int number) throws FormulaIsMissingException;

    public IUserExitFormula[] getFormulas();

    public IUserExitType getType();
}
