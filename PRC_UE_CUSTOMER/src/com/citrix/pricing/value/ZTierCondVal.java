/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.citrix.pricing.userexits.value;
        
import java.math.BigDecimal;

import com.sap.spe.base.logging.UserexitLogger;
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ValueFormulaAdapter;


/**
 * @author John Stanislaus
 * formula 603
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ZTierCondVal extends ValueFormulaAdapter {
	
	private static UserexitLogger uelogger = new UserexitLogger(ZTierCondVal.class);
	
    public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
    	
    	if (uelogger.isLogDebug())
    		uelogger.writeLogDebug("Userexit ZTierCondVal:");

		BigDecimal beginscale = pricingCondition.getIncScaleBegin();
		BigDecimal endscale = pricingCondition.getIncScaleEnd();
		
		
	try {
		BigDecimal newQtyToAllocate = pricingItem.getBaseQuantity().getValue();
		
        
		String consumedQty = pricingItem.getAttributeValue("ZTOT_CONSUMED_QTY");
		
		//Start Defect 797
		String exchangeRate = pricingItem.getAttributeValue("ZEXCH_RATE");
				
		if(exchangeRate != null && exchangeRate.trim().length() > 0){
			exchangeRate = exchangeRate.trim();
			}
		
		BigDecimal exchangeRateValue = new BigDecimal(exchangeRate);
		//End Defect 797
		
		//no extension
		if(consumedQty != null && consumedQty.trim().length() > 0){
			consumedQty = consumedQty.trim();
			
			//Removing the 0 value check as requested on 02.07.2018
//			if(new BigDecimal(consumedQty).compareTo(PricingTransactiondataConstants.ZERO) == 0)
//				return null;
			
		}
		else {
				
			return null;
		}
		
		if(newQtyToAllocate != null && newQtyToAllocate.intValue() == 0)
				return null;
		
		int consumed = new BigDecimal(consumedQty).intValue();
		int newQty = newQtyToAllocate.intValue();
		
		int totalqty = consumed + newQty;

		BigDecimal totprice = PricingTransactiondataConstants.ZERO;
		BigDecimal condRate = pricingCondition.getConditionRate().getValue();
		BigDecimal condval = PricingTransactiondataConstants.ZERO;
		BigDecimal consumedprice = PricingTransactiondataConstants.ZERO;
		
		if (uelogger.isLogDebug()) {
			
			uelogger.writeLogDebug("cond rate :"+condRate);
		}
		
		if(totalqty >= endscale.intValue()) 
		{
			uelogger.writeLogDebug("qty :"+(endscale.intValue() - beginscale.intValue()));
			totprice = condRate.multiply(new BigDecimal(endscale.intValue() - beginscale.intValue()));
			//Defect 797
			totprice = totprice.multiply(exchangeRateValue) ;
		}
		else 
		{
			if(totalqty < beginscale.intValue())
			{
				totprice = PricingTransactiondataConstants.ZERO;
				uelogger.writeLogDebug("totprice:"+totprice);
				
			}
			else {
				uelogger.writeLogDebug("qty :"+(totalqty - beginscale.intValue()));
				totprice = condRate.multiply(new BigDecimal(totalqty - beginscale.intValue()));
				//Defect 797
				totprice = totprice.multiply(exchangeRateValue) ;
			}
		}
				
		
		if (uelogger.isLogDebug()) {
			uelogger.writeLogDebug("exChangeRateValue:"+exchangeRateValue);
			uelogger.writeLogDebug("totprice after exchange rate:"+totprice);
		}

		// Calculate price for the tier if Quantity = CONSUMED
		if( consumed > endscale.intValue() ) {
			consumedprice = condRate.multiply(new BigDecimal(endscale.intValue() - beginscale.intValue()));
			//Defect 797
			consumedprice = consumedprice.multiply(exchangeRateValue);
		}
		else {
			if(consumed > beginscale.intValue()){
				consumedprice = condRate.multiply(new BigDecimal(consumed - beginscale.intValue())); 
				//Defect 797
				consumedprice = consumedprice.multiply(exchangeRateValue);
				
			}
			else {
				consumedprice = PricingTransactiondataConstants.ZERO; 
			}
		}

		if (uelogger.isLogDebug()) {
			uelogger.writeLogDebug("exChangeRateValue:"+exchangeRateValue);
			uelogger.writeLogDebug("consumedprice after exchange rate:"+consumedprice);
		}
		condval = totprice.subtract(consumedprice);
		
		if(condval.compareTo(PricingTransactiondataConstants.ZERO) <= 0)
			condval = PricingTransactiondataConstants.ZERO;
		
		if (uelogger.isLogDebug()) 
			uelogger.writeLogDebug("condval:"+condval.toString());
		
		return condval;	
		
	   } catch(Exception ex) {
  		    uelogger.writeLogDebug("exception occured:"+ex.getMessage());
  		    return null;
  	   }
	 
    }
}
