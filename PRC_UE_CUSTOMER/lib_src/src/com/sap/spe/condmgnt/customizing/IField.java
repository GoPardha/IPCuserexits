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
import java.util.List;

import com.sap.spe.text.IDataElement;


public interface IField extends Serializable {
    public String getName();

    public char getFieldOccurrence();

    public boolean isFieldInDatabase();

    public boolean isUsedInScale();

    public boolean isUsedInRateTable();

    public IDataElement getDataElement();

    public String getDataElementName();

    public String getDescription();

    public String getDescription(byte length);
    
    public List getFieldRelation();
    
    public interface FieldOccurence extends Serializable {
        static final char PURELY_INTERNAL_FIELD = 'A';
        static final char PURELY_EXTERNAL_FIELD = 'B';
        static final char INTERNAL_AND_EXTERNAL_USAGE = 'C';
        static final char PURE_ACCESS_FIELD = 'D';
        static final char INTERNAL_AND_EXTERNAL_USAGE_BUT_NOT_VISIBLE_IN_UI = 'E';
        static final char INTERNAL_AND_EXTERNAL_USAGE_BUT_NOT_IN_UI_AND_DB = 'F';
    }
}
