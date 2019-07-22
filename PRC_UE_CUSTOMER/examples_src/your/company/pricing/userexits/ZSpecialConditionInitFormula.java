package your.company.pricing.userexits;

import java.math.BigDecimal;

import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.PricingConditionInitFormulaAdapter;

public class ZSpecialConditionInitFormula extends PricingConditionInitFormulaAdapter {

    public void init(IPricingDocumentUserExit prDocument, IPricingItemUserExit prItem,
            IPricingConditionUserExit prCondition) {

    	if (prCondition.getConditionTypeName() != "0PR0" && prCondition.getChangeOfRateAllowed())
    		prCondition.setConditionRateValue(new BigDecimal("2"));
    }
}
