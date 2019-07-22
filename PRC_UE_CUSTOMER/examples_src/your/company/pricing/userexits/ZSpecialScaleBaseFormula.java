package your.company.pricing.userexits;

import java.math.BigDecimal;

import com.sap.spe.pricing.transactiondata.userexit.IGroupConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ScaleBaseFormulaAdapter;

import com.sap.spe.pricing.transactiondata.userexit.IC;

import com.sap.spe.conversion.IDimensionalValue;
import com.sap.spe.condmgnt.masterdata.IConditionRecord;
import com.sap.spe.condmgnt.masterdata.IScale;

import com.sap.spe.condmgnt.customizing.IAccess;
import com.sap.spe.condmgnt.customizing.IStep;

public class ZSpecialScaleBaseFormula extends ScaleBaseFormulaAdapter {

	/**
	 * Purpose: This is an example of a scale basis formula. A scale basis
	 * formula is assigned to a condition type in the customizing and alters the
	 * value that the system uses to read the scales in a condition record. This
	 * formula sets the whole number part of the value to zero. For example, the
	 * value 203.559 would be changed to 0.559.
	 * 
	 * Example: A company sells their products in cases. Each of their materials
	 * has a conversion factor to pallets. When an order is placed by a
	 * customer, the user would like the system to add up the quantities across
	 * items and compute the number of full pallets. If the customer does not
	 * order in full pallets, the user would like to charge a fixed surcharge of
	 * 20 USD. The user sets up a condition type in the pricing procedure. In
	 * customizing for this condition type , this scale base formula is assigned
	 * as well as the group condition flag so that the quantities across order
	 * items can be considered. Within the condition records for this condition
	 * type, the user maintains a rate of "from 0.001 PAL" a fixed charge of 20
	 * USD. If an order is placed, for example, that is equal to 10.35 pallets,
	 * this formula will alter the value to 0.35 and then read the condition
	 * record scale. A surcharge of 20 USD would then be applied to the overall
	 * sales order.
	 * 
	 */
	public BigDecimal overwriteScaleBase(IPricingItemUserExit pricingItem,
			IPricingConditionUserExit pricingCondition,
			IGroupConditionUserExit groupCondition) {

		IConditionRecord record = pricingCondition.getConditionRecord();
		IScale scale = record.getScale();
		scale.getScaleDimensions();
		
		Test test = new Test();
		
		String recid = pricingCondition.getConditionRecordId();
		
		IDimensionalValue dim = pricingCondition.getConditionScale();
		
	
		BigDecimal roundedScaleBaseValue = pricingCondition.getConditionScale()
				.getValue().setScale(0, BigDecimal.ROUND_FLOOR);
		return pricingCondition.getConditionScale().getValue().subtract(
				roundedScaleBaseValue);

	}

	public BigDecimal overwriteGroupScaleBase(
			IPricingDocumentUserExit pricingDocument,
			IGroupConditionUserExit groupCondition) {

		BigDecimal roundedScaleBaseValue = groupCondition.getConditionScale()
				.getValue().setScale(0, BigDecimal.ROUND_FLOOR);
		return groupCondition.getConditionScale().getValue().subtract(
				roundedScaleBaseValue);

	}
}
