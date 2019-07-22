/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.customizing;

import com.sap.spe.condmgnt.customizing.IConditionType;
import com.sap.spe.condmgnt.customizing.IUserExitFormula;
import com.sap.spe.condmgnt.exception.FormulaIsMissingException;
import com.sap.spe.conversion.IDimensionalUnit;


/**
 * Interface of the pricingConditionType-object.
 */
public interface IPricingConditionType extends IConditionType {

    /** @return a flag which specifies if manual entries are allowed.
     * (R/3-field: T685A-KMANU) */
    public char getManualEntryFlag();

    /** @return true if a manual change of the condition rate is allowed.
     * (R/3-field: T685A-KAEND_BTR) */
    public boolean isChangeOfRateAllowed();

    /** @return true if a manual change of the condition value is allowed.
     * (R/3-field: T685A-KAEND_WRT) */
    public boolean isChangeOfValueAllowed();

    /** @return true if a manual change of the numerator and denominator for quantity conversion is allowed.
       This functionality is not supported yet.
     * (R/3-field: T685A-KAEND_UFK) */
    public boolean isChangeOfConversionFactorAllowed();

    /** @return true if a manual change of the calculation rule is allowed.
       This functionality is not supported yet.
     * (R/3-field: T685A-KAEND_RCH) */
    public boolean isChangeOfCalculationTypeAllowed();

    /** @return true if a manual deletion is allowed.
     * (R/3-field: T685A-KAEND_LOE) */
    public boolean isDeletionAllowed();

    /** @return true if a pricing condition can be maintained on the pricing document level.
       This functionality is not supported yet.
     * (R/3-field: T685A-KKOPF) */
    public boolean isHeaderCondition();

    /** @return true if a pricing condition can be maintained on the pricing item level.
     * (R/3-field: T685A-KPOSI) */
    public boolean isItemCondition();

    /** @return a flag which specifies if rounding is performed.
     * (R/3-field: T685A-TXPRF) */
    public char getRoundingRule();

    /** @return a flag which specifies if the condition category.
     * (R/3-field: T685A-KNTYP) */
    public char getConditionCategory();

    /** @return a flag which specifies if the condition class.
     * (R/3-field: T685A-KOAID) */
    public char getConditionClass();

    /** @return true if currency conversion is performed for the condition value.
       @return false if currency converision is performed for the condition rate.
     * (R/3-field: T685A-GANZZ) */
    public boolean isCurrencyConversion();

    /** @return a flag which specifies if the calculation type.
     * (R/3-field: T685A-KRECH) */
    public char getCalculationType();

    /** @return true if I am a group conditiontype. The corresponding functionality is not supported yet.
     * (R/3-field: T685A-KGRPE) */
    public boolean isGroupCondition();

    /**
     * @return true if condition values are accumulated. The functionality in
     * only supported in the R/3 system.
     * (R/3-field: T685A-KOUPD)
     */
    public boolean isConditionUpdate();

    /** @return true if I am a variant conditiontype.
     * (R/3-field: T685A-KVARC) */
    public boolean isVariantCondition();

    /** @return true if I am a conditiontype for intercompany invoices. The corresponding functionality is not supported yet.
     * (R/3-field: T685A-KFKIV) */
    public boolean isInterCompanyBilling();

    /** @return true if I am a conditiontype where accruals should be posted.
       The corresponding functionality is only supported in the R/3 system.
     * (R/3-field: T685A-KRUEK) */
    public boolean isAccrual();

    /** @return true if I am a conditiontype for intercompany invoices. The corresponding functionality is not supported yet.
     * (R/3-field: T685A-KRELI) */
    public boolean isInvoiceList();

    /** @return a flag which specifies the allowed sign(s) of the condition rate.
     * (R/3-field: T685A-KNEGA) */
    public char getPlusMinus();

    public char getPricingDateFlag();

    /** @return true if the conditiontype is marked for duplication within bill of materials.
     */
    public boolean isConditionForDuplication();

    /** @return true if the conditiontype is marked for cumulation within bill of materials.
     */
    public boolean isConditionForCumulation();

    /** @return the indicator for structure conditions.
     */
    public char getStructureCondition();

    /** @return true if roundingdifference calculation should be performed.
     */
    public boolean isRoundingDifferenceCalculationRequired();

    /**@return the number of the group condition key formula.
     */
    public int getGroupConditionKeyFormulaNumber();
    
    public IUserExitFormula getGroupConditionKeyFormula() throws FormulaIsMissingException;
    
    public IUserExitFormula getScaleBaseFormula() throws FormulaIsMissingException;    



    /**@return the number of the scaleBase formula.
     */
    public int getScaleBaseFormulaNumber();

    public IDimensionalUnit getScaleBaseUnit();

    public String getDataSource();

    public IConditionPurpose getPurpose();

    public boolean isUsingHolidays();

    public char getScaleType();

    public String getScaleBaseType();

    public IPricingConditionLimits getPricingConditionLimits(char calculationType, String conditionCurrencyUnitName);
}
