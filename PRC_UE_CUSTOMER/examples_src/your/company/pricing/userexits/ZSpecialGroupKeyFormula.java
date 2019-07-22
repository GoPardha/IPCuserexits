package your.company.pricing.userexits;

import com.sap.spe.pricing.transactiondata.userexit.GroupKeyFormulaAdapter;
import com.sap.spe.pricing.transactiondata.userexit.IGroupConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;

public class ZSpecialGroupKeyFormula extends GroupKeyFormulaAdapter {
	/**
	 * Purpose: This is an example of a group key formula. A group key formula
	 * can be used to influence the basis the system uses when reading the scale
	 * of a group condition. The formula is assigned to a group condition type
	 * in customizing.
	 * 
	 * This Formula adds up the quantities / values of all of the line items in
	 * the sales document independent of which condition types have been
	 * applied.
	 * 
	 * Example: A company defines their prices with scales based on weight. When
	 * a sales order line item is priced, the user would like the system to read
	 * the scale with not just the weight of the current line item, but the
	 * combined weight of all items in the sales document. To accomplish this,
	 * the user defines their price condition types as group conditions and
	 * assigns this group key formula to them in customizing.
	 * 
	 */
	public String setGroupKey(IPricingDocumentUserExit document,
			IPricingItemUserExit item, IPricingConditionUserExit condition,
			IGroupConditionUserExit groupCondition) {
		groupCondition.setConditionTypeName("++++");
		return "002";
	}
}
