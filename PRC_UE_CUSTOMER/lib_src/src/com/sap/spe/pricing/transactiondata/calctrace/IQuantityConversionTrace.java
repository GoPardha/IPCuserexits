package com.sap.spe.pricing.transactiondata.calctrace;

import java.math.BigDecimal;

/**
 * This interface is used for the price calculation trace and stores for a 
 * pricing step the quantity conversion information.
 */
public interface IQuantityConversionTrace {

	public void setStepNumber(int stepNumber);
	public void setCounter(int counter);
	public void setSalesQuantity(BigDecimal salesQuantity);
	public void setSalesUnit(String salesUnit);
	public void setBaseQuantity(BigDecimal baseQuantity);
	public void setBaseUnit(String baseUnit);
	public void setSalesToBaseNumerator(int numerator);
	public void setSalesToBaseDenominator(int denominator);
	public void setConditionBasis(BigDecimal condBasis);
	public void setConditionUnit(String conditionUnit);
	public void setBaseToCondNumerator(int numerator);
	public void setBaseToCondDenominator(int nenominator);
	
	public int getStepNumber();
	public int getCounter();
	public BigDecimal getSalesQuantity();
	public String getSalesUnit();
	public BigDecimal getBaseQuantity();
	public String getBaseUnit();
	public int getSalesToBaseNumerator();
	public int getSalesToBaseDenominator();
	public BigDecimal getConditionBasis();
	public String getConditionUnit();
	public int getBaseToCondNumerator();
	public int getBaseToCondDenominator();
	
}
