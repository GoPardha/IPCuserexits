package your.company.pricing.userexits;

import java.math.BigDecimal;

import com.sap.spe.pricing.transactiondata.userexit.BaseFormulaAdapter;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;

public class ZSpecialBaseFormula extends BaseFormulaAdapter {

	/**
	 * Purpose: This is an example of a condition base value formula. A
	 * condition base value formula can be used to influence the basis the
	 * system uses when computing a pricing value. A condition base formula is
	 * assigned to a condition type in the pricing procedure.
	 * 
	 * This example formula is used to convert the basis to a whole number. For
	 * example, a basis of 300.153 would be converted to 300. This formula can
	 * be used to compute pallet discounts.
	 * 
	 * Example: A company sells their products in cases. Each of their materials
	 * has a conversion factor to pallets. When an order is placed by a
	 * customer, the user would like the system to calculate the number of full
	 * pallets for each line and to offer a 5 USD discount per full pallet
	 * ordered. The user sets up a discount condition type in the pricing
	 * procedure and assigns this condition base value formula to it. Within the
	 * condition records for this condition type, the user maintains the 5 USD
	 * per pallet discount rate. If an order line item is placed that contains
	 * 5.5 pallets, the system will adjust the base value to 5 and compute a
	 * discount of 25 USD for the sales line item.
	 * 
	 */

	public BigDecimal overwriteConditionBase(IPricingItemUserExit pricingItem,
			IPricingConditionUserExit pricingCondition) {

		return pricingCondition.getConditionBase().getValue().setScale(0,
				BigDecimal.ROUND_FLOOR);
	}
}
