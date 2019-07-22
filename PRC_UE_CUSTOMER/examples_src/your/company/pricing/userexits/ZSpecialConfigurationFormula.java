package your.company.pricing.userexits;

import com.sap.spc.document.userexit.ISPCItemUserExitAccess;
import com.sap.sce.front.base.Instance;
import com.sap.spc.document.userexit.SPCSubItemCreatedByConfigurationFormulaAdapter;

public class ZSpecialConfigurationFormula extends SPCSubItemCreatedByConfigurationFormulaAdapter {
	
	/**
	 *  all configuration sub items are pricing relevant!
	 */ 
	
	public boolean isRelevantForPricing(ISPCItemUserExitAccess subItem,
            Instance instance) {

    	return true;
    }
}
