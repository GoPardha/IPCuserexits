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
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ValueFormulaAdapter;


/**
 * @author John Stanislaus
 * Formula 606
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ZDailyProrateACV extends ValueFormulaAdapter {
	
	private static UserexitLogger uelogger = new UserexitLogger(ZDailyProrateACV.class);
	private static final int YEAR_DAYS = 365;
	
    public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
    	
    	if (uelogger.isLogDebug())
    		uelogger.writeLogDebug("Userexit ZDailyProrateACV :");
    	
    	BigDecimal baseval = pricingCondition.getConditionBase().getValue();
    	
    	
    	
    	String billType = pricingItem.getAttributeValue("ZBILL_TYPE");
    	
    	 if (uelogger.isLogDebug()){
   	    	 uelogger.writeLogDebug("Bill Type:"+billType);
    	       }
    	
    	//Start: Change as per requirement given on 26.06.2018
    	//String itemType = pricingItem.getAttributeValue("ZITM_TYPE");
    	String zoneTimeCharge = pricingItem.getAttributeValue("ZONE_TIME_CHARGE");
    	//Start: Change as per DaaS requirement given on 13.06.2019
    	String zbill_term = pricingItem.getAttributeValue("ZBILL_TERM");
       	if(zoneTimeCharge.equals("X")) //End: Change as per requirement given on 26.06.2018
	   		return baseval;
      
        if(billType.equals("U"))
	   		return baseval;

    	//Start:Change as per ABACUS requirement for Annual Evergreen Billing 07/21/2020 - Raja Narayanan  
        if(billType.equals("R") && zbill_term.equals("E"))
	   		return baseval;
	    //End: Change as per ABACUS requirement for Annual Evergreen Billing 07/21/2020 - Raja Narayanan   
        
        if (uelogger.isLogDebug()) {
        	uelogger.writeLogDebug("baseval :"+baseval);
        }
    	
    	String contractStartDate = pricingItem.getAttributeValue("CONTRACT_START_DATE");
        String contractEndDate = pricingItem.getAttributeValue("CONTRACT_END_DATE");
        
      
       
        if (uelogger.isLogDebug()) {
        	uelogger.writeLogDebug("end :"+contractEndDate);
        	uelogger.writeLogDebug("start :"+contractStartDate);
        }
        
        if(contractStartDate == null || contractStartDate.length() == 0 || 
        		"0".equals(contractStartDate.trim()) ||
        		"00000000".equals(contractStartDate.substring(0,8)) ){
        	uelogger.writeLogDebug("Inside startdate 0 check :");
        	return PricingTransactiondataConstants.ZERO;
        	
        }else{
        	contractStartDate = contractStartDate.trim().substring(0,8);
        }
        
        if(contractEndDate == null || contractEndDate.length() == 0 ||
        		"0".equals(contractEndDate.trim()) ||
        		"00000000".equals(contractEndDate.substring(0,8))) {
        	uelogger.writeLogDebug("Inside enddate 0 check :");
        	return PricingTransactiondataConstants.ZERO;
        }else{
        	
        	contractEndDate = contractEndDate.trim().substring(0,8);
        }
        
   	    long daysContractdate = 0;
   	     
   	    BigDecimal ccval = new BigDecimal("0");
   	    SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMdd");  

   	    // 11/16/2018 - Added for getTime()  method to be consistent across servers in different regions.
   	    myFormat.setTimeZone(TimeZone.getTimeZone("GMT"));      	    
   	    
	   	try {
	   		
	   		if (billType.equals("M") && zbill_term.equals("E") ){
	   			ccval =  baseval;
	   		}
	   	else {
   	       Date startdate = myFormat.parse(contractStartDate);
   	       Date enddate = myFormat.parse(contractEndDate);
   	       
   	       if (uelogger.isLogDebug()){
   	    	 uelogger.writeLogDebug("Contract Start Date with Time:"+startdate.getTime());
    	       }

   	       if (uelogger.isLogDebug()){
   	    	 uelogger.writeLogDebug("Contract End Date with Time:"+enddate.getTime());
    	       }
    	   
   	       long diff = enddate.getTime() - startdate.getTime();
   	       
   	       if (uelogger.isLogDebug()){
   	    	 uelogger.writeLogDebug("enddate - startdate diff:"+diff);
    	       }

   	       if(diff == 0)
   	    	   return PricingTransactiondataConstants.ZERO;
   	       
   	       daysContractdate = ((diff / (1000*60*60*24)) + 1); 
   	       
   	       BigDecimal contractDuration = new BigDecimal(daysContractdate).divide(new BigDecimal(YEAR_DAYS), 5, BigDecimal.ROUND_HALF_UP);//contract duration, rounding till 5 decimal places
   	     
   	       if (uelogger.isLogDebug()){
   	    	 uelogger.writeLogDebug("contractEndDate - startdate :"+daysContractdate);
   	    	 uelogger.writeLogDebug("contractEndDate - startdate/365 :"+contractDuration);
    	       }
   	       
   	       //  Begin : Added changes by Sateesh - 12/05/2018
   	       //  Copy base value ZNCT as ZACV  if the total contract duration is less than a year 
   	       if  ( daysContractdate >= YEAR_DAYS
   	    		   // DaaS 8/5/2019 PARDHUG - Skip less than year logic for monthly billing
   	    		|| (billType.equals("M")) // Contract date less than year & is monthly billing 
   	    		   ) {
   	    	   //   End changes by Sateesh - 12/05/2018
   	   	       if (uelogger.isLogDebug()){
   	  	    	 uelogger.writeLogDebug("Contract Days :"+daysContractdate);
   	   	       	   }
   	    	   ccval = new BigDecimal("1.00")
   	    			   .divide(contractDuration, 12,BigDecimal.ROUND_HALF_UP);
   	    	   //  Begin : Added changes by Sateesh - 12/05/2018 
   	       }else { 
   	    	   ccval = new BigDecimal("1.00");
   	       }
   	       //   End changes by Sateesh - 12/05/2018 
   	      
   	       	  if (uelogger.isLogDebug()){
     	    	  uelogger.writeLogDebug("ccval :"+ccval);
      	       }
   	      
   	       
   	       	  ccval=ccval.multiply(baseval);
   	     
   	       	  if (uelogger.isLogDebug()){
     	    	 uelogger.writeLogDebug("ccval :"+ccval);
     	 	 
      	       }
   	       //   DaaS End changes by Kishore - 06/06/2019
	   	}
	   		
   	       	  BigDecimal itmqty = pricingItem.getBaseQuantity().getValue();
		
   	       	  if(ccval.compareTo(PricingTransactiondataConstants.ZERO) > 0)
			   pricingCondition.setConditionRateValue(ccval.divide(itmqty,2,BigDecimal.ROUND_HALF_UP));
 
  	    } catch(Exception ex) {
  		    uelogger.writeLogDebug("exception occured");
  		    return PricingTransactiondataConstants.ZERO;
  	    }
	   	
	   	ccval = ccval.setScale(2, BigDecimal.ROUND_HALF_UP);
	   	
	   	if (uelogger.isLogDebug()){
	   		uelogger.writeLogDebug("scale ccval:"+ccval);
	   		uelogger.writeLogDebug("subtotal ccval:"+ccval.doubleValue());
	   	}
	   		
	   		
	   		return ccval;
	   	
    }
}
