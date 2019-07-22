/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata;

import java.math.BigDecimal;

import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.spe.pricing.customizing.IPricingConditionType;
import com.sap.spe.pricing.transactiondata.exc.ManualChangeNotAllowedException;


/**
 * A pricing condition contains information copied from the pricing knowledge base
 * (from the customizing of the condition type and the pricing procedure), from the
 * condition record, and from the result of the calculation.
 */
public interface IPricingCondition extends IPricingConditionCommon {
    public void setConditionRecordId(String conditionRecordId);

    public void setConditionTableName(String conditionTableName);

    /**
     * Allows to change the value and the unit of the condition rate if the
     * relevant flags in the customizing of the corresponding condition type are set.
     */
    public void changeConditionRate(BigDecimal rate, String unitName, BigDecimal prUnitValue, String prUnitName)
        throws ManualChangeNotAllowedException, ConversionMissingDataException;

    // TODO: SXEInconsistentDBException;

    /** Allows to change the condition value if the relevant flags in the customizing
     * of the corresponding condition type are set. */
    public void changeConditionValue(BigDecimal value)
        throws ManualChangeNotAllowedException;

    public void changeCalculationType(char calculationType)
        throws ManualChangeNotAllowedException;

    public void changeConditionBaseValue(BigDecimal conditionBase)
        throws ManualChangeNotAllowedException;

    /** @return my header condition. This returns null if I have not been created due to
     * the manual entry of a header condition.
     */
    public IHeaderCondition getHeaderCondition();

    // TODO: temp???
    public IPricingConditionType getPricingConditionType();

    /** set the condition control.
     * (R/3-field: KONV-KSTEU) */
    public void setConditionControl(char control);

    /** set the accrual flag (R/3-field: KONV-KRUEK).
     * Maybe that the statistical flag will be changed implicitely by this method. */
    public void setAccrual(boolean accrualFlag);

    /** set the second condition unit.
     * (R/3-field: KONV-KWAEH) */
    public void setAlternativeCurrencyUnit(String currencyUnit2);
}
