package com.sap.spe.pricing.transactiondata.userexit;

import java.util.List;

import com.sap.spe.condmgnt.customizing.IAccess;
import com.sap.spe.condmgnt.customizing.IConditionType;
import com.sap.sxe.sys.SAPTimestamp;

public class PricingConditionAccessFormulaAdapter implements IPricingConditionAccessFormula {
	
	public boolean performAccess(IConditionType condType, SAPTimestamp accessTimeStamp, IAccess access,
                                IPricingItemUserExit prItem, char[] releaseStatus,
								List resultRecordIds) {
		
		return false;
	}

}
