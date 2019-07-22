/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.masterdata;

import com.sap.spe.condmgnt.masterdata.IConditionRecord;
import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.conversion.IPhysicalUnit;
import com.sap.spe.conversion.IPhysicalValue;
import com.sap.sxe.util.math.Fraction;


public interface IPricingConditionRecord extends IConditionRecord {

    // pricing fields
    public static final String CONDITION_RATE = "KBETR";
    public static final String CONDITION_CURRENCY = "KONWA";
    public static final String PRICING_UNIT = "KPEIN";
    public static final String UNIT_OF_MEASUREMENT = "KMEIN";
    public static final String CALCULATION_TYPE = "KRECH";
    public static final String NUMERATOR_CONVERTION_CONDITION_BASE_UNIT = "KUMZA";
    public static final String DENOMINATOR_CONVERTION_CONDITION_BASE_UNIT = "KUMNE";
    public static final String BASE_TEN_EXPONENT_CONVERTION_CONDITION_BASE_UNIT = "EXPNT";
    public static final String SALES_TAX_CODE = "MWSK1";
    public static final String WITHHOLDING_TAX_CODE = "MWSK2";
    public static final String BASE_UNIT = "MEINS";
    public static final String EXCLUSION_FLAG = "KZNEP";
    public static final String UPPERLIMIT = "GKWRT";
    public static final String LOWERLIMIT = "MXWRT";
    public static final String PAYMENT_TERM = "PTERM";

    public char getCalculationType();

    public ICurrencyValue getConditionRate();

    public IPhysicalValue getPricingUnit();

    public Fraction getFractionForConversionToBaseUnit();

    public IPhysicalUnit getBaseUnit();

    public String getSalesTaxCode();

    public String getWithholdingTaxCode();

    public ICurrencyValue getUpperLimit();

    public ICurrencyValue getLowerLimit();

    public String getPaymentTerm();

    /**
     * @return the condition exclusion flag (R/3 field: KONP-KZNEP)
     */
    public char getExclusionFlag();

    public boolean isUsingIntervalScales();
}
