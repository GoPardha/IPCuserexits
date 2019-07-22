package your.company.pricing.userexits;

import com.sap.spe.conversion.IQuantityValue;
import com.sap.spe.pricing.customizing.ICopyType;
import com.sap.spe.pricing.customizing.IPricingType;
import com.sap.spe.pricing.customizing.PricingCustomizingConstants;
import com.sap.spe.pricing.transactiondata.PricingTransactiondataConstants;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.PricingCopyFormulaAdapter;

public class ZSpecialCopyFormula extends PricingCopyFormulaAdapter {

	/**
	 * Purpose: This is an example of a pricing copy formula. A pricing copy 
	 * formula is assigned to a condition type in the customizing and alters
	 * the value that the system uses to read the scales in a condition
	 * record. 
	 * 
	 * 
	 * Example:  A customer sends back one of the ordered items of an already
	 * paid-for sales order. during the copy process the conditions should be
	 * fixed and their sign should be inverted. Except for the freight costs.  
	 *  
	 */
	
	public void pricingCopy(IPricingDocumentUserExit pricingDocument,
			IPricingItemUserExit pricingItem,
			IPricingConditionUserExit pricingCondition,
			IPricingType pricingType, ICopyType copyType,
			IQuantityValue sourceSalesQuantity) {

		// set condition control (KSTEU) to 'H'
		pricingCondition
				.setConditionControl(PricingCustomizingConstants.Control.VALUE_FIXED_FOR_COST_PRICE);

		// invert condition value
		pricingCondition
				.setConditionValue(pricingCondition.getConditionValue()
						.getValue().multiply(
								PricingTransactiondataConstants.MINUS_ONE));

		// invert condition base for percentage conditions that it looks
		// consistent on the condition screen
		if (PricingCustomizingConstants.CalculationType
				.isFixedAmountOrPercentage(pricingCondition
						.getCalculationType())) {
			pricingCondition.setConditionBaseValue(pricingCondition
					.getConditionBase().getValue().multiply(
							PricingTransactiondataConstants.MINUS_ONE));
		}
	}
}
