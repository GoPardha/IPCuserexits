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

import com.sap.spe.base.util.event.StatusListener;
import com.sap.spe.condmgnt.finding.IRelevantAttributes;
import com.sap.spe.conversion.ICurrencyUnit;
import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.spe.pricing.transactiondata.exc.ManualChangeNotAllowedException;


/**
 * The pricing document contains accumulated pricing information of all pricing items
 * and provides methods which are processing all pricing items.
 */
public interface IPricingDocument extends IPricingDocumentCommon {

    /**
     * Creates a new PricingItem with the item number of the input parameter item and
     * adds it to my set of PricingItems.
     *
     * Typical use:  When a new sales document line item is created.
     *
     * Note:
     * The position is necessary to store the PricingConditions for one
     * PricingItem.
     * @param item - The pricing relevant part of a (sales order) line item. (The corresponding
     * interface should be implemented by the line item of the document.)
     * @Exception ConversionInconsistentDBException is thrown if a unit in the parameter item is not found in
     * the database.
     */
    public IPricingItem createItem(IItemBase item);

    // TODO: throws ConversionInconsistentDBException, SXEIllegalArgumentException;

    /**
     * Call the method pricing for each of my pricing items.
     */
    public void pricing();

    // TODO: throws ConversionInconsistentDBException, UnsuppliedAttributeException, SXEInconsistentDBException;

    /**
     * Call the method pricing for each of the pricing items in the array of pricingItems.
     */
    public void pricing(IPricingItem[] pricingItems);

    // TODO: throws ConversionInconsistentDBException, UnsuppliedAttributeException, SXEInconsistentDBException;

    /**
     * Starts pricing logic for the whole pricing document (e.g. the group condition logic:
     * here the accumulation of scale base values for groups of items is done).
     */
    public void pricingComplete();

    /**
     * Adds a PricingItem to my set of PricingItems and performs pricing.
     *
     * Typical use:  When a sales document item is created.
     */
    public void addItem(IPricingItem item);

    // TODO: throws ConversionInconsistentDBException, UnsuppliedAttributeException, SXEInconsistentDBException;

    /**
     * Adds PricingItems to my set of PricingItems and performs pricing.
     *
     * Typical use:  When a new sales document items are created.
     *
     * Note:         * The position is necessary to store the PricingConditions for one
     * PricingItem. Futhermore the method pricingComplete is processed.
     */
    public void addItems(IPricingItem[] items);

    // TODO: throws ConversionInconsistentDBException, UnsuppliedAttributeException, SXEInconsistentDBException;

    /**
     * Adds a PricingItem to my set of PricingItems.
     *
     * Typical use:  When a sales document item is loaded from the local db or from external.
     */
    public void addItemWithoutPricing(IPricingItem item);

    /**
     * Removes a PricingItem with an itemnumber from my set of PricingItems.
     * @return true if the element was found, else false.
     *
     * Typical use:  When a sales document item is deleted.
     */
    public boolean removeItem(String itemNumber);

    /**
     * Removes PricingItems from my set of PricingItems.  (Only
     * removes the first occurrence.)
     * @return true if all elements were found, else false.
     *
     * Typical use:  When sales order items are deleted.
     *
     * Note:         * Futhermore the method pricingComplete is processed.
     */
    public boolean removeItems(String[] itemNumbers);

    /**
     * @return an item in my list whose item number matches
     * itemNumber. Returns null if nothing matched.
     */
    public IPricingItem findItem(String itemNumber);

    /**
     * @return an item in my list whose external item number matches
     * externalItemNumber. Returns null if nothing matched.
     */
    public IPricingItem findItemExternalNumber(String externalItemNumber);

    /**
     * Returns a list of all attributes  which are relevant for the search
     * of condition records (e.g. VKORG, VTWEG, MATNR....). These are determined
     * from all AccessSequences used in my PricingProcedure. The sales document
     * should be responsible for only adding the relevant fields to the
     * PricingItem.
     *
     * Typical use: This method should be called when the sales document
     * is created and the Procedure for the document is known. The contents
     * of the attributes should be filled by the sales order item and this
     * completed OrderedSet should then be passed by the method AddAttributes.
     */
    public IRelevantAttributes determineRelevantAttributes();

    /**
     * @return my document currency unit.
     */
    public ICurrencyUnit getDocumentCurrencyUnit();

    /**
     * Set my document currency unit.
     */
    public void setDocumentCurrencyUnit(ICurrencyUnit documentCurrencyUnit);

    // TODO: throws ConversionInconsistentDBException, UnsuppliedAttributeException, SXEInconsistentDBException;

