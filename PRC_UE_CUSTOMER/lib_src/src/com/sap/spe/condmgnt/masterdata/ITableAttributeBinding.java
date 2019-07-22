/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.masterdata;

import com.sap.spe.condmgnt.customizing.ITableField;


/**
 * A TableAttributeBinding represents a variable/value pair or binding.
 * It has an TableAttributeClass, a name which is the same as
 * its TableAttributeClass, and a value.
 */

// TODO: tm
public interface ITableAttributeBinding {
    public String getName();

    public ITableField getTableAttributeClass();

    public String getValue();

    public boolean isHeaderAttributeBinding();

    public boolean isMultipleAttributeValue();

    public String[] getValues();

    public boolean isKeyFieldNotUsed();

    public void setKeyFieldNotUsed(boolean b);
    
    public boolean isDirectValueAssigned();
    
    public void setDirectValueUsageIndicator(boolean directValueIndicator);
}
