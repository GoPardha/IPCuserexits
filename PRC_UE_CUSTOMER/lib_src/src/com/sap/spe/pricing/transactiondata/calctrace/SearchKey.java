package com.sap.spe.pricing.transactiondata.calctrace;

import java.io.Serializable;

/** This class is used as search keys for maps (HashMap, SortedMap), 
 * for example by classes FormulaTracer or ScaleTracer. */
public class SearchKey implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public int stepNumber = 0;
	/** dynamic counter of price result; is independent from counter (ZAEHK) in pricing procedure. */
	public int dynamicCounter = 1;
	
	/** overwrite Object method hashCode() */
	public int hashCode() {
		return new String(stepNumber + "_" + dynamicCounter).hashCode();
	}

	/** overwrite Object method equals */
	public boolean equals(Object arg0) {
		SearchKey key = (SearchKey)arg0;
		if (stepNumber != key.stepNumber) {
			return false;
		}
		if (dynamicCounter != key.dynamicCounter) {
			return false;
		}
		return true;
	}
}
