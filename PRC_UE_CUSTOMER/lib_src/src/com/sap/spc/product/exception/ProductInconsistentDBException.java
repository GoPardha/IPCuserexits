/************************************************************************

Copyright (c) 2004 by SAP AG

All rights to both implementation and design are reserved.

Use and copying of this software and preparation of derivative works
based upon this software are not permitted.

Distribution of this software is restricted. SAP does not make any
warranty about the software, its performance or its conformity to any
specification.

**************************************************************************/
package com.sap.spc.product.exception;


import com.sap.spc.product.Configuration;
import com.sap.vmc.exception.BaseException;


public class ProductInconsistentDBException extends BaseException {
	public ProductInconsistentDBException(int messageNumber, Object[] args,  Throwable rootCause ) {
	       super(Configuration.MESSAGE_CLASS, messageNumber, args, rootCause);
	}
 

}
