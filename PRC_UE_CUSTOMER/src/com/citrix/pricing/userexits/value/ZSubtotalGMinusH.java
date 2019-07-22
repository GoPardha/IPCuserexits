package com.citrix.pricing.userexits.value;

import java.math.BigDecimal;

import com.sap.spe.base.logging.UserexitLogger;
import com.sap.spe.pricing.customizing.PricingCustomizingConstants;
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ValueFormulaAdapter;

/**
 * @author John Stanislaus
 *
 */

public class ZSubtotalGMinusH extends ValueFormulaAdapter {
	
	private static UserexitLogger uelogger = new UserexitLogger(ZSubtotalGMinusH.class);

	public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
		
    	if (uelogger.isLogDebug())
    		uelogger.writeLogDebug("Userexit ZSubtotalGMinusH new:");

        BigDecimal xworkd =
            pricingItem.getSubtotal(PricingCustomizingConstants.ConditionSubtotal.SUBTOTAL_G).getValue();

        //Start - Added new code to make XWORKD as ZERO when it comes as NULL - 8/28/2018 - Raja Narayanan
        if (xworkd == null){
        	xworkd = PricingTransactiondataConstants.ZERO;	
        }
        //End - Added new code to make XWORKD as ZERO when it comes as NULL - 8/28/2018 - Raja Narayanan
        
        //Comment this if statement - 8/28/2018 - Raja Narayanan
       // if (xworkd.compareTo(PricingTransactiondataConstants.ZERO) != 0) {
            return xworkd.subtract(pricingItem.getSubtotal(PricingCustomizingConstants.ConditionSubtotal.SUBTOTAL_H)
                                                  .getValue());
        
        //}     //Comment this if statement - 8/28/2018 - Raja Narayanan   
        //return PricingTransactiondataConstants.ZERO; //Comment this if statement - 8/28/2018 - Raja Narayanan
        
    }
}
