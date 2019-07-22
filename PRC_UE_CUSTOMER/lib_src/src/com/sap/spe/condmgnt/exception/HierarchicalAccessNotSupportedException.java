package com.sap.spe.condmgnt.exception;

import com.sap.spe.condmgnt.Configuration;
import com.sap.vmc.exception.BaseRuntimeException;

/** 
 * This exception is thrown when hierarchical accesses are used for pricing. 
 * The hierarchical accesses functionality is no longer supported.
 */
public class HierarchicalAccessNotSupportedException extends BaseRuntimeException {
	 
	private static final long serialVersionUID = 1L;

	public HierarchicalAccessNotSupportedException(int messageNumber, Object[] args, Throwable rootCause) {
	        super(Configuration.MESSAGE_CLASS, messageNumber, args, rootCause);
	}
}
