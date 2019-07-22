package com.sap.spe.pricing.transactiondata;

public interface IPricingItemCurrencyConversionTraceResult {
	
	public IPricingConditionCurrencyConversionTraceResult getCondCurrConvTraceResult(); 
	
	public void setCondCurrConvTraceResult(IPricingConditionCurrencyConversionTraceResult condCurrConvTraceResult);
}
