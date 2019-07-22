package com.citrix.pricing.userexits.req;


import com.sap.spe.base.logging.UserexitLogger;
import com.sap.spe.condmgnt.customizing.IAccess;
import com.sap.spe.condmgnt.customizing.IStep;
import com.sap.spe.condmgnt.finding.userexit.IConditionFindingManagerUserExit;
import com.sap.spe.condmgnt.finding.userexit.RequirementAdapter;


public class ZDriveDiscountReq extends RequirementAdapter {
	
	private static UserexitLogger uelogger = new UserexitLogger(ZDriveDiscountReq.class);
	
	public boolean checkRequirement(IConditionFindingManagerUserExit item,
				IStep step, IAccess access) {

    	if (uelogger.isLogDebug())
    		uelogger.writeLogDebug("Userexit ZDriveDiscountReq :");

			String driveDiscount = item.getAttributeValue("ZDRIVE");

	    if (uelogger.isLogDebug())
	    	uelogger.writeLogDebug("driveDiscount flag:"+driveDiscount);
	    	
			return driveDiscount.equals("X");
			
	}
}


