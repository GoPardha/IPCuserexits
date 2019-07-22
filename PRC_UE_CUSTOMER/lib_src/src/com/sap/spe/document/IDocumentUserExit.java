/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.document;

import com.sap.spe.document.userexit.IDocumentUserExitAccess;


public interface IDocumentUserExit {
    public void initializeDocument(IDocumentUserExitAccess documentUserExitAccess);

    /**
     * @return the string array of header attribute names which is necessary to process
     * document user exit.
     */
    public String[] determineRelevantAttributes();
}
