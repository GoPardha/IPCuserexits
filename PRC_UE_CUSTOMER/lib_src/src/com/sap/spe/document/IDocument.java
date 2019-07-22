/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.document;

import com.sap.spe.base.logging.Protocol;
import com.sap.spe.condmgnt.exception.ConditionInconsistentDBException;
import com.sap.spe.condmgnt.finding.IAttributeBinding;
import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.spe.conversion.exc.CurrencyConversionException;
import com.sap.spe.pricing.transactiondata.IDocumentBase;
import com.sap.spe.pricing.transactiondata.IExternalPricingConditions;
import com.sap.spe.pricing.transactiondata.IPricingCondition;
import com.sap.spe.pricing.transactiondata.IPricingDocument;
import com.sap.spe.pricing.transactiondata.IPricingProduct;
import com.sap.sxe.sys.SAPDate;


public interface IDocument extends IDocumentBase {
	
    public IItem addItem(IPricingProduct product);

    /**@return my internal ID
     */
    public String getId();

    /**@return my external ID
     */
    public String getExternalId();

    /**Sets the external ID.
     */
    public void setExternalId(String externalId);

    /**@return the net value of my pricing document that was calculated
     * by
     * @see #pricing
     * @see #pricingGroupConditionProcessing
     */
    public ICurrencyValue getNetValue();

    /**@return the tax value of my pricing document that was calculated
     * by
     * @see #pricing
     * @see #pricingGroupConditionProcessing
     */
    public ICurrencyValue getTaxValue();

    /** @return the gross value of my pricing document which is the sum of the net value and
     * the tax value.
     */
    public ICurrencyValue getGrossValue();

    /** @return a vector of Iitems of the external (sales) document. This is needed if a pricing document
     * should be loaded from the local database. */
    public IItem[] getItems();

    public IItem getItem(String itemId);

    /**Removes all my items.
     */
    public void removeAllItems();

    /**Removes item from my list of Items.
     */
    public void removeItem(String itemId);

    /**        @return my pricing document.
     */
    public IPricingDocument getPricingDocument();

    public Protocol getProtocol();

    /**        @return my PopUp protocol
     */
    public Protocol getPopUpProtocol();

    /**        clear the PopUp protocol
     */
    public void clearPopUpProtocol();

    /**@return a copy of my header attribute environment (an OrderedSet of
     * AttributeBindings)
     */
    public IAttributeBinding[] getAttributeEnvironment();

    /**@return the IAttributeBinding whose name matches attributeName. Returns null
     * if none found.
     */
    public IAttributeBinding getAttributeBinding(String attributeName);

    /**Creates a new AttributeBinding and adds it to my header attribute environment.
     * If the environment contains already a binding named attributeName, that binding is replaced.
     * @return the IAttributeBinding replaced or null
     * @exception SPEIllegalArgumentException if attributeName or attributeValue is null
     */
    public IAttributeBinding setAttributeBinding(String attributeName, String[] attributeValue);

    public void addPricingProcedure(String pricingProcedureName) throws ConditionInconsistentDBException;

    /**Changes my document currency and my PricingDocument's document currency,
     * and invokes pricing.
     */
    public void setDocumentCurrency(String documentCurrencyUnitName)
        throws CurrencyConversionException, ConversionMissingDataException;  //TODO, SXEInconsistentDBException;

    /**Changes my document currency and my PricingDocument's local currency,
     * and invokes pricing.
     */
    public void setLocalCurrency(String localCurrencyUnitName)
        throws ConversionMissingDataException, CurrencyConversionException;  //TODO, SXEInconsistentDBException;

    /**Invokes pricing on all items of my pricing document.
     */
    public void pricing() throws ConversionMissingDataException;  //TODO, SXEInconsistentDBException;

    /** Set the flag which determines if groupconditions should always be redetermined. The default value is
     *  true. For Internet sales this flag probably should be set to false.
     */
    public void setAlwaysPerformGroupConditionProcessing(boolean alwaysPerformGroupConditionProcessing);

