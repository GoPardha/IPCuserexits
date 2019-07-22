package your.company.pricing.userexits;

import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.PricingItemCalculateEndFormulaAdapter;

public class ZSpecialCalculationEndFormula extends PricingItemCalculateEndFormulaAdapter {

    private int stepNumber, counter;
    
    public void calculationEnd(IPricingDocumentUserExit prDocument, IPricingItemUserExit prItem) {
    	
    	stepNumber = 10;
    	counter = 1;
     	prItem.findPricingCondition(stepNumber, counter).setConditionControl('A');
    	
    }
}
