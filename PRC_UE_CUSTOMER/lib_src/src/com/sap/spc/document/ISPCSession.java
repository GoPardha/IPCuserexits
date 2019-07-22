/************************************************************************

	Copyright (c) 1999 by SAP AG

	All rights to both implementation and design are reserved.

	Use and copying of this software and preparation of derivative works
	based upon this software are not permitted.

	Distribution of this software is restricted. SAP does not make any
	warranty about the software, its performance or its conformity to any
	specification.

**************************************************************************/
package com.sap.spc.document;

import java.util.Iterator;

import com.sap.spe.document.ISession;

/**Defines the interface to a
 * {@link SPCSession}.
 */
public interface ISPCSession extends ISession {
    
	public String addSPCDocument(ISPCDocument document);

	public Iterator getSPCDocuments();

	public ISPCDocument getSPCDocument(String documentId);

	public ISPCDocument removeSPCDocument(String documentId);
}