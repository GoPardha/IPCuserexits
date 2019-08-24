package com.citrix.pricing.userexits.scalebase;

import java.math.BigDecimal;
import java.util.Map;

import com.sap.spe.pricing.customizing.PricingCustomizingConstants;
import com.sap.spe.pricing.masterdata.IPricingScale;
import com.sap.spe.pricing.masterdata.IPricingScaleDefinition;
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.IGroupConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ScaleBaseFormulaAdapter;


import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.conversion.IDimensionalUnit;
import com.sap.spe.conversion.IDimensionalValue;
import com.sap.spe.condmgnt.masterdata.IConditionRecord;
import com.sap.spe.condmgnt.masterdata.IScale;
import com.sap.spe.condmgnt.masterdata.IScaleDefinition;
import com.sap.spe.condmgnt.masterdata.IScaleDimension;
import com.sap.spe.condmgnt.masterdata.IScaleDimensionAmount;
import com.sap.spe.condmgnt.masterdata.IScaleLevel;
import com.sap.spe.base.logging.UserexitLogger;
import com.sap.spe.condmgnt.customizing.IAccess;
import com.sap.spe.condmgnt.customizing.IStep;


public class ZScaleBaseFormula extends ScaleBaseFormulaAdapter {
	
	 private static UserexitLogger uelogger = new UserexitLogger(ZScaleBaseFormula.class);


	/**
     * @author John Stanislaus
     * scale base formula 601
	 */
	public BigDecimal overwriteScaleBase(IPricingItemUserExit pricingItem,
			IPricingConditionUserExit pricingCondition,
			IGroupConditionUserExit groupCondition) {
		
    	if (uelogger.isLogDebug())
    		uelogger.writeLogDebug("Userexit ZScaleBaseFormula:");
		
		String consumedQty = pricingItem.getAttributeValue("ZTOT_CONSUMED_QTY");
		BigDecimal totalQty = new BigDecimal("0.0");

		
    	
		if(consumedQty == null || consumedQty.trim().length() == 0) {
			return null;
		}
		else {
			consumedQty = consumedQty.trim();
			if(new BigDecimal(consumedQty).compareTo(PricingTransactiondataConstants.ZERO) == 0){
				return null;
			}
			else {
				BigDecimal newQtyToAllocate = pricingItem.getBaseQuantity().getValue();
				totalQty = newQtyToAllocate.add(new BigDecimal(consumedQty));
				
				pricingCondition.setScaleBaseValue(totalQty);
				
			}
		}
       	
    	if (uelogger.isLogDebug())
    		uelogger.writeLogDebug("Userexit ScaleBaseValue:"+totalQty.toString());

		return null;
	}

	public BigDecimal overwriteGroupScaleBase(
			IPricingDocumentUserExit pricingDocument,
			IGroupConditionUserExit groupCondition) {

		BigDecimal roundedScaleBaseValue = groupCondition.getConditionScale()
				.getValue().setScale(0, BigDecimal.ROUND_FLOOR);
		return groupCondition.getConditionScale().getValue().subtract(
				roundedScaleBaseValue);

	}
}
