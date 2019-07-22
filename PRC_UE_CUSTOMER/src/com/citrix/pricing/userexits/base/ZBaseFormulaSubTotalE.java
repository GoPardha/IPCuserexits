package com.citrix.pricing.userexits.base;

import java.math.BigDecimal;

import com.sap.spe.pricing.customizing.PricingCustomizingConstants;
import com.sap.spe.pricing.transactiondata.userexit.BaseFormulaAdapter;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;

/**
 * @author Subhro Muhuri
 * Formula 601
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ZBaseFormulaSubTotalE  extends BaseFormulaAdapter {
	public BigDecimal overwriteConditionBase(IPricingItemUserExit pricingItem,
	        IPricingConditionUserExit pricingCondition) {
	        return pricingItem.getSubtotal(PricingCustomizingConstants.ConditionSubtotal.SUBTOTAL_E).getValue();
	    }
}
