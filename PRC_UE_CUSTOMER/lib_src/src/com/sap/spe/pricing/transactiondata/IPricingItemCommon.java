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

import java.util.Map;

import com.sap.spe.base.util.event.StatusEvent;
import com.sap.spe.condmgnt.finding.IConditionFindingManagerCommon;
import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.conversion.IDimensionalValue;
import com.sap.spe.conversion.IExchangeRate;
import com.sap.spe.conversion.IPhysicalValue;
import com.sap.spe.conversion.IQuantityValue;
import com.sap.sxe.sys.SAPDate;


/**
 * The pricing item contains accumulated pricing information of all pricing conditions
 * and provides methods which are working on all pricing condition of this pricing item.
 */
public interface IPricingItemCommon extends IConditionFindingManagerCommon {

    /**
     * @return the total netvalue of the pricingItem in the document currency. (R/3-field: KOMP-NETWR)
     */
    public ICurrencyValue getNetValue();
    
    /**
     * @return the net value (R/3-field: KOMP-NETWR) in the document currency as BigDecimal. 
     * Use this method to avoid the superfluous creation of a currency value object.
     */
    public BigDecimal getNetValueAsBigDecimal();
    
    /**
     * @return the total netprice of the pricingItem. (R/3-field: KOMP-NETPR)
     */
    public ICurrencyValue getNetPrice();

    /**
     * @return the pricing unit of the net price.
     */
    public IDimensionalValue getNetPricePricingUnit();

    /**
     * @return the total taxvalue of the pricingItem in the document currency. (R/3-field: KOMP-MWSBP)
     */
    public ICurrencyValue getTaxValue();
    
    /**
     * @return the total taxvalue of the pricingItem in the document currency. (R/3-field: KOMP-MWSBP)
     * Use this method to avoid the superfluous creation of a currency value object.
     */
    public BigDecimal getTaxValueAsBigDecimal();

    /**
     * @return the net weight of the pricingItem.
     */
    public IPhysicalValue getNetWeight();

    /**
     * @return the gross weight of the pricingItem.
     */
    public IPhysicalValue getGrossWeight();

    /**
     *  @return the volume of the pricingItem.
     */
    public IPhysicalValue getVolume();

    /**
     * @return the item number of the pricingItem.
     */
    public String getId();

    /**
     * Return the IPricingProduct of the pricingItem.
     */
    public IPricingProduct getProduct();

    /** @return the sales quantity which has been passed to the pricingItem. */
    public IQuantityValue getProductQuantity();

    /** @return the sales quantity in the base unit of measure of the material. */
    public IQuantityValue getBaseQuantity();

    /** @return true if the pricing item is a return item. This flag has been passed to
     * the pricingItem by the (sales order) item.*/
    public boolean isReturn();

    /** @return true if the pricing item is a statistical item. This flag has been passed to
     * the pricingItem by the (sales order) item.*/
    public boolean isStatistical();

    /** @return the value of the exchange rate.*/
    public IExchangeRate getExchangeRate();

    /** @return the type for the determination of the exchange rate between document and local currency.
     */
    public String getExchangeRateType();

    /** @return my date for the determination of the rate between document and local curreny.
     */
    public SAPDate getExchangeRateDate();
    
    /**
     * @param subtotalFlag the subtotal flag (@see com.sap.spe.pricing.customizing.application.PricingCustomizingConstants#ConditionSubtotal
     * @return the subtotal value. How the value is calculated is determined by the customizing of the pricing procedure.*/
    public ICurrencyValue getSubtotal(char subtotalFlag);
    
    /**
     * @param subtotalFlag the subtotal flag (@see com.sap.spe.pricing.customizing.application.PricingCustomizingConstants#ConditionSubtotal
     * @return the subtotal value. How the value is calculated is determined by the customizing of the pricing procedure.
     * use this method to avoid the superfluous creation of a currency value object.
     * */
    public BigDecimal getSubtotalAsBigDecimal(char subtotalFlag);
    
    /**
     * @return the subtotal value. How the value is calculated is determined by the customizing of the pricing procedure.
     * Use this method to avoid the superfluous creation of a currency value object.
     * */
    public BigDecimal getSubtotalAsBigDecimal();

