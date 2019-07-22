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

import com.sap.spe.condmgnt.finding.ICondition;
import com.sap.spe.conversion.ICurrencyUnit;
import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.conversion.IDimensionalValue;
import com.sap.spe.conversion.IExchangeRate;
import com.sap.spe.conversion.IPhysicalValue;
import com.sap.spe.pricing.customizing.IConditionPurpose;
import com.sap.sxe.util.math.Fraction;


/**
 * A pricing condition contains information copied from the pricing knowledge base
 * (from the customizing of the condition type and the pricing procedure), from the
 * condition record, and from the result of the calculation.
 * This interface contains all methods which are accessable in SPE-userexits and
 * in the IPricingCondition-interface.
 */
public interface IPricingConditionCommon extends ICondition {

    /** @return the application. (R/3-field KOMK-KAPPL)*/
    public String getApplication();

    public String getUsage();

    public String getConditionTypeName();

    /** @return true if condition values are accumulated. The functionality in only supported in the
       R/3 system.
     * (R/3-field: T685A-KOUPD) */
    public boolean isConditionUpdate();

    public IConditionPurpose getPurpose();

    public String getConditionRecordId();

    /** The calculation type determines how the conditionbase value and the
     *  condition value is calculated. (R/3-field KONV-KRECH)
     *  @return the calculation type */
    public char getCalculationType();

    /** @return true if the change of the calculation type is allowed.
     * (R/3-field: KONVD-KAEND_RCH). Always false because the functionality is not implemented yet.*/
    public boolean getChangeOfCalculationTypeAllowed();

    /** @return true if the change of the conversion factors between the unit of measure of the
     * condition record and the base unit of measure is allowed. (R/3-field: KONVD-KAEND_UFK).
     * Is always false because the functionality is not implemented yet.*/
    public boolean getChangeOfConversionFactorAllowed();

    /** @return true if the change of the conditionrate is allowed.
     * (R/3-field: KONVD-KAEND_BTR) */
    public boolean getChangeOfRateAllowed();

    /** @return true if the change of the unit of the condition rate is allowed.*/
    public boolean getChangeOfUnitAllowed();

    /** @return true if the change of the condition value is allowed
     * (R/3-field: KONVD-KAEND_WRT */
    public boolean getChangeOfValueAllowed();

    /** The conditionbase contains a currency, physical or quantity value depending
     *  on the calculation type. (R/3-field KONV-KAWRT)
     *  @return the conditionbase. */
    public IDimensionalValue getConditionBase();

    /** Grouping of conditions/conditiontypes: variants, cost, freight....
     *  (R/3-field KONV-KNTYP) In R/3 for special logic is used for conditions with
     *  a special condition category.
     *  @return the condition category. */
    public char getConditionCategory();

    /** Grouping of conditions/conditiontypes: price, discount, tax, rebate.
     * (R/3-field KONV-KOAID)
     * @return the condition class (a flag if a pricing condition
     * is a price, a discount or a tax). */
    public char getConditionClass();

    /** @return a flag which specifies eg. if a condition has been changed manually or if
     * the condition value is fixed. (R/3-field: KONV-KSTEU) */
    public char getConditionControl();  // copy of PricingCondition type

    /** The conditionrate contains the rate (a currency value or a percentage) which
     *  is copied from the condition record. (R/3-fields: KONV-KBETR and -WAERS)
     *  @return the condition rate.*/
    public ICurrencyValue getConditionRate();
    
    /**
     * @return the scale dimension.
     */
    public int getConditionScaleDimension();
    
    /** @return the scalebase value of the condition. The scalebase contains a currency, a physical
     * or a quantity value in dependence of the scalebaseindicator.
     * (R/3-fields: KONV-KSTBS, -KONMS and -KONWS) */
    public IDimensionalValue getConditionScale();

    public IDimensionalValue getConditionScale(int dimNo);

    /** @return the condition value in the document currency.
     * (R/3-field: KONV-KWERT) */
    public ICurrencyValue getConditionValue();

    /** @return a flag which specifies if the currency conversion is performed before the condition rate is
     * multipied with the condition base or afterwards. (R/3-field: KONVD-GANZZ) */
    public boolean isCurrencyConversion();

    /** @return true if the deletion of the pricing condition is allowed.
     * (R/3-field: KONVD-KAEND_LOE) */
    public boolean isDeletionAllowed();

    /** @return the description of the pricing condition. */
    public String getDescription();

    /** @return the document currency as a String */
    public String getDocumentCurrencyUnitName();

    /** @return an error message eg. if an error occured during currency/quantity conversion.*/
    public String getErrorMessage();

    /** @return the exchange rate for the conversion from condition
     * currency to local currency. (R/3-field: KONV-KKURS)*/
    public IExchangeRate getExchangeRate();

    public IExchangeRate getDirectExchangeRate();

    /** @return true if a condition is a group condition.
     * (R/3-field: KONV-KGRPE) */
    public boolean isGroupCondition();

    /** @return true if the condition is a header condition/can be added on the header level.
     * (R/3-field: KKOPF) */
    public boolean isHeaderCondition();

