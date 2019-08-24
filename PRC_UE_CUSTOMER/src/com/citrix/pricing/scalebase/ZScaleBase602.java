package com.citrix.pricing.userexits.scalebase;

import java.math.BigDecimal;

import com.sap.spe.base.logging.UserexitLogger;
import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.spe.pricing.transactiondata.userexit.IGroupConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ScaleBaseFormulaAdapter;

public class ZScaleBase602 extends ScaleBaseFormulaAdapter {
	 private static UserexitLogger uelogger = new UserexitLogger(ZScaleBase602.class);

		/**
	     * @author PARDHUG
	     * scale base formula 602
		 */
		public BigDecimal overwriteScaleBase(IPricingItemUserExit pricingItem,
				IPricingConditionUserExit pricingCondition,
				IGroupConditionUserExit groupCondition) { 
			
			if (uelogger.isLogDebug())
		    		uelogger.writeLogDebug("Userexit ZScaleBase602:");
			
			//Initialize local variable 
			BigDecimal zsr2Rate = new BigDecimal(0);
			BigDecimal big1 = new BigDecimal(1);
			//Get Item quantity
			BigDecimal itemQuantity = pricingItem.getBaseQuantity().getValue();
			
			//Initialize condition Rate Value
	    	pricingCondition.setConditionValue(big1);
			
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
			//Multiply ZSR2 rate with item Quantity
			zsr2Rate = zsr2Rate.multiply(itemQuantity);
			//Set the calculated value as Condition Rate in USD currency
			try {
				//Set ZSR2 Rate as Rate for the condition Record
				pricingCondition.setConditionRate(zsr2Rate, "USD");
				if (uelogger.isLogDebug())
		    		uelogger.writeLogDebug("602 SCL Routine Condition Rate Set as: "+ pricingCondition.getConditionRate().getValue());
				//Set Condition Record Base as 1 - So system is not again multiplying with
				//Quantity to populate Condition Value, just copies
				pricingCondition.setConditionBaseValue(big1);
				if (uelogger.isLogDebug())
		    		uelogger.writeLogDebug("602 SCL Routine Condition Base Value Set as: "+ pricingCondition.getConditionBase().getValue());
				
			} catch (ConversionMissingDataException e) {
				
				e.printStackTrace();
				if (uelogger.isLogDebug())
		    		uelogger.writeLogDebug("Exception while setting Condition Rate in 602 SCL routine" + e.getMessage());
			}
			//All values set - Just do a return as required by routines to return some value
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
