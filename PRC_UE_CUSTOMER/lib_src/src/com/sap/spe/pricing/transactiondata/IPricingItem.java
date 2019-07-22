/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Vector;

import com.sap.spe.base.util.event.StatusListener;
import com.sap.spe.condmgnt.finding.IConditionFindingManager;
import com.sap.spe.conversion.IQuantityValue;
import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.spe.pricing.transactiondata.exc.ManualChangeNotAllowedException;


/**
 * The pricing item contains accumulated pricing information of all pricing conditions
 * and provides methods which are working on all pricing condition of this pricing item.
 */
public interface IPricingItem extends IConditionFindingManager, IPricingItemCommon {

    /**
     * Perform a search of condition records and then a calculation for the pricing conditions
     * which have been found. Input parameters are the IItem which should be implemented by the (sales order)
     * item, and the flag 'redetermineAllConditions'. If this is 'true' the function 'New Pricing' is performed:
     * all existing conditions are thrown away and a new search and calculation is performed.
     * Usually the parameter 'redetermineAllConditions' should be set to false.
     */
    public void pricing(boolean redetermineAllConditions);

    // TODO: throws ConversionInconsistentDBException, UnsuppliedAttributeException, SXEInconsistentDBException;

    /**
     * Performs a pricing on this <code>IPricingItem</code>.
     *
     * @param pricingType specifies how this <code>IPricingItem</code> will be
     *        priced (which conditions will be redetermined, which conditions
     *        will be kept, ...)
     * @see com.sap.spe.pricing.customizing.IPricingType
     */
    public void pricing(char pricingType);

    /**
     * This method allows to add a pricing condition to the pricing item, if the corresponding condition type exists
     * in the pricing procedure and the customizing of the condition type allows manual entries.
     */
    public IPricingCondition[] addPricingCondition(String condTypeName)
        throws ManualChangeNotAllowedException;  // TODO: SXEIllegalArgumentException, ManualChangeNotAllowedException, ConversionInconsistentDBException, UnsuppliedAttributeException, SXEInconsistentDBException;

    /**
     * This method combines the three methods {@link IPricingItem#addPricingCondition(String)} and
     * {@link IPricingCondition#changeConditionRate(BigDecimal, String, BigDecimal, String)} and
     * {@link IPricingItem#changeCalculationType(char)}.
     * This method was created for SAP Note 1294785.
     * 
     * @param condTypeName The name of the condition type.
     * @param condRate The Condition Rate.
     * @param unitName The Condition Currency.
     * @param prUnitValue The Condition Pricing Unit.
     * @param prUnitName The Condition Unit.
     * @param calculationType The calculation type.
     * @return A list of IPricingCondition.
     */
    public IPricingCondition[] addPricingCondition(String condTypeName, BigDecimal condRate, 
    		String unitName, BigDecimal prUnitValue, String prUnitName, char calculationType)
        throws ManualChangeNotAllowedException, ConversionMissingDataException;  
    
    /**
     * This method allows to delete a pricing condition from the pricing item, if the customizing of the
     * corresponding condition type allows manual entries.
     */
    public void deletePricingCondition(int stepNo, int counter)
        throws ManualChangeNotAllowedException;

    /**
     * Returns true if this condition may be deleted from this item; otherwise false.
     */
    public boolean canDeletePricingCondition(IPricingCondition cond);

    /**
     *  This method allows to change the itemnumber of a pricing item.
     */
    public void setId(String itemId);

    /**
     * @return the item conditions, filtered for printing
     */
    public IPricingCondition[] getConditionsForPrinting();

    public IPricingCondition[] getPricingConditions();

    public IPricingCondition[] getPricingConditions(boolean checkAuthority);

    /** Adds a StatusListener.
     *   @param l the listener to be added
     */
    public void addStatusListener(StatusListener l);

    /** Removes a StatusListener.
     *   @param l the listener to be removed
     */
    public void removeStatusListener(StatusListener l);

    /**
     *  @return my highlevel item or null
     */
    public IPricingItem getHighLevelItem();

    /** Return the IPricingDocument of the pricingItem.*/
    public IPricingDocument getDocument();

    public void loadFromExternal(IQuantityValue sourceSalesQuantity, char pricingTypeName, String copyTypeName,
        BigDecimal srcNetValue, BigDecimal tarNetValue, IExternalPricingConditions conditions);

    // TODO: throws ConversionInconsistentDBException, SXEInconsistentDBException, UnsuppliedAttributeException;
    public void loadFromLocalDBWithReference(IQuantityValue sourceSalesQuantity, String sourceDocumentId,
        String sourceItemId, char pricingTypeName, String copyTypeName, BigDecimal srcNetValue, BigDecimal tarNetValue);

    // TODO: throws ConversionInconsistentDBException, SXEInconsistentDBException, UnsuppliedAttributeException;
    public ILastPrice getLastPrice();
    
    /**
     * for checking whether the item is loaded from persistency or not
     * currently designed for delayed item load scenario
     * @return TRUE if item is loaded from DB lazily
     */
    public boolean isLoadedFromDBLazily();
    
    /**
     * get item pricing conditions in external format; This will not include cross item condition (group/pure header/structure condns)
     * currently designed only for configuartion session usage
     * @return externalized item pricing conditions; returns NULL if conditions are not available
     */
    public IExternalPricingCondition[] getExternalizedConditions();   
    
    /**
     * to import pricing conditions which is in external format. Currently this is defined for importing conditions from config session
     * currently designed only for configuartion session usage
     * @param extPrcCondns array of pricing conditions in external format 
     */
    public void loadFromExternal(IExternalPricingCondition[] extPrcCondns);
    
    /** Is used to switch on or off the price calculation trace .*/
    public void setPerformCalcTrace(boolean performCalcTrace);
    
    /** Returns boolean flag if price calculation trace is activated. */
    public boolean isCalcTracing();
    
    /** Returns the formula trace. */
    public Collection getFormulaTrace();
    
    /** Returns the exclusion trace. */
    public Collection getExclusionTrace();
    
    /** Returns the scale trace. */
    public Collection getScaleTrace();
    
    /** Returns the currency conversion trace. */
    public Map getCurrencyConversionTrace();
    
    /** Returns the quantity conversion trace. */
    public Vector getQuantityConversionTrace();
}