    /**
     * Sets the unit to be rounded, e.g. 5 for the so-called 5-Rappen-rounding in swisse.
     *  Should be 0 if no rounding should be performed.
     */
    public void setUnitToBeRoundedTo(int unitToBeRoundedTo);

    /**
     * Set my local currency unit.
     */
    public void setLocalCurrencyUnit(ICurrencyUnit localCurrencyUnit);

    // TODO: throws ConversionInconsistentDBException, UnsuppliedAttributeException, SXEInconsistentDBException;

    /**
     * Should be used to switch the trace for the search of condition records on or off.
     */
    public void setPerformTrace(boolean performTrace);
    
    /**
     * Should be used to switch the trace for the calculation on or off.
     */
    public void setPerformCalcTrace(boolean performCalcTrace);

    /**Sets the authority level the user has to display and edit this document's conditions
     */
    public void setAuthority(int authorityForDisplay, int authorityForEdit);

    /** Adds a StatusListener.
     *   @param l the listener to be added
     */
    public void addStatusListener(StatusListener l);

    /** Removes a StatusListener.
     *   @param l the listener to be removed
     */
    public void removeStatusListener(StatusListener l);

    /**
     * @return the item conditions summed up
     */
    public IPricingCondition[] getConditions();

    /**
     * @return the item conditions, filtered and condensed for printing
     */
    public IPricingCondition[] getConditionsForPrinting();

    /**
     * @return the header conditions which have been added manually
     */
    public IHeaderCondition[] getHeaderConditions();

    /**
     * @return the document items
     */
    public IPricingItem[] getItems();

    /**
     * Adds a header condition that is type of condTypeName.
     */
    public IHeaderCondition addHeaderCondition(String condTypeName)
        throws ManualChangeNotAllowedException;  // TODO: SXEInconsistentDBException, ConversionInconsistentDBException, ManualChangeNotAllowedException, UnsuppliedAttributeException;

    /**
     * This method was created for SAP Note 1294785.
     * 
     * @param condTypeName The name of the condition type.
     * @param condRate The Condition Rate.
     * @param unitName The Condition Currency.
     * @param prUnitValue The Condition Pricing Unit.
     * @param prUnitName The Condition Unit.
     * @param calcType The calculation type.
     * @return A list of IHeaderCondition.
     */
    public IHeaderCondition addHeaderCondition(String condTypeName, BigDecimal condRate, 
    		String unitName, BigDecimal prUnitValue, String prUnitName, char calcType)
        throws ManualChangeNotAllowedException, ConversionMissingDataException;  
    
    /**
     * Deletes all my (truely header and summed up) conditions which have a corresponding counter.
     */
    public void deletePricingCondition(int counter)
        throws ManualChangeNotAllowedException;

    public void addSourcePricingItem(String sourceDocumentId, String sourceItemId);

    public void loadFromLocalDB();

    // TODO: throws ConversionInconsistentDBException, SXEInconsistentDBException, SXEException;
    public void loadFromExternal(IExternalPricingConditions externalPricingConditions);

    // TODO: throws ConversionInconsistentDBException, SXEInconsistentDBException, SXEException;
    public void setOnlySpecifiedUsage(boolean getOnlySpecifiedUsage);

    public void adaptConditionsDueToUnchangeableItems();

    // TODO: remove stepNo parameter
    public IHeaderCondition findHeaderCondition(int stepNo, int headerCounter);

    /**
     *
     * For performance reasons, the automatic calculation might be deactivated.
     * After having done a series of method calls, the calculation must be
     * triggered explicitely.
     * @param b
     */
    public void setAlwaysPerformingCalculation(boolean b);

    /**
     * An explicit calculation might be triggered e.g. after switching on
     * the alwaysPerformingCalculation flag.
     */
    public void calculate();

    /**
     * sets the process mode for manual changes
     * @param processMode
     */
    public void setProcessMode(char processMode);

    public interface ProcessMode {
        public final char MANUAL_MODE = 'N';  // manual input, perform checks and set manual flag
        public final char LEGACY_MODE = 'Y';  // suppress checks and set manual flag
        public final char EXTERNAL_DETERMINATION_MODE = 'A';  // suppress checks and do not set manual flag
    }
    
    /**
     * get header level pricing status. It returns true if pricing at header level is already done otherwise return false.
     * @return header level pricing completion status.
     */
    public boolean getHdrLevelPricingCompletionStatus();
    
    /**
     * delayed loading of conditions while opening a pricing document
     * @param pricing conditions in external format
     */
    public void lazyCondnLoadFromExternal(IExternalPricingConditions externalPricingConditions);
    
}
