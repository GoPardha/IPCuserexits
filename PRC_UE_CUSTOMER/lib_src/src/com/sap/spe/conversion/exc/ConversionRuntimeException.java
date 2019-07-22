/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.conversion.exc;

import com.sap.spe.conversion.ConversionConstants;
import com.sap.vmc.exception.BaseRuntimeException;


public class ConversionRuntimeException extends BaseRuntimeException {
    public ConversionRuntimeException(int messageNumber, Object[] args, Throwable rootCause) {
        super(ConversionConstants.C_CONV_MSGAREA, messageNumber, args, rootCause);
    }
}
