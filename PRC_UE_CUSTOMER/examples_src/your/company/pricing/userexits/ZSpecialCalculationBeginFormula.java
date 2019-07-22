package your.company.pricing.userexits;

import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.PricingItemCalculateBeginFormulaAdapter;

public class ZSpecialCalculationBeginFormula extends PricingItemCalculateBeginFormulaAdapter {

    public void calculationBegin(IPricingDocumentUserExit prDocument, IPricingItemUserExit prItem) {

    	if (prDocument.getPricingProcedure().getName().equals("XYZ"))
    		prDocument.setZeroPriceActive(true);
    }
}
