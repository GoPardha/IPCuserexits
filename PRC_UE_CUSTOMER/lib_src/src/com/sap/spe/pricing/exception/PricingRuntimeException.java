/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.exception;

import com.sap.vmc.exception.BaseRuntimeException;


public class PricingRuntimeException extends BaseRuntimeException {
    public PricingRuntimeException(String messageClass, int messageNumber, Object[] args, Throwable rootCause) {
        super(messageClass, messageNumber, args, rootCause);
    }

    //	TODO  hack
    public PricingRuntimeException(String message) {
        super(message);
    }
}
