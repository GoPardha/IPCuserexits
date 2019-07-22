package com.sap.spe.pricing.transactiondata.calctrace;

import com.sap.spe.base.logging.Message;

/**
 * This interface is used for the calculation pricing trace and stores
 * for a pricing step the condition exclusion information.
 */
public interface IExclusionTraceInfo {

	public abstract String getConditionTypeName();
	public abstract void setConditionTypeName(String conditionTypeName);

	public abstract int getStepNumber();
	public abstract int getCounter();

	public abstract void setStepNumber(int stepNumber);
	public abstract void setCounter(int counter);

	public abstract char getExclusionType();
	public abstract String getExclusionTypeWithText();
	public abstract void setExclusionType(char exclusionType);

	public abstract String getExclusionGroupName();
	public abstract void setExclusionGroupName(String exclusionGroupName);

	public abstract String getReasonForExclusion();
	public abstract void setReasonForExclusion(String reasonForExclusion);
	
	public abstract Message getReasonForExclusionMessage();
	public abstract void setReasonForExclusionMessage(String msgClass, int msgNumber, String[] variables);
	public abstract void setReasonForExclusionMessage(Message message);

	public abstract String getDetails();
	public abstract void setDetails(String details);
	
	public abstract Message getDetailsMessage();
	public abstract void setDetailsMessage(String msgClass, int msgNumber, String[] variables);
	public abstract void setDetailsMessage(Message message);
	
	public abstract void setExclusionGroupTraceInfo1(IExclusionGroupTraceInfo exclGroup1);
	public abstract IExclusionGroupTraceInfo getExclusionGroupTraceInfo1();
	
	public abstract void setExclusionGroupTraceInfo2(IExclusionGroupTraceInfo exclGroup2);
	public abstract IExclusionGroupTraceInfo getExclusionGroupTraceInfo2();
	
}