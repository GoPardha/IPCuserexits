package com.citrix.pricing.userexits.req;

import com.sap.spe.base.logging.UserexitLogger;
import com.sap.spe.condmgnt.customizing.IAccess;
import com.sap.spe.condmgnt.customizing.IStep;
import com.sap.spe.condmgnt.finding.userexit.IConditionFindingManagerUserExit;
import com.sap.spe.condmgnt.finding.userexit.RequirementAdapter;

public class ZPromoDiscountReq extends RequirementAdapter{

private static UserexitLogger uelogger = new UserexitLogger(ZSparkDiscountReq.class);
	
	public boolean checkRequirement(IConditionFindingManagerUserExit item,
				IStep step, IAccess access) {
		
    	if (uelogger.isLogDebug())
    		uelogger.writeLogDebug("Userexit PromoDiscountReq :");
			
    	    //Get Condition Type - Raja Narayanan - 11/21/2018 
			String CondType = step.getConditionType( ).getName();	
			//Get Condition Type Value - Raja Narayanan - 11/21/2018 
			String DiscountFlag = item.getAttributeValue(CondType);
			
		    if (uelogger.isLogDebug())
		    	uelogger.writeLogDebug("Promo Flag for:"+CondType+" = "+DiscountFlag);
		    
		    //return value equals to "X" else return blank values - 11/21/2018 
			return DiscountFlag.equals("X");

	}
}
