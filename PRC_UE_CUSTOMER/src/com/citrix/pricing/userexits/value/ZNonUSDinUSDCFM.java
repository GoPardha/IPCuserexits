/**
 * 
 */
package com.citrix.pricing.userexits.value;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.sap.spe.base.logging.UserexitLogger;
import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ValueFormulaAdapter;

/**
 *@author KishoreVuppuluri formula 612 TODO Non USD in USD using Ex Rates
 */

public class ZNonUSDinUSDCFM extends ValueFormulaAdapter {
	private static UserexitLogger uelogger = new UserexitLogger(ZNonUSDinUSDCFM.class);

	public BigDecimal overwriteConditionValue(IPricingItemUserExit pricingItem,
			IPricingConditionUserExit pricingCondition) {

		String exMsg = "";
		BigDecimal lv_zcfm = PricingTransactiondataConstants.ZERO;
		String ztermdays = pricingItem.getAttributeValue("ZTERM_DUR");
		String contractEndDate = pricingItem.getAttributeValue("CONTRACT_END_DATE");
		String contractStartDate = pricingItem.getAttributeValue("CONTRACT_START_DATE");
		String nxtBillDate = pricingItem.getAttributeValue("ZNEXT_A_BILL");
       	String billType = pricingItem.getAttributeValue("ZBILL_TYPE");
       	String zbillterm = pricingItem.getAttributeValue("ZBILL_TERM");
		BigDecimal itmqty = pricingItem.getBaseQuantity().getValue();

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
   	    long daysterm = 0;
  	     
   	   
   	    SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMdd");  
   	    
   	    myFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        
		try {
			if (uelogger.isLogDebug())
				uelogger.writeLogDebug("Userexit ZTotalPromoDiscVal begin:");

		       Date startdate = myFormat.parse(contractStartDate);
	 	       
		       Date nxtAnnualBill = myFormat.parse(nxtBillDate);
		       
		       Date enddate = myFormat.parse(contractEndDate);

		       if (uelogger.isLogDebug()){
		    	 uelogger.writeLogDebug("Contract Start Date with Time:"+startdate.getTime());
	 	       }

		       if (uelogger.isLogDebug()){
		    	 uelogger.writeLogDebug("Next Annual Bill Date with Time:"+nxtAnnualBill.getTime());
	 	       }  
		       
		    	//For monthly Evergreen send ordered quantity
		       	if (billType.equals("M") && zbillterm.equals("E")) {
				       long diff = nxtAnnualBill.getTime() - startdate.getTime();
					   daysNxtBillStdate = (diff / (1000*60*60*24));
				 	   BigDecimal contractDuration = new BigDecimal(daysNxtBillStdate).divide(new BigDecimal (ztermdays), 5, BigDecimal.ROUND_HALF_UP);//contract duration, rounding till 5 decimal places
				 	  lv_zcfm = contractDuration.multiply(itmqty);    
		       	}
		       	else {
//				       long diff = enddate.getTime() - startdate.getTime() + 1;//Duration
//				       long diff1 = nxtAnnualBill.getTime() - startdate.getTime();//First Year no of days
//					   daysNxtBillStdate = (diff / (1000*60*60*24));//Duration
//					   daysterm = (diff1 / (1000*60*60*24));//First year no of days
//				 	   BigDecimal contractDuration = new BigDecimal(daysterm).divide(new BigDecimal (daysNxtBillStdate), 5, BigDecimal.ROUND_HALF_UP);//contract duration, rounding till 5 decimal places
//				 	  lv_zcfm = contractDuration.multiply(itmqty);
		       		  lv_zcfm = itmqty;
		       	}	       

			// Set Condition Rate Value 
			pricingCondition.setConditionValue(lv_zcfm);
			   
			
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
