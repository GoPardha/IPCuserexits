package com.sap.spe.conversion;

import java.math.BigDecimal;

import com.sap.spe.conversion.IExchangeRate;
import com.sap.sxe.sys.SAPDate;
import com.sap.spe.conversion.ICurrencyUnit;

public interface ICurrencyConversionTraceResult {

	    public IExchangeRate getLocalExchangeRate();

	    public IExchangeRate getForeignExchangeRate();

	    public IExchangeRate getDirectExchangeRate();
	    
	    public BigDecimal getIntermediateExchangeRate();
	    
	    public SAPDate getConditionToLocalExchangeRateDate();
	    
	    public SAPDate getLocalToForeignExchangeRateDate();
	    
	    public String getExchangeRateType();

	    public ICurrencyUnit getForeignCurrency();
	    
	    public ICurrencyUnit getLocalCurrency();
	    
	    public BigDecimal getForeignAmount();
	    
	    public BigDecimal getLocalAmount();
	    
	    public BigDecimal getDirectAmount();
	    
	    public Object clone() throws CloneNotSupportedException;
}
