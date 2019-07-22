/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata;

import java.util.Map;

import com.sap.spe.base.util.event.StatusEvent;
import com.sap.spe.conversion.ICurrencyUnit;
import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.pricing.customizing.IPricingProcedure;


/**
 * The pricing document contains accumulated pricing information of all pricing items
 * and provides methods which are processing all pricing items.
 * This interface contains all methods which are accessable in SPE-userexits and
 * in the IPricingDocument-interface.
 */
public interface IPricingDocumentCommon {

    /**
     * @return my Id. The Id corresponds to the R/3-databasefield KONV-KNUMV
     */
    public String getId();

    /**
     * @return the netvalue of the whole document, which is a
     * sum of the netvalue of the items.
     */
    public ICurrencyValue getNetValue();

    /**
     * @return the taxvalue of the whole document.
     */
    public ICurrencyValue getTaxValue();

    /**@return the freight value of the whole document.
     */
    public ICurrencyValue getFreight();

    /**@return the net value without freight of the whole document.
     */
    public ICurrencyValue getNetValueWithoutFreight();

    /**
     * @param subtotalFlag the subtotal flag (@see com.sap.spe.pricing.customizing.application.PricingCustomizingConstants#ConditionSubtotal)
     * @return the subtotal value of the whole document.
     */
    public ICurrencyValue getSubtotal(char subtotalFlag);

    public Map getSubtotals();

    /**
     * @return my document currency unit.
     */
    public ICurrencyUnit getDocumentCurrencyUnit();

    /**
     * @return my local currency unit.
     */
    public ICurrencyUnit getLocalCurrencyUnit();

    /**
     * @return my pricing procedure.
     */
    public IPricingProcedure getPricingProcedure();

    /**
     * @return the flag which specifies if pricing condition with zero values should
     * be taken into account for the exclusion group logic.
     */
    public boolean getZeroValuesForExclusionFlag();

    /**
     * Set the flag which specifies if pricing condition with zero values should
     * be taken into account for the exclusion group logic.
     */
    public void setZeroValuesForExclusionFlag(boolean flag);

    /**
     * @return the flag which specifies if the currency conversion should be done
     * directly between condition record currency and document currency or from
     * condition record currency to local currency to document currency.
     */
    public boolean getTryDirectCurrencyConversionFlag();

    /**
     * Set the flag which specifies how the currency conversion should be performed.
     */
    public void setTryDirectCurrencyConversionFlag(boolean flag);

    /**
     * @return the flag which specifies if the currency conversion flag in the
     * customizing of the condition type should be taken into account.
     */
    public boolean getTakeCurrencyConversionFlagIntoAccount();

    /**
     * Set the flag which specifies if the currency conversion flag in the
     * customizing of the condition type should be taken into account.
     */
    public void setTakeCurrencyConversionFlagIntoAccount(boolean flag);

    public int getUnitToBeRoundedTo();

    public boolean isOnlySpecifiedUsage();

    public boolean isAlwaysPerformingGroupConditionProcessing();

    public void setAlwaysPerformingGroupConditionProcessing(boolean alwaysPerformGroupConditionProcessing);

    /**
     * This method can be used to store objects in the userexits. To remove a key, set the parameter obj to null.
     */
    public Object setObjectForUserExits(String key, Object obj);

    /**
     * This method can be used to retrieve objects.
     */
    public Object getObjectForUserExits(String key);

    public void addObserver(IPricingDocumentObserver observer);

    public void removeObserver(IPricingDocumentObserver observer);

    /** @return the partial processing flag of the (sales) document. The partial processing flag contains
     *  information about if all documents lineitems are processed or not.*/
    public boolean isPartialProcessing();

    /** @return the authority level the user has to display this document's conditions. */
    public int getAuthorityForDisplay();

    /** @return the authority level the user has to edit this document's conditions. */
    public int getAuthorityForEdit();

    /** @return the flag if prices with the rate 0 are set to inactive. */
    public boolean isZeroPriceActive();

    public void setZeroPriceActive(boolean isActive);

    /**
     * Signals an instance of StatusEvent to my status listeners by invoking
     * their setStatusMessage() method.
     * @param event the StatusEvent to be signaled
     */
    public void setStatusMessage(StatusEvent event);

    /** Signals an instance of StatusEvent to my status listeners by invoking
     *   their clearStatusMessage() method.
     *   @param event the StatusEvent to be signaled
     */
    public void clearStatusMessage(StatusEvent event);
    
	/**
	 * to check whether return document indicator is set at the document(header) level or not
	 * @return true if return falg is set at header level otherwise returns false
	 */
	public boolean isReturnDocument();
	
    /**
	 * to check whether this document is a 'lean document' or not. Lean document is used for 
	 * interactive configuration in decoupled pricing and configuration scenario.
	 * @return TRUE if it is a lean document otherwise FALSE
	 */
	public boolean isLeanDocument();
}
