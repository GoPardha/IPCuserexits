/**
 * 
 */
package com.citrix.pricing.userexits.value;

import java.math.BigDecimal;
import com.sap.spe.base.logging.UserexitLogger;
import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.pricing.customizing.PricingCustomizingConstants;
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ValueFormulaAdapter;

/**
 *@author KishoreVuppuluri formula 613 TODO Net Non USD in USD using Ex Rates(ZCFU)
 */

public class ZNetNONUSDinUSDCFU extends ValueFormulaAdapter {
	private static UserexitLogger uelogger = new UserexitLogger(ZNetNONUSDinUSDCFU.class);

	public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
			IPricingConditionUserExit pricingCondition) {

		String exMsg = "";
		BigDecimal itmqty = pricingItem.getBaseQuantity().getValue();
	
		try {
			if (uelogger.isLogDebug())
				uelogger.writeLogDebug("Userexit ZTotalPromoDiscVal begin:");

			// Set Condition Rate Value 
			pricingCondition.setConditionValue(itmqty); 
			   
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			exMsg = e.getMessage();
			if (uelogger.isLogDebug())
				uelogger.writeLogDebug("exception: " + exMsg);
		}

		return pricingCondition.getConditionValue().getValue();
	}

}
