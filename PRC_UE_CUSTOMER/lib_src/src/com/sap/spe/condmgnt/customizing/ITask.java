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


public interface ITask extends Serializable {
    public IApplication getApplication();

    public IUsage getUsage();

    public String getDescription();

    public boolean isScaleAdditionalDataExisting();

    public char getEngineType();

    public interface EngineType {
        public final static char ABAP = 'A';
        public final static char JAVA = 'B';
    }
}
