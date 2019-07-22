/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.text;

import java.io.Serializable;


public interface IDataElement extends Serializable {
    public static final byte C_XSHORT = 1;
    public static final byte C_SHORT = 2;
    public static final byte C_MEDIUM = 3;
    public static final byte C_LONG = 4;

    public String getDescriptionText(byte length);

    public String getName();

    public String getTextExtraShort();

    public String getTextShort();

    public String getTextMedium();

    public String getTextLong();
}
