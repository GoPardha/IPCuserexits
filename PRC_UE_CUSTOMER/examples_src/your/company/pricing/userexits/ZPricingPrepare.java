package your.company.pricing.userexits;

import com.sap.spe.document.userexit.IItemUserExitAccess;
import com.sap.spe.document.userexit.PricingPrepareFormulaAdapter;

public class ZPricingPrepare extends PricingPrepareFormulaAdapter {

	public void addAttributeBindings(IItemUserExitAccess itemUserExitAccess) {
		// bound attribute ZLAND to value DE
		itemUserExitAccess.addAttributeBinding("ZLAND", "DE");
	}
}
