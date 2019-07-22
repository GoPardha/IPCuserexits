/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit.internal;

import java.math.BigDecimal;

import com.sap.spe.pricing.customizing.IPricingStep;
import com.sap.spe.pricing.transactiondata.userexit.IPricingConditionUserExit;
import com.sap.spe.pricing.transactiondata.userexit.IPricingItemUserExit;
import com.sap.sxe.sys.SAPTimestamp;


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IPricingItemSAPExit extends IPricingItemUserExit {
    public boolean isGroupConditionProcessing();

    public boolean isLoadingFromExternal();

    public IPricingConditionSAPExit addTaxCondition(IPricingStep step, String conditionTypeName, boolean isStatistical,
        char printId, String accountKey1, String accountKey2, char calculationType, BigDecimal conditionRateValue,
        String conditionRateUnit, BigDecimal pricingUnitValue, String pricingUnitUnit, BigDecimal conditionBaseValue,
        BigDecimal conditionValue, String description, String taxPurp, SAPTimestamp pricingTimestamp);
    
    /**
     * checks whether conditions added by tax engine is present or not
     * @return true if it exist otherwise false
     */
    public boolean isTaxConditionsExist();
    
    public IPricingConditionUserExit[] getUserExitConditions(int fromStep, int toStep);    
}
