package com.citrix.pricing.userexits.scalebase;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.sap.spe.base.logging.UserexitLogger;
import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.IGroupConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ScaleBaseFormulaAdapter;

public class ZScaleBase603 extends ScaleBaseFormulaAdapter {
	 private static UserexitLogger uelogger = new UserexitLogger(ZScaleBase603.class);

		/**
	     * @author PARDHUG
	     * scale base formula 603
		 */
		public BigDecimal overwriteScaleBase(IPricingItemUserExit pricingItem,
				IPricingConditionUserExit pricingCondition,
				IGroupConditionUserExit groupCondition) { 
			
			if (uelogger.isLogDebug())
		    		uelogger.writeLogDebug("Userexit ZScaleBase603:");
			
			//Initialize local variable 
			BigDecimal zsr2Rate = new BigDecimal(0);
			BigDecimal scaleBaseRate = new BigDecimal(0);
			BigDecimal big1 = new BigDecimal(1);
			//Get custom attribute values
	    	String zbillTerm = pricingItem.getAttributeValue("ZBILL_TERM");
	    	String zbillType = pricingItem.getAttributeValue("ZBILL_TYPE");
	    	
	    	//Initialize condition Rate Value
	    	pricingCondition.setConditionValue(big1);
	        
			if (uelogger.isLogDebug())
		    		uelogger.writeLogDebug("Bill Term:"+zbillTerm);
			if (uelogger.isLogDebug())
	    		uelogger.writeLogDebug("Bill Type:"+zbillType);
	    	
			//Get Item quantity
			BigDecimal itemQuantity = pricingItem.getBaseQuantity().getValue();
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
			//Multiply ZSR2 rate with item Quantity - ZSR2 Updated with new value
			scaleBaseRate = zsr2Rate.multiply(itemQuantity);
			if (uelogger.isLogDebug())
	    		uelogger.writeLogDebug("603 SCL Routine rate value for quantity by ZSR2:"+ scaleBaseRate);
			//Set the calculated value as Condition Rate in USD currency
			try {
				//For Monthly evergreen items determine the by
				// Order Qty*((Term End Date– Start date)/No. of days in month) * ZSR2 unit price
				if ((zbillType.equals("M") && zbillTerm.equals("E"))){
					
					if (uelogger.isLogDebug())
			    		uelogger.writeLogDebug("603 SCL Routine Montly & Evergreen Condition");
					
					String ztermDur = pricingItem.getAttributeValue("ZTERM_DUR");
			    	BigDecimal zterm_dur = new BigDecimal(ztermDur) ;
					if (uelogger.isLogDebug()) {
			        	uelogger.writeLogDebug("Term Duration :"+zterm_dur.toString());
			        }
					
					//Get contract dates 
			    	String contractStartDate = pricingItem.getAttributeValue("CONTRACT_START_DATE");
			        String contractNxtBillDate = pricingItem.getAttributeValue("ZNEXT_BILL_DATE");
					
					if (uelogger.isLogDebug()) {
			        	uelogger.writeLogDebug("Next bill Date :"+contractNxtBillDate.trim());
			        	uelogger.writeLogDebug("start :"+contractStartDate.trim());
			        	uelogger.writeLogDebug("Term Duration :"+zterm_dur);
			        }
			        
			    	        
			        if(contractStartDate == null || contractStartDate.length() == 0 || 
			        		"0".equals(contractStartDate.trim()) ||
			        		"00000000".equals(contractStartDate.substring(0,8)) ) {
			        	
			        	return PricingTransactiondataConstants.ZERO;
			        	
			        }else{
			        	contractStartDate = contractStartDate.trim().substring(0,8);
			        }
			        
			        if(contractNxtBillDate == null || contractNxtBillDate.length() == 0 || 
			        		"0".equals(contractNxtBillDate.trim()) ||
			        		"00000000".equals(contractNxtBillDate.substring(0,8))) {
			        	
			        	return PricingTransactiondataConstants.ZERO;
			        	
			        }else{
			        	
			        	contractNxtBillDate = contractNxtBillDate.trim().substring(0,8);
			        }
			        
			        SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMdd");  
			        myFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
			        Date startdate = null;
			        Date enddate = null;
					try {
						 startdate = myFormat.parse(contractStartDate);
						 enddate = myFormat.parse(contractNxtBillDate);
					} catch (ParseException e) {
						
						e.printStackTrace();
					}
			   	       
			   	       if (uelogger.isLogDebug()){
			   	    	 uelogger.writeLogDebug("Contract Start Date with Time: "+startdate.toString());
			    	       }

			   	       if (uelogger.isLogDebug()){
			   	    	 uelogger.writeLogDebug("Contract End Date with Time: "+enddate.toString());
			    	       }
			    	   
			   	       long diff = enddate.getTime() - startdate.getTime();
			   	       
			   	       diff = diff / (1000 * 60 * 60 * 24);
			   	       
			   	       BigDecimal diffdec = new BigDecimal(diff);
			   	       
			   	       if (uelogger.isLogDebug()){
			   	    	 uelogger.writeLogDebug("enddate - startdate diff: "+diff);
			    	       }
			   	       
			   	       diffdec = diffdec.setScale(5);
			   	       
			   	       BigDecimal rateBase = diffdec.divide(zterm_dur, 5);
			   	       
			   	       
			   	       if (uelogger.isLogDebug()){
			   	    	 uelogger.writeLogDebug("Rate Base: "+rateBase.toString());
			    	       }

				    	scaleBaseRate = scaleBaseRate.multiply(rateBase);

						if (uelogger.isLogDebug()) {
				        	uelogger.writeLogDebug("Scale Base with Term Duration :"+scaleBaseRate.toString());
				        }
			   	     
				}
				else {
				//For rest, including termed upfront..
				//Set ZSR2 Rate as Rate for the condition Record
					if (uelogger.isLogDebug())
			    		uelogger.writeLogDebug("603 SCL Routine Termed upfront condition");
				}
				//Set SCale Rate
				scaleBaseRate = scaleBaseRate.setScale(2, BigDecimal.ROUND_HALF_UP);
				pricingCondition.setConditionRate(scaleBaseRate, "USD");
				if (uelogger.isLogDebug())
		    		uelogger.writeLogDebug("603 SCL Routine Condition Rate Set as: "+ pricingCondition.getConditionRate().getValue());
				//Set Condition Record Base as 1 - So system is not again multiplying with
				//Quantity to populate Condition Value, just copies
				pricingCondition.setConditionBaseValue(big1);
				if (uelogger.isLogDebug())
		    		uelogger.writeLogDebug("603 SCL Routine Condition Base Value Set as: "+ pricingCondition.getConditionBase().getValue());
				
			} catch (ConversionMissingDataException e) {
				
				e.printStackTrace();
				if (uelogger.isLogDebug())
		    		uelogger.writeLogDebug("Exception while setting Condition Rate in 603 SCL routine" + e.getMessage());
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
