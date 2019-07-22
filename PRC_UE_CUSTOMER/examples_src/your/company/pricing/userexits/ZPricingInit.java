package your.company.pricing.userexits;

import com.sap.spe.document.userexit.IDocumentUserExitAccess;
import com.sap.spe.document.userexit.PricingInitFormulaAdapter;

public class ZPricingInit extends PricingInitFormulaAdapter {
	/**
	 * Example: In case the document currency is Swiss Frank, 
	 * set the rounding unit to 5. 
	 */
	public void initializeDocument(
			IDocumentUserExitAccess documentUserExitAccess) {

		if (documentUserExitAccess.getDocumentCurrency().getUnitName().equals(
				"CHF")) {
			documentUserExitAccess.setUnitToBeRoundedTo(5);
		}
	}
}
