package com.sap.spc.document.impl.userexit.csticValueSurcharge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.sap.spc.document.userexit.ICsticValueSurchargeUserExitAccess;
import com.sap.spc.document.userexit.CsticValueSurchargeFormulaAdapter;
import com.sap.spc.document.userexit.ICsticValueSurchargeUserExitAccess.IPricingConditionRate;
import com.sap.spe.document.IItem;
import com.sap.spe.pricing.masterdata.IPricingConditionRecord;
import com.sap.spe.pricing.transactiondata.IPricingCondition;

public class StandardImplementation extends CsticValueSurchargeFormulaAdapter {
	public void determinePricingConditionRates(
			ICsticValueSurchargeUserExitAccess oneToOneKeyConnection,
			String[] variantKeys, IPricingCondition[] variantPricingConditions) {

		Map pricesForVariantKeys = new HashMap();

		IItem variantItem = oneToOneKeyConnection.getItem();
		if (variantItem != null && variantItem.isRelevantForPricing()) {
			Set variantKeysWithMoreThanOneCondRecord = null;
			for (int i = 0; i < variantPricingConditions.length; i++) {
				String variantKey = variantPricingConditions[i]
						.getVariantConditionKey();
				IPricingConditionRecord prCondRecord = (IPricingConditionRecord) variantPricingConditions[i]
						.getConditionRecord();
				if (prCondRecord != null) {
					IPricingConditionRate price = oneToOneKeyConnection
							.createPricingConditionRate();
					price.setRate(prCondRecord.getConditionRate());
					price.setPricingUnit(prCondRecord.getPricingUnit());
					price.setCalculationType(prCondRecord.getCalculationType());
					if (pricesForVariantKeys.put(variantKey, price) != null) {
						if (variantKeysWithMoreThanOneCondRecord == null)
							variantKeysWithMoreThanOneCondRecord = new HashSet();
						variantKeysWithMoreThanOneCondRecord.add(variantKey);
					}
				}
			}

			// not more than one condition record is allowed for a variant key
			if (variantKeysWithMoreThanOneCondRecord != null) {
				for (Iterator iterator = variantKeysWithMoreThanOneCondRecord
						.iterator(); iterator.hasNext();) {
					pricesForVariantKeys.remove(iterator.next());
				}
			}

			// add the pricing condition rates
			for (Iterator iter = pricesForVariantKeys.entrySet().iterator(); iter
					.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				oneToOneKeyConnection.addPricingConditionRate((String) entry
						.getKey(), (IPricingConditionRate) entry.getValue());
			}
		}

		// add a dummy pricing condition rate for each variant key for which a
		// variant condition record is missing
		if (pricesForVariantKeys.size() < variantKeys.length) {
			IPricingConditionRate dummyRate = oneToOneKeyConnection
					.createPricingConditionRate();
			for (int i = 0; i < variantKeys.length; i++) {
				if (!pricesForVariantKeys.containsKey(variantKeys[i])) {
					oneToOneKeyConnection.addPricingConditionRate(
							variantKeys[i], dummyRate);
				}
			}
		}
	}

}
