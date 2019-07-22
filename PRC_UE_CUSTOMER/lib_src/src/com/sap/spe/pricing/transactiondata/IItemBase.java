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

import com.sap.spe.base.sys.TimePeriod;
import com.sap.spe.condmgnt.finding.IAttributeBinding;
import com.sap.spe.condmgnt.finding.IConditionAccessTimestamp;
import com.sap.spe.conversion.IExchangeRate;
import com.sap.spe.conversion.IPhysicalValue;
import com.sap.spe.conversion.IQuantityValue;
import com.sap.spe.pricing.customizing.IPricingConditionType;
import com.sap.sxe.sys.SAPDate;
import com.sap.sxe.sys.SAPTimestamp;
import com.sap.sxe.util.math.Fraction;


/**
 * This interface should be implemented by the (sales order) item and is
 * used for callbacks of the pricing engine. It provides information of the
 * (sales order) item which is needed for pricing.
 */
public interface IItemBase {

    /** @return the ID of the external (sales order) line item. (R/3-field: KOMP-KPOSN) */
    public String getId();

    /** @return the vector of IItemBase-objects for my direct subItems. */
    public IItemBase[] getSubItemBases();

    /** @return my main item.         */
    public IItemBase getHighLevelItemBase();

    /** @return the external ID of the external (sales order) line item for displaying it on the
     *  GUI.  */
    public String getExternalId();

    /** @return the IPricingMaterial (which should be implemented by the material of the
     * (sales order) line item) of the external item.  (R/3-field: KOMP-MATNR) */
    public IPricingProduct getProduct();

    /** @return a vector of IAttributeBinding-Objects
     * (that are pairs of Attributeclass and AttributeValue) for the pricingItem.
     * The attribute bindings are used to search for condition records.  */
    public IAttributeBinding[] getAttributeEnvironment();
    
    /**
     * get attribute binding of given attribute name
     * @param attributeName
     * @return attribute binding
     */
    public IAttributeBinding getAttributeBinding(String attributeName);
    
 
    /** @return a vector of IVarcond-Objects for the pricingItem.
     * The varconds are used to find variant condition records.*/
    public IVariantCondition[] getVariantConditions();

    /** @return the exchange rate which has been derived by the external (sales order) line item for
     * conversions between local and document currencies. */
    public IExchangeRate getExchangeRate();

    /** @return the type for the determination of the exchange rate between document and local currency.
     */
    public String getExchangeRateType();

    /** @return my date for the determination of the rate between document and local curreny.
     */
    public SAPDate getExchangeRateDate();

    /**@return my condition access timestamps
     */
    public IConditionAccessTimestamp[] getConditionAccessTimestampEnvironment();

    /**@return my scaleBase timestamp.
     */
    public SAPTimestamp getScaleBaseTimestamp();

    /**
     * @return the scale base time period (R/3-fields: KOMP-STF_TAGE, KOMP-STF_WOCHEN, KOMP-STF_MONATE, KOMP-STF_JAHRE).
     */
    public TimePeriod getScaleBaseTimePeriod();

    /**
     * @return the condition base time period (R/3-fields: KOMP-ANZ_TAGE, KOMP-ANZ_WOCHEN, KOMP-ANZ_MONATE, KOMP-ANZ_JAHRE).
     */
    public TimePeriod getConditionBaseTimePeriod();

    /** @return the sales quantity of the (sales order) line item. (R/3-fields: KOMP-MGAME and KOMP-VRKME)*/
    public IQuantityValue getProductQuantity();

    /** @return the sales quantity of the (sales order) line item in the base unit of measure of
     * the line item's material. (R/3-fields: KOMP-MGLME and KOMP-LAGME) */
    public IQuantityValue getBaseQuantity();

    /** @return the fraction which is used for quantity conversion between the sales quantity and
     * the base quantity. (R/3-fields: KOMP-UMVKZ and KOMP-UMVKN)*/
    public Fraction getProductToBaseQuantityRatio();

    /** @return the gross weight of the (sales order) line item. (R/3-fields: KOMP-BRGEW and KOMP-GEWEI)*/
    public IPhysicalValue getGrossWeight();

    /** @return the netweight of the (sales order) line item. (R/3-fields: KOMP-NETGW and KOMP-GEWEI)*/
    public IPhysicalValue getNetWeight();

    /** @return the points of the (sales order) line item. (R/3-fields: KOMP-ANZPU and KOMP-PUNEI)*/
    public IPhysicalValue getPoints();

    /** @return the volume of the (sales order) line item. (R/3-fields: KOMP-VOLUM and KOMP-VOLEH)*/
    public IPhysicalValue getVolume();

    /** @return a flag which specifies if the (sales order) line item is a return. (R/3-field: KOMP-SHKZG)*/
    public boolean isReturn();

    /** @return a flag which specifies if the (sales order) line item is statistical for pricing. (R/3-field: KOMP-KOWRR)*/
    public boolean isStatistical();

    /** @return a flag which specifies if pricing analysis should be performed on the pricing item. */
    public boolean isTracing();

    /** @return a flag which specifies if the pricing conditions should not be changed anymore. In R/3 this
     *  flag is set if a sales order item is already (partially) billed. */
    public boolean isUnchangeable();

    /** @return a condition rate for example for the moving average price. This callback method could
     *  also be used for other pricing conditions. */
    public IExternalDataSource[] getExternalDataSource(IPricingConditionType pricingConditionType);

    /** @return a string which indicates which conditions should NOT be searched for (Fixation Group)
     *  Is given to java through the interface with CRM */
    public String getFixationGroupName();

    /** Returns the multiplicity of an item. */
    public BigDecimal getMultiplicity();
        
    /**
     * to check whether this item is loaded from Persistency/DB or not 
     * currently designed for delayed/lazy item load scenario
     * @return true if item is loaded from Persistency otherwise false
     */    
    public boolean isLoadedFromPersistency();    
}
