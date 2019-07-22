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
 * @author Raja Narayanan formula 611 TODO Sum up Promo discounts
 */

public class ZTotalPromoDiscVal extends ValueFormulaAdapter {
	private static UserexitLogger uelogger = new UserexitLogger(ZTotalPromoDiscVal.class);

	public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
			IPricingConditionUserExit pricingCondition) {

		String exMsg = "";
		BigDecimal zpm0 = PricingTransactiondataConstants.ZERO;
		String condition = "";
		BigDecimal lv_zpma = PricingTransactiondataConstants.ZERO;
		BigDecimal lv_zpmb = PricingTransactiondataConstants.ZERO;
		BigDecimal lv_zpmc = PricingTransactiondataConstants.ZERO;
		BigDecimal lv_zpmd = PricingTransactiondataConstants.ZERO;
		BigDecimal lv_zpme = PricingTransactiondataConstants.ZERO;
		BigDecimal lv_zpmf = PricingTransactiondataConstants.ZERO;
		BigDecimal lv_zpmg = PricingTransactiondataConstants.ZERO;
		BigDecimal lv_zpmh = PricingTransactiondataConstants.ZERO;
		
		try {
			if (uelogger.isLogDebug())
				uelogger.writeLogDebug("Userexit ZTotalPromoDiscVal begin:");

			// Get Condition rate
			IPricingConditionUserExit[] prSubItems = pricingItem.getUserExitConditions();
			
			for (int j = 0; j < prSubItems.length; j++) {
				if (prSubItems[j].getConditionTypeName() == null)
					continue;
				else
					condition = prSubItems[j].getConditionTypeName().trim();
				
				//Get upfront discount rates
				if (condition.equalsIgnoreCase("ZPMA")) {
					lv_zpma = prSubItems[j].getConditionRate().getValue();
					if (uelogger.isLogDebug())
						uelogger.writeLogDebug(lv_zpma.toString());
				}
				else if (condition.equalsIgnoreCase("ZPMB")) {
					lv_zpmb = prSubItems[j].getConditionRate().getValue();
					if (uelogger.isLogDebug())
						uelogger.writeLogDebug(lv_zpmb.toString());
				} else if (condition.equalsIgnoreCase("ZPMC")) {
					lv_zpmc = prSubItems[j].getConditionRate().getValue();
					if (uelogger.isLogDebug())
						uelogger.writeLogDebug(lv_zpmc.toString());
				} else if (condition.equalsIgnoreCase("ZPMD")) {
					lv_zpmd = prSubItems[j].getConditionRate().getValue();
					if (uelogger.isLogDebug())
						uelogger.writeLogDebug(lv_zpmd.toString());
				} else if (condition.equalsIgnoreCase("ZPME")) {
					lv_zpme = prSubItems[j].getConditionRate().getValue();
					if (uelogger.isLogDebug())
						uelogger.writeLogDebug(lv_zpme.toString());
				} else if (condition.equalsIgnoreCase("ZPMF")) {
					lv_zpmf = prSubItems[j].getConditionRate().getValue();
					if (uelogger.isLogDebug())
						uelogger.writeLogDebug(lv_zpmf.toString());
				} else if (condition.equalsIgnoreCase("ZPMG")) {
				    lv_zpmg = prSubItems[j].getConditionRate().getValue();
				   if (uelogger.isLogDebug())
					uelogger.writeLogDebug(lv_zpmg.toString());
			    } else if (condition.equalsIgnoreCase("ZPMH")) {
				    lv_zpmh = prSubItems[j].getConditionRate().getValue();
				   if (uelogger.isLogDebug())
					uelogger.writeLogDebug(lv_zpmh.toString());
			    }

			}
			
//			Consolidated PROMO discounts
			zpm0 = lv_zpma.add(lv_zpmb).add(lv_zpmc).add(lv_zpmd).add(lv_zpme).add(lv_zpmf).add(lv_zpmg).add(lv_zpmh);

			// Set Conditon Rate Value
			pricingCondition.setConditionRate(zpm0, "%");
			   if (uelogger.isLogDebug())
				uelogger.writeLogDebug("Consolidated Promo ZPM0 ="+zpm0.toString());			
			zpm0 = pricingCondition.getConditionValue().getValue();

			
			
			
			if (uelogger.isLogDebug())
				uelogger.writeLogDebug("ZPM0 " + zpm0);
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
