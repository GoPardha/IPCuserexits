/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.document;

import java.util.Iterator;


/**Defines the interface to a
 * {@link Session}.
 */
public interface ISession {
    public String addDocument(IDocument document);

    public IDocument getDocument(String documentID);

    public Iterator getDocuments();

    public IDocument removeDocument(String documentID);
}
