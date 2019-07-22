/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.customizing;

public interface ICopyType {
    public String getName();

    public int getFormulaNumber();

    public char getCopyAbsoluteItemConditions();

    public String getDescription();

    public interface CopyAbsoluteItemConditions {
        public static final char APPLY_QUANTITY_RATIO = 'A';
        public static final char COPY_ONE_TO_ONE = 'B';
    }
}
