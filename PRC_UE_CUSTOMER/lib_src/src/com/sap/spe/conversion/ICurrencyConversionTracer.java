package com.sap.spe.conversion;

import java.math.BigDecimal;

import com.sap.spe.conversion.ICurrencyUnit;

import com.sap.sxe.sys.SAPDate;

public interface ICurrencyConversionTracer {
	
	 void setTrace(IExchangeRate localExchangeRate,IExchangeRate foreignExchangeRate,
			IExchangeRate directExchangeRate,SAPDate conditionToLocalExchangeRateDate,SAPDate localToForeignExchangeRateDate, String exchangeRateType,
			ICurrencyUnit foreignCurrency,ICurrencyUnit localCurrency,BigDecimal foreignAmount, 
			BigDecimal localAmount, BigDecimal directAmount);

	ICurrencyConversionTraceResult getTrace();
}
