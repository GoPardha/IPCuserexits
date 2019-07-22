package com.sap.spe.pricing.transactiondata.userexit;

import java.util.List;

import com.sap.spe.condmgnt.customizing.IAccess;
import com.sap.spe.condmgnt.customizing.IConditionType;
import com.sap.spe.condmgnt.customizing.userexit.IFormula;
import com.sap.sxe.sys.SAPTimestamp;

public interface IPricingConditionAccessFormula extends IFormula {
	
	  public final static String TYPE_NAME = "PCA"; //pricing condition access
	  
	  /**
	   * performs condition records access for given condition type, access step combination
	   * @param condType pricing condition type
	   * @param accessTimeStamp access time stamp for condition access
	   * @param access access step information
	   * @param prItem pricing item
	   * @param releaseStatus target condition record release statuses (for example released or released for simulation etc)
	   * @param resultRecordIds result of condition record access. List of condition record IDs (varnumh field)
	   * @return should be set to TRUE if access is performed from this method. Otherwise set it to FALSE. If it is set TRUE then
	   *         it indicates that logic inside formula is really performed. If it is set to false, default condition access mechanism
	   *         of IPC will be used
	   */
	  public boolean performAccess(IConditionType condType, SAPTimestamp accessTimeStamp, IAccess access, 
			                      IPricingItemUserExit prItem, char[] releaseStatus,
			                      List resultRecordIds);
}
