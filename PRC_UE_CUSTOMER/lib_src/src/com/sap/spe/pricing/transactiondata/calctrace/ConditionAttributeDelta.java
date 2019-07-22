package com.sap.spe.pricing.transactiondata.calctrace;

import java.io.Serializable;

/** This class is used for the calculation trace of the User Exits.
 *  It stores a condition attribute before and after the call of a user exit,
 *  for example exchange rate or condition inactive flag. */
public class ConditionAttributeDelta implements Serializable {
	private static final long serialVersionUID = 1L;
	public String identifier;
	public String technicalName;
	public Object attributeBefore;
	public Object attributeAfter;
}
