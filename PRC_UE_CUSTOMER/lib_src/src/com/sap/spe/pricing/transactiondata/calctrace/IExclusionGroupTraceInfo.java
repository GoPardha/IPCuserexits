package com.sap.spe.pricing.transactiondata.calctrace;

import java.util.Vector;

import com.sap.spe.pricing.transactiondata.IExclusionCondition;

/** Stores trace information for exclusion rules C and F. */
public interface IExclusionGroupTraceInfo {

	public abstract void clear();

	public abstract void addExclusionCondition(IExclusionCondition exclusionCondition);

	public abstract Vector getExclusionConditions();

	public abstract void setGroupName(String groupName);

	public abstract String getGroupName();
	
	public abstract void setDocCurrUnitName(String docCurrUnitName);

	public abstract String getDocCurrUnitName();

}