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
			
			//7.26.2019 - New logic to multiply ZSR2 base rate to item quantity
   			//--Begin 7.26.2019 New logic 
   			BigDecimal zsr2Rate = new BigDecimal(0);
			//Get All the pricing conditions for the item
			IPricingConditionUserExit pricingConditions[] = pricingItem.getUserExitConditions();
			//Filter ZSR2 Conditions & add first 1 in loop
			for(int j=0;j < pricingConditions.length;j++)
			{
			  if(pricingConditions[j].getConditionTypeName() != null)
			  {
			    if(pricingConditions[j].getConditionTypeName().trim().equalsIgnoreCase("ZSR2"))
			    {
			    	zsr2Rate = zsr2Rate.add(pricingConditions[j].getConditionRate().getValue());
			    	break;
			    }
			  }
			}
			itmqty = itmqty.multiply(zsr2Rate);
			//--End 7.26.2019 New logic 
			
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
