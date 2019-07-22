package com.sap.spe.pricing.transactiondata.calctrace;

import java.math.BigDecimal;
import java.util.Map;

import com.sap.spe.condmgnt.customizing.IUserExitFormula;
import com.sap.spe.pricing.customizing.IPricingStep;

/**
 * This interface is used for the price calculation trace and stores for a pricing step number
 * and the dynamic counter the trace information regarding formula processing (User Exits).
 */
public interface IFormulaStepTrace {

	public abstract IPricingStep getPricingStep();
	
	/** Returns the dynamic counter of the price result. This counter is independent from the 
	 * counter (ZAEHK) within the pricing procedure. */
	public abstract int getDynamicCounter();
	
	public abstract IUserExitFormula getBaseFormula();

	public abstract IUserExitFormula getValueFormula();

	public abstract IUserExitFormula getRequirementFormula();

	public abstract void setScaleBaseFormula(IUserExitFormula scaleBaseFormula);

	public abstract IUserExitFormula getScaleBaseFormula();

	public abstract BigDecimal getOldCondBase();

	public abstract void setOldCondBase(BigDecimal oldCondBase);

	public abstract BigDecimal getNewCondBase();

	public abstract void setNewCondBase(BigDecimal newCondBase);

	public abstract BigDecimal getOldCondValue();

	public abstract void setOldCondValue(BigDecimal oldCondValue);

	public abstract BigDecimal getNewCondValue();

	public abstract void setNewCondValue(BigDecimal newCondValue);

	public abstract void setOldScaleBase(BigDecimal oldScaleBase);

	public abstract BigDecimal getOldScaleBase();

	public abstract BigDecimal getNewScaleBase();

	public abstract void setNewScaleBase(BigDecimal newScaleBase);

	public abstract Map getGenericDeltaForBaseFormulaAttributes();

	public abstract Map getGenericDeltaForValueFormulaAttributes();

}