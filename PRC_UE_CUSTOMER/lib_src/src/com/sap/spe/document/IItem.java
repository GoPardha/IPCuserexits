/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.document;

import java.io.Serializable;
import java.math.BigDecimal;

import com.sap.spe.base.logging.Protocol;
import com.sap.spe.base.sys.TimePeriod;
import com.sap.spe.condmgnt.finding.IAttributeBinding;
import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.conversion.IExchangeRate;
import com.sap.spe.conversion.IPhysicalValue;
import com.sap.spe.conversion.IQuantityValue;
import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.spe.conversion.exc.CurrencyConversionException;
import com.sap.spe.pricing.transactiondata.IExternalDataSource;
import com.sap.spe.pricing.transactiondata.IExternalPricingCondition;
import com.sap.spe.pricing.transactiondata.IExternalPricingConditions;
import com.sap.spe.pricing.transactiondata.IItemBase;
import com.sap.spe.pricing.transactiondata.IPricingCondition;
import com.sap.spe.pricing.transactiondata.IPricingItem;
import com.sap.spe.pricing.transactiondata.IPricingProduct;
import com.sap.spe.pricing.transactiondata.IVariantCondition;
import com.sap.sxe.sys.SAPDate;
import com.sap.sxe.sys.SAPTimestamp;


public interface IItem extends IItemBase, Serializable {

    /**@return my pricing item. */
    public IPricingItem getPricingItem();

    /**@return my internal id. */
    public String getId();

    /** Return my pricing conditions. */
    public IPricingCondition[] getPricingConditions();

    public Protocol getProtocol();

    /** @return my PopUp protocol
     */
    public Protocol getPopUpProtocol();

    /**        resets the PopUp protocol
     */
    public void clearPopUpProtocol();

    public IAttributeBinding getAttributeBinding(String attributeName);

    public IAttributeBinding setAttributeBinding(String attributeName, String[] attributeValues);

    public IExternalDataSource[] getExternalDataSources();

    /**Adds a variant condition (Varcond) to me.
     */
    public void addVariantCondition(String key, double factor, String description);
    
    /**
     * imprort externalized variant conditions (for config session). This should be used
     * only for just copying variant conditions to item
     * currently designed only for configuartion session usage
     * @param variantCondns array of variant conditions
     */
    public void importVariantConditions(IVariantCondition[] variantCondns);

    /**Removes a variant condition (Varcond) from me.
     */
    public void removeVariantCondition(String key);

    /**Removes all variant conditions (Varcond) from me.
     */
    public void removeAllVariantConditions();

    /**Turns the pricing analysis on or off.
     */
    public void setPerformPricingAnalysis(boolean b);

    /**Performs pricing on me only.
     */
    public void pricing() throws ConversionMissingDataException;  // TODO, SXEInconsistentDBException;

    /**Performs pricing on me only.
     */
    public void pricing(boolean redetermineAllConditions)
        throws ConversionMissingDataException;  // TODO, SXEInconsistentDBException;

    /**@return my net value.
     */
    public ICurrencyValue getNetValue();

    /**@return my tax value.
     */
    public ICurrencyValue getTaxValue();

    /** @return the gross value which is the sum of net value and tax value.
     */
    public ICurrencyValue getGrossValue();

    /**@return the document I belong to.
     */
    public IDocument getDocument();

    /** @return the vector of items for my direct subItems. */
    public IItem[] getSubItems();

    /** @return my main item.         */
    public IItem getHighLevelItem();

    /**Set my highlevel item I belong to (in a bill of materials).
     */
    public void setHighLevelItem(IItem highLevelItem);

    /**@return my subitems and my subitems' subitems and so forth.
     */
    public IItem[] getSubItemsRecursive();

    /**Adds item to my subitems and assigns me to
     * item as the highlevel item.
     */
    public IItem addSubItem(IPricingProduct product);

    // TODO throws ConversionInconsistentDBException, SXEInconsistentDBException, UnsuppliedAttributeException;
    public void addSubItem(IItem item);

    /**Removes item from my subitems and from my document. Furthermore,
     * removes all item's subitems recursively from item and from their
     * document. Invalidates inreversibly the items removed.
     */
    public void removeSubItem(String itemId);

    /** Sets my sales quantity, recalculates the base quantity, and sets
     * my sales to base quantity ratio.
     */
    public void setProductQuantity(IQuantityValue salesQuantity)
        throws ConversionMissingDataException;

    public void setBaseQuantity(IQuantityValue baseQuantity);

    public void setProductQuantity(BigDecimal salesQuantity, String salesQuantityUnit)
        throws ConversionMissingDataException;

    /** sets one of the condition access timestamps
     */
    public void setConditionAccessTimestamp(String conditionAccessTimestampName,
        SAPTimestamp conditionAccessTimestampValue);

    /**Sets my scale base timestamp.
     */
    public void setScaleBaseTimestamp(SAPTimestamp timestamp);

