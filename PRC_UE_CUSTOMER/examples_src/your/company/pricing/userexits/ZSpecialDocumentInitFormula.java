package your.company.pricing.userexits;

import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.PricingDocumentInitFormulaAdapter;

public class ZSpecialDocumentInitFormula extends PricingDocumentInitFormulaAdapter {

    public void init(IPricingDocumentUserExit prDocument) {
    	if (!prDocument.isAlwaysPerformingGroupConditionProcessing())
    		prDocument.setAlwaysPerformingGroupConditionProcessing(true);
    }
}
