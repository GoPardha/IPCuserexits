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


public interface IDomain extends Serializable {
    public String getName();

    // return vector of values for this domain
    public String[] getValues();

    // return vector of texts for this domain
    public String[] getTexts();

    /** Returns corresponding text of a value. */
    public String getText(String value);

    /** Return corresponding text of a char value. */
    public String getText(char value);
}
