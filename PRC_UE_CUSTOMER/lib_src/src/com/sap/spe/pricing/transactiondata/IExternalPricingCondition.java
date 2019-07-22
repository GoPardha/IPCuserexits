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

import com.sap.sxe.sys.SAPDate;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IExternalPricingCondition {
    public String getAccountKey();

    public String getAccountKeyAccrualsOrProvisions();

    public String getApplication();

    public char getCalculationType();

    public char getCategory();

    public BigDecimal getConditionBaseValue();

    public BigDecimal getConditionBaseValueFactor();

    public BigDecimal getConditionBaseValueFactorPeriod();

    public char getConditionClass();

    public char getConditionControl();

    public String getConditionCurrency();

    public String getAlternativeConditionCurrency();

    public BigDecimal getConditionRate();

    public String getConditionRecordId();

    public String getConditionTable();

    public String getConditionType();

    public BigDecimal getConditionValue();

    public int getConversionExponentConditionUnit2BaseUnit();

    public int getConversionDenominatorConditionUnit2BaseUnit();

    public int getConversionNumeratorConditionUnit2BaseUnit();

    public int getCounter();

    public String getDataSource();

    public String getDocumentId();

    public BigDecimal getExchangeRate();

    public int getHeaderConditionCounter();

    public char getInactive();

    public String getItemId();

    public char getOrigin();

    public SAPDate getPricingDate();

    public String getPricingUnitUnit();

    public BigDecimal getPricingUnitValue();

    public BigDecimal getRoundingDifference();

    public String getTaxCode();

    public String getScaleBaseType();

    public BigDecimal getScaleBaseValue();

    public String getScaleCurrency();

    public char getScaleType();

    public String getScaleUnit();

    public int getStepNumber();

    public char getStructureCondition();

    public String getVariantCondition();

    public String getWithholdingTaxCode();

    public boolean isAccruals();

    public boolean isChangedManually();

    public boolean isConditionUpdate();

    public boolean isConfiguration();

    public boolean isGroupCondition();

    public boolean isInterCompanyBilling();

    public boolean isInvoiceList();

    public boolean isStatistical();

    public void setAccountKey(String accountKey);

    public void setAccountKeyAccrualsOrProvisions(String accountKeyAccrualsOrProvisions);

    public void setAccruals(boolean accruals);

    public void setApplication(String application);

    public void setCalculationType(char calculationType);

    public void setCategory(char category);

    public void setChangedManually(boolean changedManually);

    public void setConditionBaseValue(BigDecimal conditionBaseValue);

    public void setConditionBaseValueFactor(BigDecimal conditionBaseValueFactor);

    public void setConditionBaseValueFactorPeriod(BigDecimal conditionBaseValueFactorPeriod);

    public void setConditionClass(char conditionClass);

    public void setConditionControl(char conditionControl);

    public void setConditionCurrency(String conditionCurrency);

    public void setAlternativeConditionCurrency(String conditionCurrency);
    
    public void setAlternativeConditionBase(BigDecimal alternativeConditionBase);
    
    public void setAlternativeConditionValue(BigDecimal alternativeConditionValue);

    public void setConditionRate(BigDecimal conditionRate);

    public void setConditionRecordId(String conditionRecordId);

    public void setConditionTable(String conditionTable);

    public void setConditionType(String conditionType);

    public void setConditionUpdate(boolean conditionUpdate);

    public void setConditionValue(BigDecimal conditionValue);

    public void setConfiguration(boolean configuration);

    public void setConversionExponentConditionUnit2BaseUnit(int conversionExponentConditionUnit2BaseUnit);

    public void setConversionDenominatorConditionUnit2BaseUnit(int conversionDenominatorConditionUnit2BaseUnit);

    public void setConversionNumeratorConditionUnit2BaseUnit(int conversionNumeratorConditionUnit2BaseUnit);

    public void setCounter(int counter);

    public void setDataSource(String dataSource);

    public void setDocumentId(String documentId);

    public void setExchangeRate(BigDecimal exchangeRate);

    public void setGroupCondition(boolean groupCondition);

    public void setHeaderConditionCounter(int headerConditionCounter);

    public void setInactive(char inactive);

    public void setInterCompanyBilling(boolean interCompanyBilling);

    public void setInvoiceList(boolean invoiceList);

    public void setItemId(String itemId);

    public void setOrigin(char origin);

    public void setPricingDate(SAPDate pricingDate);

    public void setPricingUnitUnit(String pricingUnitUnit);

    public void setPricingUnitValue(BigDecimal pricingUnitValue);

    public void setRoundingDifference(BigDecimal roundingDifference);

    public void setTaxCode(String salesTaxCode);

    public void setScaleBaseType(String scaleBaseType);

    public void setScaleBaseValue(BigDecimal scaleBaseValue);

    public void setScaleCurrency(String scaleCurrency);

    public void setScaleType(char scaleType);

    public void setScaleUnit(String scaleUnit);

    public void setStatistical(boolean statistical);

    public void setStepNumber(int stepNumber);

    public void setStructureCondition(char structureCondition);

    public void setVariantCondition(String variantCondition);

    public void setWithholdingTaxCode(String withholdingTaxCode);
}
