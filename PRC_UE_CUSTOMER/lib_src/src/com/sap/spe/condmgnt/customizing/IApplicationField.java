/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.customizing;

public interface IApplicationField extends IField {
    public String getApplication();

    public char getType();

    public boolean isAccessTimestamp();

    public boolean isMultiValued();

    public boolean isItemField();

    public boolean isHeaderField();

    public interface Type {
        static final char HEADER_STRUCTURE = 'H';
        static final char ITEM_STRUCTURE = 'I';
        static final char MIXED_STRUCTURE = 'M';
        static final char NOT_CONTAINED = 'N';
        static final char HEADER_STRUCTURE_MULTI_VALUED = 'O';
        static final char ITEM_STRUCTURE_MULTI_VALUED = 'P';
        static final char MIXED_STRUCTURE_MULTI_VALUED = 'Q';
    }
}
