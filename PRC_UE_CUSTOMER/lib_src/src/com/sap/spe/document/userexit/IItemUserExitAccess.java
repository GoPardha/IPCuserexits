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
import com.sap.spe.conversion.IQuantityValue;
import com.sap.spe.pricing.transactiondata.IPricingProduct;
import com.sap.spe.pricing.transactiondata.IVariantCondition;


public interface IItemUserExitAccess {

    /** @return my material.
     */

    //give user access to product, ...accessing productHierarchy etc.
    public IPricingProduct getProduct();

    public IQuantityValue getProductQuantity();

    /**@return the IAttributeBinding whose name matches attributeName. Returns null
     * if none found. The item class guarantees that all relevant header attributes
     * are part of the header attribute environment. If a header attribute is not
     * set explicitly the value of the corresponding binding is null.
     */
    public IAttributeBinding getAttributeBinding(String attributeName);

    public IAttributeBinding addAttributeBinding(String attributeName, String attributeValue);

    /**Creates a new AttributeBinding and adds it to my header attribute environment.
     * If the environment contains already a binding named attributeName, that binding is replaced.
     * Example: addHeaderAttributeBinding("VKORG", "0001");
     * @return the IAttributeBinding replaced or null
     * @exception SPEIllegalArgumentException if attributeName or attributeValue is null
     */
    public IAttributeBinding addAttributeBinding(String attributeName, String[] attributeValue);

    /**Adds a variant condition (Varcond) to me.
     * @param key           is the variant key
     * @param factorString  a factor which is associated with the key
     * @param description   the description of the variant key for showing in condition screen
     */
    public void addVariantCondition(String key, double factor, String description);

    /**@return a Vector of variant conditions (Varconds)
     */
    public IVariantCondition[] getVariantConditions();

    /**Removes a variant condition (Varcond) from me.
     */
    public void removeVariantCondition(String key);

    /**
     * @return true if a attribute is set explicitly, false if it is set in the item user exits
     */
    public boolean isAttributeSetExplicitly(String attributeName);

    /**
     * @return the document of the item.
     */
    public IDocumentUserExitAccess getDocument();

    /**
     * @return the high level item.
     */
    public IItemUserExitAccess getHighLevelItem();

    /**
     *
     * @return the item Identification.
     */
    public String getId();

    /**
     * @return true if the attribute is relevant for pricing
     */
    public boolean isAttributeRelevantForPricing(String attributeName);
}
