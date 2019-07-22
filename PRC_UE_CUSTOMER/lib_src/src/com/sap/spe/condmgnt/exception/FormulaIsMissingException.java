/*******************************************************************************

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*******************************************************************************/
package com.sap.spe.condmgnt.exception;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FormulaIsMissingException extends ConditionException {
    
    public interface Reason {
        public final static int NOT_MAINTAINED = 20;
        public final static int NOT_IMPLEMENTED = 21;
        public final static int FORMULA_TYPE_IS_UNKNOWN = 22;
    }
     
    public FormulaIsMissingException(int reason, String application, String usage, String formulaType, int formulaNumber) {
        super(reason, 
                new Object[] { formulaType, application, usage, String.valueOf(formulaNumber) }, 
                null);
    }
    
    public FormulaIsMissingException(int reason, String usage, String formulaType) {
        super(reason, 
                new Object[] { usage, formulaType }, 
                null);
    }
}