    public Map getSubtotals();

    /** @return the values of the fields with the purposeFieldNames coming from the
     *        customizing of the conditionTypes. The key-value-pairs of the map are String-ICurrencyValue-pairs for
     * the purposeFieldNames (String) and their values (ICurrencyValue).
     */    
    public Map getAccumulatedValuesForConditionsWithPurpose();

    /**
     * @return hashtable, which contains all key-value pairs of the dynamic return container. This method
     * returns only a copy of the dynamic return container and should not be used to change the contents
     * of this container.
     */
    public Map getDynamicReturnValues();

    /**
     * This method can be used to set a key-value pair in the dynamic return container.
     * @param key this parameter specifies the unique access key of the key-value-pair.
     * If this parameter is null, no key-value pair will be inserted in the dynamic return container.
     * @param value this parameter stands for the value of the key-value-pair
     * @return if the dynamic return container contains already a key-value pair with the
     * same key, the old value of this key is returned, otherwise null is returned
     */
    public String setDynamicReturnValue(String key, String value);

    /**
     * @return the amount eligible for cash discount in document currency
     * Corresponds to the R/3 field SKFBP.
     */
    public ICurrencyValue getCashDiscountBasis();

    /**
     * @return the accumulated cash discount.
     */
    public ICurrencyValue getCashDiscount();
    
    /**
     * @return the accumulated cash discount.
     * Use this method to avoid the superfluous creation of a currency value object.
     */
    public BigDecimal getCashDiscountAsBigDecimal();

    /**@return the accumulated freight value
     * 
     */
    public ICurrencyValue getFreight();
    
    /**@return the accumulated freight value
     * Use this method to avoid the superfluous creation of a currency value object.
     */
    public BigDecimal getFreightAsBigDecimal();

    /**@return the accumulated netValue without freight
     */
    public ICurrencyValue getNetValueWithoutFreight();
    
    /**@return the accumulated netValue without freight
     * Use this method to avoid the superfluous creation of a currency value object.
     */
    public BigDecimal getNetValueWithoutFreightAsBigDecimal();
    

    /**
     * @return the exclusion flag. This flag could be used to realise
     * special exclusion logic with the use of condition base or condition
     * value formulas.
     */
    public char getExclusionFlag();

    /**
     * This method can be used to transfer some values from one formula to another.
     * To remove a key, set the parameter obj to null.
     */
    public Object setObjectForUserExits(String key, Object obj);

    /**
     * This method can be used to transfer some values from one formula to another.
     */
    public Object getObjectForUserExits(String key);

    /** @return a flag which specifies if the pricing conditions should not be changed anymore. In R/3 this
     *  flag is set if a sales order item is already (partially) billed. */
    public boolean isUnchangeable();

    /** Returns the multiplicity of an item. */
    public BigDecimal getMultiplicity();

    /**
     * @return the external item number of the pricingItem.
     */
    public String getExternalId();

    /**
     * This method returns a pricing condition from the pricing item with the
     * given step number and condition counter as search criteria
     */
    public IPricingCondition findPricingCondition(int stepNo, int counter);

    /**
     * Sets a flag that this item should be recalculated during 
     * pricing complete.
     */
    public void setCalculationDuringPricingCompleteRequired(boolean calcRequired);    

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
     * This class defines all constants, which are used in the dynamic return container.
     * This return container contains key/value pairs determined during the pricing functionality.
     */
    public interface DynamicReturnContainerConstants {

        /**
         * key name for payment term (determined by a condition on item level)
         */
        static final String PAYMENT_TERM = "PTERM";

        /**
         * key name for the payment term flag, which indicates
         * if the payment term was new determined
         */
        static final String PAYMENT_TERM_NEW = "PTERM_NEW";

        /**
         * this value indicates, that the payment term was new determined
         */
        static final String PAYMENT_TERM_NEW_TRUE = "X";

        /**
         * this value indicates, that the payment term was not new determined
         */
        static final String PAYMENT_TERM_NEW_FALSE = "";
    }
}
