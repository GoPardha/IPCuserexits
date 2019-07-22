/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.document.userexit;

import com.sap.spe.condmgnt.finding.IAttributeBinding;
import com.sap.spe.conversion.ICurrencyUnit;
import com.sap.spe.pricing.customizing.IPricingProcedure;


public interface IDocumentUserExitAccess {

    /**
     * @return the unit which is used for rounding.
     * Usually this should be 0. (For swisse it could be set to 5, because the
     * smallest unit of the currency are 5 Rappen.)
     */
    public int getUnitToBeRoundedTo();

    /**
     * @return the local curreny.
     */
    public ICurrencyUnit getLocalCurrency();

    /**
     * @return the currency of me.
     */
    public ICurrencyUnit getDocumentCurrency();

    /**
     * @return the document identification.
     */
    public String getId();

    /**@return a copy of my header attribute environment (an OrderedSet of
     * AttributeBindings)
     */
    public IAttributeBinding[] getAttributeEnvironment();

    /**@return the IAttributeBinding whose name matches attributeName. Returns null
     * if none found.
     */
    public IAttributeBinding getAttributeBinding(String attributeName);

    public IAttributeBinding addAttributeBinding(String attributeName, String[] attributeValues);

    /**
     * @sets the unit which is used for rounding.
     * Usually this should be 0. (For swisse it could be set to 5, because the
     * smallest unit of the currency are 5 Rappen.)
     */
    public void setUnitToBeRoundedTo(int unitToBeRoundedTo);

    /**
     * @return true if the attribute is relevant for pricing
     */
    public boolean isAttributeRelevantForPricing(String attributeName);
    
    public IPricingProcedure getPricingProcedure();
    
    public void setPricingCompleteRequiredDuringLoad(boolean required);

}
