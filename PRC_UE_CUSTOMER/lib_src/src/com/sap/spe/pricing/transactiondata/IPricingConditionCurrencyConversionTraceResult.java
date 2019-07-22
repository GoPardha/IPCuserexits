package com.sap.spe.pricing.transactiondata;

import java.math.BigDecimal;

import com.sap.spe.conversion.ICurrencyConversionTraceResult;
import com.sap.spe.conversion.ICurrencyValue;

public interface IPricingConditionCurrencyConversionTraceResult {
	
	public IPricingCondition getCondType();
	
	public ICurrencyConversionTraceResult getCurrencyConversionTraceResult();
	
	public void setCondType(IPricingCondition pricingCondition);
	
	public void setCurrencyConversionTraceResult(ICurrencyConversionTraceResult currencyConversionTraceResult);
	
	public String getForeignEMU();
	
	public String getLocalEMU();
	
	public String getFromEMU();
	
	public void setForeignEMU(boolean isEMUCurrency);
	
	public void setLocalEMU(boolean isEMUCurrency);
	
	public ICurrencyValue getFromCurrencyValue();
	
	public void setFromCurrencyValue(ICurrencyValue fromCurrencyValue);
	
	public void setFromEMU(boolean isEMUCurrency);
	
	public BigDecimal getDBExchangeRate();
	
	public void setDBExchangeRate(BigDecimal dbExchangeRate);
	
	public Object clone();
	
}
