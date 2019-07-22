package your.company.pricing.userexits;

import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.PricingItemInitFormulaAdapter;

public class ZSpecialItemInitFomula extends PricingItemInitFormulaAdapter {
	
    public void init(IPricingDocumentUserExit prDocument, IPricingItemUserExit prItem) {
    	if (prItem.isStatistical())
    		prItem.setExclusionFlag('$');
    }
}
