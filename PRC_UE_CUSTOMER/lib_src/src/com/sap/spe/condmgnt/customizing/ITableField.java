/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.customizing;

import java.io.Serializable;

import com.sap.spe.text.IDataElement;


/**
 * Interface for an tableAttributeClass-object. There exists only one tableAttributeClass-object for
 * each attribute name.
 */
public interface ITableField extends Serializable {
    public static final byte EXTRA_SHORT_DESCRIPTION = IDataElement.C_XSHORT;
    public static final byte SHORT_DESCRIPTION = IDataElement.C_SHORT;
    public static final byte MEDIUM_DESCRIPTION = IDataElement.C_MEDIUM;
    public static final byte LONG_DESCRIPTION = IDataElement.C_LONG;

    public String getName();
    
    public boolean isGuid();

    public IApplicationField getApplicationField();

    public String getDescription();

    public String getDescription(byte textLength);

    public boolean isPartOfVariableKey();

    public boolean isPartOfVariableData();
}
