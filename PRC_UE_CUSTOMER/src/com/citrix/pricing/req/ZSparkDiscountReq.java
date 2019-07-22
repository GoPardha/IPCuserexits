package com.citrix.pricing.userexits.req;

import com.sap.spe.base.logging.UserexitLogger;
import com.sap.spe.condmgnt.customizing.IAccess;
import com.sap.spe.condmgnt.customizing.IStep;
import com.sap.spe.condmgnt.finding.userexit.IConditionFindingManagerUserExit;
import com.sap.spe.condmgnt.finding.userexit.RequirementAdapter;


public class ZSparkDiscountReq extends RequirementAdapter {
	
	private static UserexitLogger uelogger = new UserexitLogger(ZSparkDiscountReq.class);
	
	public boolean checkRequirement(IConditionFindingManagerUserExit item,
				IStep step, IAccess access) {
		
    	if (uelogger.isLogDebug())
    		uelogger.writeLogDebug("Userexit ZSparkDiscountReq :");
		
			String sparkDiscount = item.getAttributeValue("ZSPARK");
			
		    if (uelogger.isLogDebug())
		    	uelogger.writeLogDebug("sparkDiscount flag:"+sparkDiscount);

			return sparkDiscount.equals("X");

	}
}


