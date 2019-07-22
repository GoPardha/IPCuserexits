package your.company.pricing.userexits;

import java.math.BigDecimal;

import com.sap.spe.base.logging.UserexitLogger;
import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.pricing.transactiondata.userexit.IGroupConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingDocumentUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.spe.pricing.transactiondata.userexit.ValueFormulaAdapter;

public class ZSpecialRoundingValueFormula extends ValueFormulaAdapter {

	private static UserexitLogger userexitlogger = new UserexitLogger(
			ZSpecialRoundingValueFormula.class);

	public BigDecimal overwriteConditionValue(IPricingItemUserExit item,
			IPricingConditionUserExit condition) {
		BigDecimal result;

		ICurrencyValue val = condition.getConditionValue();
		userexitlogger.writeLogDebug("old cond value: "
				+ val.getValueAsString());

		result = val.getValue().setScale(0, BigDecimal.ROUND_HALF_UP);

		BigDecimal qnt = item.getProductQuantity().getValue();
		qnt = qnt.divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);

		userexitlogger.writeLogDebug("new cond value: " + result.subtract(qnt));

		return result.subtract(qnt);
	}

}
