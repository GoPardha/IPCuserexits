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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import com.sap.spe.base.logging.UserexitLogger;
import com.sap.spe.pricing.customizing.PricingCustomizingConstants;
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ValueFormulaAdapter;


/**
 * @author John Stanislaus
 * formula 601
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ZDailyProrateTCV extends ValueFormulaAdapter {
	
	private static UserexitLogger uelogger = new UserexitLogger(ZDailyProrateTCV.class);
	private static final int YEAR_DAYS = 365;
	
    public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
    	
    	if (uelogger.isLogDebug())
    		uelogger.writeLogDebug("Userexit ZDailyProrateTCV :");
    	
    	
    	BigDecimal baseval = pricingCondition.getConditionBase().getValue();
   	    BigDecimal itmqty = pricingItem.getBaseQuantity().getValue();
    	
    	if (uelogger.isLogDebug()) {
    		uelogger.writeLogDebug("Base value :"+baseval);
    		uelogger.writeLogDebug("quanity: "+itmqty);
    		}
    	
    	//Start: Change as per requirement given on 26.06.2018
    	//String itemType = pricingItem.getAttributeValue("ZITM_TYPE");
    	String zoneTimeCharge = pricingItem.getAttributeValue("ZONE_TIME_CHARGE");
    	String zbill_term = pricingItem.getAttributeValue("ZBILL_TERM");
    	String billType = pricingItem.getAttributeValue("ZBILL_TYPE");
       	String zesr = pricingItem.getAttributeValue("ZESR");
		String exchangeRate = pricingItem.getAttributeValue("ZEXCH_RATE");
		
		if(exchangeRate != null && exchangeRate.trim().length() > 0){
			exchangeRate = exchangeRate.trim();
			}
		
		BigDecimal exchangeRateValue = new BigDecimal(exchangeRate);
    	
       	if(zoneTimeCharge.equals("X"))		//End: Change as per requirement given on 26.06.201
	   		return baseval.multiply(itmqty);
       	
    	//if(zesr.equals("X"))
	   	//return baseval.multiply(itmqty);
    	
    	if (baseval.compareTo(PricingTransactiondataConstants.ZERO) == 0) {
    		return PricingTransactiondataConstants.ZERO;
    	}
    	
    	String contractStartDate = pricingItem.getAttributeValue("CONTRACT_START_DATE");
        String contractEndDate = pricingItem.getAttributeValue("CONTRACT_END_DATE");
        
        if (uelogger.isLogDebug()) {
        	uelogger.writeLogDebug("end :"+contractEndDate);
        	uelogger.writeLogDebug("start :"+contractStartDate);
        }
        
    	        
        if(contractStartDate == null || contractStartDate.length() == 0 || 
        		"0".equals(contractStartDate.trim()) ||
        		"00000000".equals(contractStartDate.substring(0,8)) ) {
        	
        	return PricingTransactiondataConstants.ZERO;
        	
        }else{
        	contractStartDate = contractStartDate.trim().substring(0,8);
        }
        
        if(contractEndDate == null || contractEndDate.length() == 0 || 
        		"0".equals(contractEndDate.trim()) ||
        		"00000000".equals(contractEndDate.substring(0,8))) {
        	
        	return PricingTransactiondataConstants.ZERO;
        	
        }else{
        	
        	contractEndDate = contractEndDate.trim().substring(0,8);
        }
        
     
        long daysBetween = 0;
    	   	     
   	    BigDecimal tcv = new BigDecimal("0");
   	    SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMdd"); 

   	    // 11/16/2018 - Added for getTime()  method to be consistent across servers in different regions.
   	    myFormat.setTimeZone(TimeZone.getTimeZone("GMT"));  
   	    
	   	try {
	   		//Begin of Insert - Daas 7/19/19 - PARDHUG
	   		//ZESR with 3 different conditions & calculate base value 
	   		//for each condition
	   		//Condition 1) Is the item consumption product? - Validated in BADi & zesr is marked True
	   		//Condition 2) Is the price type monthly(M) & bill term evergreen(E)
	   		//Condition 3) if none is true execute existing logic of proration
	   		// --- Condition 1 validation
	   		if (zesr.equals("X")){
	   			//It is a consumption product - base value is exchange rate
	   			if (uelogger.isLogDebug()){
	      	    	 uelogger.writeLogDebug("ZESR base value for Consumption prod.:"+exchangeRateValue);
	       	       }
	   			tcv =  exchangeRateValue;
	   		}
	   		// --- Condition 2 validation
	   		else if (billType.equals("M") && zbill_term.equals("E")) {
	   			//It is not consumption product but monthly bill type & term is evergreen
	   			//TAP price should be copied to ZESR
	   			BigDecimal subTotalM = pricingItem.getSubtotal(PricingCustomizingConstants.ConditionSubtotal.SUBTOTAL_M).getValue();
	   			
	   			tcv = subTotalM;

	   			if (uelogger.isLogDebug()){
	      	    	 uelogger.writeLogDebug("ZESR base value for monthly bill & evergreen term:"+tcv);
	       	       }
	   			}
	   		// --- Condition 3, as none of above are true execute existng login as below
	   		// calculate condition value by contract dates  
	   		else {
   	       Date startdate = myFormat.parse(contractStartDate);
   	       Date enddate = myFormat.parse(contractEndDate);

   	       if (uelogger.isLogDebug()){
   	    	 uelogger.writeLogDebug("Contract Start Date with Time:"+startdate.getTime());
    	       }

   	       if (uelogger.isLogDebug()){
   	    	 uelogger.writeLogDebug("Contract End Date with Time:"+enddate.getTime());
    	       }   	       
   	       
   	       long difference = enddate.getTime() - startdate.getTime();
   	       
   	       if (uelogger.isLogDebug()){
   	    	 uelogger.writeLogDebug("enddate - startdate diff:"+difference);
    	       }

   	       if(difference == 0)
   	    	   return PricingTransactiondataConstants.ZERO;
 
   	       daysBetween = (difference / (1000*60*60*24)) + 1;
   	       
   	       if (uelogger.isLogDebug()) 
   	    	   uelogger.writeLogDebug("days:enddate - startdate :"+daysBetween);
 	   
		   BigDecimal totalSellingTerm = new BigDecimal(daysBetween).divide(new BigDecimal(YEAR_DAYS), 5,BigDecimal.ROUND_HALF_UP);
		   	
				tcv = totalSellingTerm.multiply(baseval);
	   		}//DaaS added on 06.24.2019
				tcv = tcv.multiply(itmqty); // Added for New ZSER formula change given on 09.06.2018. The new formula is now base value* quantity * (((End date – Start Date)+1)/365)
				
				if (uelogger.isLogDebug())					
					uelogger.writeLogDebug("tcv for initial order:"+tcv);
				
			    if(tcv.compareTo(PricingTransactiondataConstants.ZERO) > 0)
					   pricingCondition.setConditionRateValue(tcv.divide(itmqty,2,BigDecimal.ROUND_HALF_UP));
				

	    	
  	    } catch(Exception ex) {
  		    uelogger.writeLogDebug("exception occured");
  		    return PricingTransactiondataConstants.ZERO;
  	    }
	   	
	   	tcv = tcv.setScale(2, BigDecimal.ROUND_HALF_UP);
	   	
	   	if (uelogger.isLogDebug())
	   		uelogger.writeLogDebug("subtotal xworkd value:"+tcv.doubleValue());
	   	
        return tcv;
    }
}
