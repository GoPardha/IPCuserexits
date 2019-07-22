/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.document;

import com.sap.spe.condmgnt.exception.ConditionInconsistentDBException;
import com.sap.spe.condmgnt.finding.IRelevantAttributes;
import com.sap.spe.conversion.ICurrencyUnit;
import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.spe.pricing.transactiondata.IPricingProduct;


public interface IDocumentEngine {
    public IPricingProduct createPricingProduct(String productId, String externalProductId, String description,
        String internalBaseUnitName)
        throws ConversionMissingDataException;

    public IDocument createDocument(String application, String usage, String documentCurrencyUnit);

    public IDocument createDocument(String application, String usage, ICurrencyUnit documentCurrencyUnit);
    
    public IRelevantAttributes getRelevantAttributes(String application, String usage, String pricingProcedure)
        throws ConditionInconsistentDBException;

    public ISession createSession();
}
