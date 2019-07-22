/**
 * 
 */
package com.citrix.pricing.userexits.value;

import java.math.BigDecimal;

import com.sap.spe.base.logging.UserexitLogger;
import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ValueFormulaAdapter;

/**
 * @author sivaramak formula 610 TODO Sum up upfront discounts
 */

public class ZTotalDiscVal extends ValueFormulaAdapter {
	private static UserexitLogger uelogger = new UserexitLogger(ZTotalDiscVal.class);

	public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
			IPricingConditionUserExit pricingCondition) {

		String exMsg = "";
		BigDecimal zupn = PricingTransactiondataConstants.ZERO;
		String condition = "";
		BigDecimal lv_uf1 = PricingTransactiondataConstants.ZERO;
		BigDecimal lv_uf2 = PricingTransactiondataConstants.ZERO;
		BigDecimal lv_pm4 = PricingTransactiondataConstants.ZERO;
		BigDecimal lv_pm5 = PricingTransactiondataConstants.ZERO;
		BigDecimal lv_pm6 = PricingTransactiondataConstants.ZERO;
		BigDecimal lv_pm7 = PricingTransactiondataConstants.ZERO;

		try {
			if (uelogger.isLogDebug())
				uelogger.writeLogDebug("Userexit ZTotalDiscountVal begin:");

			// Get Condition rate
			IPricingConditionUserExit[] prSubItems = pricingItem.getUserExitConditions();
			
			for (int j = 0; j < prSubItems.length; j++) {
				if (prSubItems[j].getConditionTypeName() == null)
					continue;
				else
					condition = prSubItems[j].getConditionTypeName().trim();
				
				//Get upfront discount rates
				if (condition.equalsIgnoreCase("ZUF1")) {
					lv_uf1 = prSubItems[j].getConditionRate().getValue();
					if (uelogger.isLogDebug())
						uelogger.writeLogDebug(lv_uf1.toString());
				}
				else if (condition.equalsIgnoreCase("ZUF2")) {
					lv_uf2 = prSubItems[j].getConditionRate().getValue();
					if (uelogger.isLogDebug())
						uelogger.writeLogDebug(lv_uf2.toString());
				} else if (condition.equalsIgnoreCase("ZPM4")) {
					lv_pm4 = prSubItems[j].getConditionRate().getValue();
					if (uelogger.isLogDebug())
						uelogger.writeLogDebug(lv_pm4.toString());
				} else if (condition.equalsIgnoreCase("ZPM5")) {
					lv_pm5 = prSubItems[j].getConditionRate().getValue();
					if (uelogger.isLogDebug())
						uelogger.writeLogDebug(lv_pm5.toString());
				} else if (condition.equalsIgnoreCase("ZPM6")) {
					lv_pm6 = prSubItems[j].getConditionRate().getValue();
					if (uelogger.isLogDebug())
						uelogger.writeLogDebug(lv_pm6.toString());
				} else if (condition.equalsIgnoreCase("ZPM7")) {
					lv_pm7 = prSubItems[j].getConditionRate().getValue();
					if (uelogger.isLogDebug())
						uelogger.writeLogDebug(lv_pm7.toString());
				}

			}
			
//			Total the discounts
			zupn = lv_uf1.add(lv_uf2).add(lv_pm4).add(lv_pm5).add(lv_pm6).add(lv_pm7);
			// Set Conditon Rate Value
			pricingCondition.setConditionRate(zupn, "%");
			zupn = pricingCondition.getConditionValue().getValue();

			
			
			
			if (uelogger.isLogDebug())
				uelogger.writeLogDebug("ZUPN " + zupn);
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