    /** @return the flag which controls if group conditions are always redetermined.
     */
    public boolean getAlwaysPerformGroupConditionProcessing();

    /**Invokes group condition processing on my pricing document.
     */
    public void pricingGroupConditionProcessing();

    /** @return a vector of IPricingCondition for all items of the document.
     * This is the detailed result of pricing.
     */
    public IPricingCondition[] getPricingConditions();

    /**
     * Loads the pricing document for this document from the database.
     */
    public void loadPricingDocument();  // TODO: throws ConversionInconsistentDBException, SXEInconsistentDBException, SXEException;

    public void loadPricingDocument(IExternalPricingConditions externalPricingConditions);

    //  	TODO:throws ConversionInconsistentDBException, SXEInconsistentDBException, SXEException;
    public boolean getSuppressExchangeRateDetermination();

    public void setSuppressExchangeRateDetermination(boolean suppressPricing);

    public void addSourcePricingItem(String sourceDocumentId, String sourceItemId);

    /** Sets the edit mode
     */
    public void setEditMode(char editMode);

    /**Sets the document's partial processing mode
     */
    public void setPartialProcessing(boolean partialProcessing);

    /**Sets the authority level the user has to display and edit this document's conditions
     */
    public void setAuthorityLevel(int authorityForDisplay, int authorityForEdit);

    /**Sets the document's flag if prices with the rate 0 are set to inactive
     */
    public void setKeepZeroPricesActive(boolean keepZeroPricesActive);

    /** Sets the date that will be used to check if a currency is expired
     */
    public void setExpiringCheckDate(SAPDate expiringCheckDate);

    public void setId(String documentId);

    public void setBusinessObjectType(String businessObjectType);

    public void setUnitToBeRoundedTo(int unitToBeRoundedTo);

    public void setOnlySpecifiedUsage(boolean onlySpecifiedUsage);
    
    /**
     * Activates the classic pricing trace (condition determination)
     */
    public void setPerformTrace(boolean performTrace);

    /**
     * Activates the calculation pricing trace (e.g. processing of user exits, 
     * scales, currency and quantity conversion, etc).
     */
    public void setPerformCalcTrace(boolean performCalcTrace);
    
	public void setSimulation(boolean simulation);    
	
	/**
	 * set return document indicator at document(header) level
	 * @param indicator boolean indicator
	 */
	public void setReturnDocumentIndicator(boolean indicator);
	
	/**
	 * set document in lazy (delayed) item and condition loading mode
	 * @param lazyItemLoad lazy loading indicator
	 */
	public void setLazyItemLoadMode(boolean lazyItemLoad);
	 /**
     * get header level pricing status for this document. It returns TRUE if pricing at 
     * header level is already done otherwise return FALSE.
     * @return header level pricing completion status.
     */	
	public boolean getHdrLevelPricingCompletionStatus();
	
	/**
	 * set option for optimized group process handling
	 * @param optimizedGroupProcess optimized group process mode
	 */
	public void setOptimizedGroupProcessMode(boolean optimizedGroupProcess);
	
	/**
	 * set this document as a lean document
	 * Lean document should be used only for interactive configuration in decoupled pricing and 
	 * configuration scenario.
	 * By default newly created document is not considered as a lean document. For that indicator 
	 * need to be set via this method
	 * @param leanDocIndicator lean document indicator
	 */
	public void setLeanDocumentIndicator(boolean leanDocIndicator);
	
	/**
	 * Sets the field Name for the variant condition key. 
	 * By default it is "VARCOND".
	 * @param fieldName
	 */
	public void setVarcondFieldname(String fieldName);
	
	/** 
	 * Sets information about the calling client 
	 * (e.g. 'A' indicates that caller is the Pricing ABAP layer) 
	 */
	public void setClientInfo(char clientInfo);
	
}
