package com.sap.spe.pricing.transactiondata.calctrace;

import java.math.BigDecimal;

import com.sap.spe.conversion.IDimensionalValue;
import com.sap.spe.pricing.customizing.IPricingStep;

/**
 * This interface is used for the price calculation trace and stores
 * for a pricing step the scale information.
 */
public interface IScaleStepTrace {

	public abstract String getScaleBaseType();

	public abstract void setScaleBaseType(String scaleBaseType);

	public abstract char getScaleType();

	public abstract void setScaleType(char scaleType);

	public abstract int getScaleBaseFormulaNumber();

	public abstract void setScaleBaseFormulaNumber(int scaleBaseFormulaNumber);

	public abstract void setScaleLevelInfoText(String scaleLevelInfoText);

	public abstract String getScaleLevelInfoText();

	public abstract IDimensionalValue getScaleBaseValue();

	public abstract void setScaleBaseValue(IDimensionalValue scaleBaseValue);

	public abstract boolean isUsedInGroupProcessing();

	public abstract void setGroupProcessing(boolean isUsedInGroupProcessing);

	/** Returns the scale type with text, e.g. "A - VALUE_SCALE" */
	public abstract String getScaleTypeWithText();

	/** Returns the scale base type with text, e.g. "B - VALUE_SCALE" */
	public abstract String getScaleBaseTypeWithText();
	
	public IPricingStep getPricingStep();
	
	/** Returns the dynamic counter of the price result. This counter is independent from 
	 * the counter (ZAEHK) within the pricing procedure. */
	public abstract int getDynamicCounter();
	
	public abstract BigDecimal getScaleLevelInfoAmountQuant();
	
	public abstract String getScaleLevelInfoAmountUnit();

	public abstract BigDecimal getScaleLevelInfoAmountValue();

	public abstract String getScaleLevelInfoAmountCurr();

	public abstract BigDecimal getScaleLevelInfoRateValue();

	public abstract String getScaleLevelInfoRateCurr();
	
	/*** Returns condition factor (KFAKTOR1) */
	public abstract BigDecimal getConditionFactor();
	
	/** adjusted scale base value; used only for interval scales*/
	public BigDecimal getAdjustedScaleBaseValue();

}