    public void setScaleBaseTimePeriod(TimePeriod scalePeriods);

    public void setConditionBaseTimePeriod(TimePeriod conditionBasePeriods);

    /**Sets me to a return item.
     */
    public void setReturn(boolean isReturn);

    /**Sets me to statistical.
     */
    public void setStatistical(boolean isStatistical);

    /**Sets my pricing item to unchangeable.
     */
    public void setUnchangeable(boolean pricingItemIsUnchangeable);

    /**Sets my relevance for pricing.
     */
    public void setRelevantForPricing(boolean isRelevantForPricing);

    // TODO throws ConversionInconsistentDBException, SXEInconsistentDBException, UnsuppliedAttributeException;

    /**@return if pricing is relevant for me.
     */
    public boolean isRelevantForPricing();

    /**Sets my gross weight.
     */
    public void setGrossWeight(IPhysicalValue grossWeight);

    public void setGrossWeight(BigDecimal value, String unitName);

    /**Sets my net weight.
     */
    public void setNetWeight(IPhysicalValue netWeight);

    public void setNetWeight(BigDecimal value, String unitName);

    /**Sets my volume.
     */
    public void setVolume(IPhysicalValue volume);

    /**Sets my points.
     */
    public void setPoints(IPhysicalValue points);

    public void setMultiplicity(BigDecimal multiplicity);

    public void setVolume(BigDecimal value, String unitName);

    public void setPoints(BigDecimal value, String unitName);

    /**Set my exchange rate between document currency and local currency.
     */
    public void setExchangeRate(IExchangeRate exRate);

    public void setExchangeRate(String exchangeRateType, SAPDate exchangeRateDateString,
        BigDecimal externalExchangeRateString)
        throws ConversionMissingDataException, CurrencyConversionException;

    /**
     * Sets a new "external condition rate" for a given condition type. Existing rates will be removed. 
     */
    public void setExternalDataSource(String pricingConditionTypeName, IExternalDataSource externalDataSource);
    
    /**
     * Adds an additional "external condition rate" for a given condition type. Already added rates won't be removed.
     * Use the method {@link #removeExternalDataSource(String)} to remove old rates 
     * or use the method {@link #setExternalDataSource(String, IExternalDataSource)} to set a single value.
     */
    public void addExternalDataSource(String pricingConditionTypeName, IExternalDataSource externalDataSource);

    public void setExternalDataSource(String pricingConditionTypeName, BigDecimal rateValue, String rateUnit,
        BigDecimal pricingUnitValue, String pricingUnitUnit, char calculationType, BigDecimal conditionBaseValue);

    public void removeExternalDataSource(String pricingConditionTypeName);

    public void importPricingConditions(char pricingTypeName, String copyTypeName, BigDecimal srcNetValue,
        BigDecimal tarNetValue, IExternalPricingConditions conditions, IQuantityValue sourceSalesQuantity);

    public void importPricingConditions(char pricingTypeName, String copyTypeNameString, BigDecimal srcNetValue,
        BigDecimal tarNetValue, IExternalPricingConditions conditions, BigDecimal sourceSalesQuantityValueString,
        String sourceSalesQuantityUnitString);

    public void importPricingConditions(char pricingTypeName, String copyTypeNameString, BigDecimal sourceNetValue,
        BigDecimal targetNetValue, String sourceDocumentId, String sourceItemId,
        BigDecimal sourceSalesQuantityValueString, String sourceSalesQuantityUnitString);

    public void setFixationGroupName(String fixationGroupName);

    public void setExternalId(String externalId);

    public void setId(String id);

    public void setProduct(IPricingProduct product);
    
   /*Adjust item base quantity value accroding to the change in sales unit to 
     base unit conversion factor
    */   
    public void adjustBaseQtyBasedOnExtnlFraction();
    
    /**
     * get item pricing conditions in external format
     * currently designed only for configuartion session usage
     * @return externalized item pricing conditions; returns NULL if conditions are not available
     */
    public IExternalPricingCondition[] exportExternalizedPricingConditions();

    /**
     * to import pricing conditions which is in external format.
     * currently designed only for configuartion session usage
     * @param extPrcCondns array of pricing conditions in external format 
     */
    public void importExternalizedPricingConditions(IExternalPricingCondition[] extPrcCondns);
    
    /**
     * set loaded from Persistency/DB status for this item 
     * currently designed for delayed/lazy item load scenario
     * @param loadedFrmPersistency item loaded from Persistency status indicator 
     */    
    public void setLoadedFromPersistency(boolean loadedFrmPersistency);
    
    public boolean getReturnInitialPricingTrace();
    
    public void setReturnInitialPricingTrace(boolean returnInitialPricingTrace);
    
    public void setInitialPricingTrace(String pricingTrace);
    
    public String getInitialPricingTrace();    
    
    public int getVariantKeysSize();  
}
