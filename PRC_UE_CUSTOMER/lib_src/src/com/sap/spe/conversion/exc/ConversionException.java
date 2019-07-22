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
import com.sap.vmc.exception.BaseException;


public class ConversionException extends BaseException {
    public ConversionException(int messageNumber, Object[] args, Throwable rootCause) {
        super(ConversionConstants.C_CONV_MSGAREA, messageNumber, args, rootCause);
    }

    public ConversionException(String msgClass, int messageNumber, Object[] args, Throwable rootCause) {
        super(msgClass, messageNumber, args, rootCause);
    }
}
