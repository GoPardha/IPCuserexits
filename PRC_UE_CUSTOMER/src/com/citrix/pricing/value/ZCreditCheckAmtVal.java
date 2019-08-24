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
 * Formula 602
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ZCreditCheckAmtVal extends ValueFormulaAdapter {
	
	private static UserexitLogger uelogger = new UserexitLogger(ZCreditCheckAmtVal.class);
	private static final int YEAR_DAYS = 365;
	
    public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
        IPricingConditionUserExit pricingCondition) {
    	
    	if (uelogger.isLogDebug())
    		uelogger.writeLogDebug("Userexit ZCreditCheckAmtVal :");
    	
    	BigDecimal baseval = pricingCondition.getConditionBase().getValue();
       	String billType = pricingItem.getAttributeValue("ZBILL_TYPE");
      //Start: DaaS change as per requirement given on 06.06.2019
       	String ztermdays = pricingItem.getAttributeValue("ZTERM_DUR");
       	String zbillterm = pricingItem.getAttributeValue("ZBILL_TERM");

       	//Start: Change as per requirement given on 26.06.2018
       	//String itemType = pricingItem.getAttributeValue("ZITM_TYPE");
    	String zoneTimeCharge = pricingItem.getAttributeValue("ZONE_TIME_CHARGE");
    	
    	
       	if(zoneTimeCharge.equals("X"))		//End: Change as per requirement given on 26.06.2018
	   		return baseval;
       	
        if(billType.equals("U"))
	   		return baseval;
    	
    	if (baseval.compareTo(PricingTransactiondataConstants.ZERO) == 0) {
    		return PricingTransactiondataConstants.ZERO;
    	}
    	
    	String contractStartDate = pricingItem.getAttributeValue("CONTRACT_START_DATE");
        String contractEndDate = pricingItem.getAttributeValue("CONTRACT_END_DATE");
        
        if (uelogger.isLogDebug()) {
        	uelogger.writeLogDebug("end :"+contractEndDate);
        	uelogger.writeLogDebug("start :"+contractStartDate);
        }
        
    	String nxtBillDate = pricingItem.getAttributeValue("ZNEXT_A_BILL");
        
        if(contractStartDate == null || contractStartDate.length() == 0 || 
        		"0".equals(contractStartDate.trim()) ||
        		"00000000".equals(contractStartDate.substring(0,8)) ){
        	
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
        
        if(nxtBillDate == null || nxtBillDate.length() == 0 || 
        		"0".equals(nxtBillDate.trim()) ||
        		"00000000".equals(nxtBillDate.substring(0,8)) ) {
        	return PricingTransactiondataConstants.ZERO;
        }
        else {
        	nxtBillDate = nxtBillDate.trim().substring(0,8);
        }

   	    long daysNxtBillStdate = 0;
   	     
   	    BigDecimal cca = new BigDecimal("0");
   	    SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMdd");  
   	    
   	    // 11/16/2018 - Added for getTime()  method to be consistent across servers in different regions.
   	    myFormat.setTimeZone(TimeZone.getTimeZone("GMT"));    	    
   	    
	   	try {
   	       Date startdate = myFormat.parse(contractStartDate);
    	       
   	       Date nxtAnnualBill = myFormat.parse(nxtBillDate);

   	       if (uelogger.isLogDebug()){
   	    	 uelogger.writeLogDebug("Contract Start Date with Time:"+startdate.getTime());
    	       }

   	       if (uelogger.isLogDebug()){
   	    	 uelogger.writeLogDebug("Next Annual Bill Date with Time:"+nxtAnnualBill.getTime());
    	       }   
   	          	       
   	       
   	       long diff = nxtAnnualBill.getTime() - startdate.getTime();
   	       

   	       //  Begin of ZCCA changes Added by Sateesh : 12/07/2018
   	       // ZCCA - if contract duration is less than a year and Next bill date is greater  then contract end Dt
   	       // then consider ZACV base value as ZCCA

   	       Date enddate = myFormat.parse(contractEndDate);

   	       if (uelogger.isLogDebug()){
   	    	   uelogger.writeLogDebug("Contract End Date with Time:"+enddate.getTime());
   	       }   
   	       long contractdays = 0; long diff1 = 0;

   	       diff1 = enddate.getTime() - startdate.getTime();
   	       contractdays = (diff1 / (1000*60*60*24));
   	 // DaaS Begin of ZCCA changes Added by Kishore : 06/07/2019 
   	    if (billType.equals("M") && zbillterm.equals("E")) {
   	    //if ( (billType == "M") && (zbillterm == "E") ) {
   	 daysNxtBillStdate = (diff / (1000*60*60*24));

    	   BigDecimal contractDuration = new BigDecimal(daysNxtBillStdate).divide(new BigDecimal (ztermdays), 5, BigDecimal.ROUND_HALF_UP);//contract duration, rounding till 5 decimal places

    	   if (uelogger.isLogDebug()){
    		   uelogger.writeLogDebug("days:nxtAnnualBill - startdate :"+daysNxtBillStdate);
    		   uelogger.writeLogDebug("xworkd :"+baseval);
    		   uelogger.writeLogDebug("contractDuration :"+contractDuration);
    		   uelogger.writeLogDebug("contractDuration :"+baseval);
    	   }

    	   cca = contractDuration.multiply(baseval);
   	    }
   	// DaaS End of ZCCA changes Added by Kishore : 06/07/2019  
   	    else {
   	    	
   	       if  ( (contractdays < 365) && (enddate.compareTo(nxtAnnualBill) < 0)){

   	    	   cca = baseval;
   	    	   if (uelogger.isLogDebug()){
   	    		   uelogger.writeLogDebug("Contract End Date less than Next Bill date CCA:"+baseval);
   	    	   }  
   	       }
   	      
   	       else {
   	    	   // End of ZCCA changes Added by Sateesh : 12/07/2018 

   	    	   daysNxtBillStdate = (diff / (1000*60*60*24));

   	    	   BigDecimal contractDuration = new BigDecimal(daysNxtBillStdate).divide(new BigDecimal(YEAR_DAYS), 5, BigDecimal.ROUND_HALF_UP);//contract duration, rounding till 5 decimal places

   	    	   if (uelogger.isLogDebug()){
   	    		   uelogger.writeLogDebug("days:nxtAnnualBill - startdate :"+daysNxtBillStdate);
   	    		   uelogger.writeLogDebug("xworkd :"+baseval);
   	    		   uelogger.writeLogDebug("contractDuration :"+contractDuration);
   	    		   uelogger.writeLogDebug("contractDuration :"+baseval);
   	    	   }

   	    	   cca = contractDuration.multiply(baseval);
   	    	   
   	       }//  else end; ZCCA changes Added by Sateesh : 12/07/2018 
   	    }
   	    
		   if(cca.compareTo(PricingTransactiondataConstants.ZERO) > 0) {
			   BigDecimal itmqty = pricingItem.getBaseQuantity().getValue();
			   pricingCondition.setConditionRateValue(cca.divide(itmqty,2,BigDecimal.ROUND_HALF_UP));
		   }
  	    } catch(Exception ex) {
  		    uelogger.writeLogDebug("exception occured");
  		    return PricingTransactiondataConstants.ZERO;
  	    }
	   	
	   	cca = cca.setScale(2, BigDecimal.ROUND_HALF_UP);
	   	if (uelogger.isLogDebug())
	   		uelogger.writeLogDebug("subtotal Acv value:"+cca.doubleValue());
	   	
        return cca;
    }
}
