/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit;

import java.math.BigDecimal;

import com.sap.spe.conversion.ICurrencyUnit;
import com.sap.spe.conversion.IDimensionalUnit;
import com.sap.spe.conversion.IExchangeRate;
import com.sap.spe.conversion.IPhysicalUnit;
import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.spe.pricing.transactiondata.IPricingConditionCommon;
import com.sap.sxe.util.math.Fraction;


/**
 * A pricing condition contains information copied from the pricing knowledge base
 * (from the customizing of the condition type and the pricing procedure), from the
 * condition record, and from the result of the calculation. The methods of this
 * interface can be used in the SPE userexits.
 */
public interface IPricingConditionUserExit extends IPricingConditionCommon {
    public void setStatistical(boolean statistical);

    public void setOrigin(char origin);

    public void setConditionControl(char control);

    public void setCategory(char category);

    public void setConditionClass(char condClass);

    public void setInactive(char inactive);

    public void setFraction(Fraction fraction);

    public void setExchangeRate(IExchangeRate exchangeRate);

    public void setPricingUnitValue(BigDecimal prUnitValue);

    public void setPricingUnit(BigDecimal prUnitValue, IPhysicalUnit prUnit);

    public void setConditionBase(BigDecimal baseValue, IDimensionalUnit baseUnit);

    public void setScaleBaseValue(BigDecimal scaleBaseValue);

    public void setScaleBase(BigDecimal scaleBaseValue, IDimensionalUnit scaleBaseUnit);

    public void setScaleBaseValue(int dimNo, BigDecimal scaleBaseValue);

    public void setScaleBase(int dimNo, BigDecimal scaleBaseValue, IDimensionalUnit scaleBaseUnit);

    public void setConditionRateValue(BigDecimal rate);

    public void setConditionRate(BigDecimal rate, ICurrencyUnit curUnit);

    public void setConditionRate(BigDecimal rate, String curUnit)
        throws ConversionMissingDataException;

    public void setConditionValue(BigDecimal value);

    public void setAlternativeCurrencyUnit(String currencyUnit2);

    public void setGroupConditionIndicator(boolean b);

    public void setScaleBaseIndicator(String scaleBaseIndicator);

    public void setScaleType(char scaleType);

    public void setScaleBaseIndicator(int dimNo, String scaleBaseIndicator);

    public void setScaleType(int dimNo, char scaleType);

    public void setManuallyChanged(boolean isManuallyChanged);

    public void setPaymentTerm(String paymentTerm);

    public void setPrintId(char printID);

    public void setVariantConditionFactor(BigDecimal factor);

    public void setCalculationType(char calcType);

    /** set the condition base value.
     * (R/3-field: KONV-KAWRT) */
    public void setConditionBaseValue(BigDecimal baseValue);
    
    public void setFactor(BigDecimal factor);    

    /**
     * when this is set corresponding item will be recalculated during group process mode calculation
     * this is valid only for a pricing condition which is marked as group conditions
     * valid only in optimized (fast) group process mode
     * @param sourceIndicator source indicator true or false
     */
    public void setAsSourceForGroupModeItemCalculation(boolean sourceIndicator);
}
