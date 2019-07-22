/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.conversion.exc;

public final class CurrencyConversionException extends ConversionException {
    public CurrencyConversionException(int messageNumber, Object[] args, Throwable rootCause) {
        super(messageNumber, args, rootCause);
    }

    public CurrencyConversionException(String msgClass, int messageNumber, Object[] args, Throwable rootCause) {
        super(msgClass, messageNumber, args, rootCause);
    }
}