    /** @return the counter
     * (R/3-field: KONV-ZAEKO for pricing) */
    public int getHeaderCounter();

    /** @return a flag which specifies if a condition is inactive (eg. inactive due
     * to a subsequent price). (R/3-field: KONV-KINAK) */
    public char getInactive();

    /** @return true if the corresponding condition type is a special condition type for intercompany invoicing.
       (R/3-field: KONV-KFKIV) */
    public boolean isInterCompanyBilling();

    /** @return true if the corresponding condition type is a special condition type for invoice lists.
       (R/3-field: KONV-KRELI) */
    public boolean isInvoiceList();

    /** @return true if the condition can be added on the item level.
     * (R/3-field: KPOSI) */
    public boolean isItemCondition();

    /** @return the item id of the corresponding line item. (R/3-field: KONV-KPOSN). */
    public String getItemId();

    /** @return a flag which specifies if condition changed manually entries. (R/3-field: KONV-KMPRS) */
    public boolean isManuallyChanged();

    /** @return a flag which specifies if manual entries are allowed and/or if manual or
     * automatic conditions have priority. (R/3-field: KONVD-KMANU) */
    public char getManualEntryFlag();

    /**
     * @return the conversion factor for the conversion from the
     * condition quantity to the base unit of measure of the material
     * (for quantity dependent pricing conditions) or the conversion factor
     * from condition volume to the base SI unit of measure of volumes
     * (for volume dependent pricing conditions)...
     * (R/3-fields: KONV-KUMNE and -KUMZA)
     */
    public Fraction getFraction();

    /** @return the origin of the condition (eg. automatically determined or manually entered)
     * (R/3-field: KONV-KHERK) */
    public char getOrigin();

    /** @return a flag which specifies if the rate of the condition should be positive or negative.
     * (' ': positive and negative rates allowed; 'A': only positive rates allowed;
     *  'X': only negative rates allowed) (R/3-field: KONVD-KNEGA) */
    public char getPlusMinus();

    /** Note that this returns a physical or a quantity value, not just a unit!
     * (R/3-fields: KONV-KPEIN and -KMEIN)
     * @return the pricingunit.*/
    public IPhysicalValue getPricingUnit();

    /** @return a flag which determines if the condition value is rounded.
     * (R/3-field: KONVD-TXPRF)*/
    public char getRoundingRule();

    /** @return a flag which specifies which scale base type is used (eg. value scales or
     * quantity scales). (R/3-field KONV-KZBZG) */
    public String getScaleBaseIndicator();

    public String getScaleBaseIndicator(int dimNo);

    /** @return a flag which specifies the scale type (eg. a from-scale or a to-scale).
     * (R/3-field: KONVD-STFKZ)  */
    public char getScaleType();

    /** @return true if a condition is statistical.
     * (R/3-field: KONV-KSTAT) */
    public boolean isStatistical();

    /** @return true if a condition is a variant condition.
     * (R/3-field: KONV-KVARC)      */
    public boolean isVariantCondition();

    /** @return an indicator which specifies if the pricingCondition is a condition to be copied
     * from a main item to subItems or if it is used for cumulation of values. (R/3-field: KONV-KDUPL)
     */
    public char getStructureConditionFlag();

    public BigDecimal getFactor();

    public BigDecimal getIncScaleBegin();

    public BigDecimal getIncScaleEnd();

    public BigDecimal getRoundingDifference();

    public boolean isSubtotalLine();

    /** @return my variable attribute. (R/3-field: KONV-VARCOND for pricing)*/
    public String getVariantConditionKey();

    public BigDecimal getVariantConditionFactor();

    /** @return the description of the variant condition key */
    public String getVariantConditionDescription();

    public String getTaxCode();

    public String getWithholdingTaxCode();

    public String getConditionTableName();

    /** @return the payment terms of the condition */
    public String getPaymentTerm();

    /** @return the data source. */
    public String getDataSource();

    /** @return the account key (1) (R/3-field KVSL1). */
    public String getAccountKey1();

    /** @return the account key (2) (R/3-field KVSL2). */
    public String getAccountKey2();

    /** @return true if I am a conditiontype where accruals should be posted. */
    public boolean isAccrual();

    /** @return print ID of corresponding condition line (R/3-field KOMK-DRUKZ). */
    public char getPrintId();

    /** @return the control for the fixation of rates under influence of the fixation group*/
    public char getFixationGroupFixRate();

    /** @return the number of the condition base formula which is called after my condition base
     * value is calculated.
     */
    public int getConditionBaseFormulaNumber();

    /** @return the number of the condition value formula which is called after my condition
     * value is calculated.
     */
    public int getConditionValueFormulaNumber();

    /** @return the number of the condition scale base formula which is called after my condition scale base
     * value is calculated.
     */
    public int getScaleBaseFormulaNumber();

    public ICurrencyUnit getAlternativeCurrencyUnit();
    public IDimensionalValue getAlternativeConditionBase();
    public ICurrencyValue getAlternativeConditionValue();
     
}
