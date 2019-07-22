/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.customizing;

public interface IPricingType {

    // pricing type names
    public static final char COPY_PRICE_COMPONENTS_AND_REDETERMINE_SCALES = 'A';
    public static final char CARRY_OUT_NEW_PRICING = 'B';
    public static final char COPY_MANUAL_PRICING_ELEMENTS_AND_REDETERMINE_THE_OHTERS = 'C';
    public static final char COPY_PRICING_ELEMENTS_UNCHANGED = 'D';
    public static final char ADOPT_PRICE_COMPONENTS_AND_FIX_VALUES = 'E';
    public static final char COPY_PRICING_ELEMENTS_TURN_VALUES_AMD_FIX = 'F';
    public static final char COPY_PRICING_ELEMENTS_UNCHANGED_AND_REDETERMINE_TAXES = 'G';
    public static final char REDETERMINE_FREIGHT_CONDITIONS = 'H';
    public static final char REDETERMINE_REBATE_CONDITIONS = 'I';
    public static final char REDETERMINE_CONFIRMED_PURCH_NET_PRICE_OR_VALUE = 'J';
    public static final char ADOPT_PRICE_COMPONENTS_AND_COSE_REDETERMINE_TAXES = 'K';
    public static final char REDETERMINE_TAXES_AND_FREIGHT_CONDITIONS = 'L';
    public static final char COPY_PRICING_ELEMENTS_TURN_VALUE = 'M';
    public static final char TRANSFER_PRICING_COMPONENTS_UNCHANGED_NEW_COST = 'N';
    public static final char REDETERMINE_VARIANT_CONDITIONS = 'O';
    public static final char ONLY_REEVALUATE = 'P';
    public static final char REDETERMINE_CALCULATION_CONDITIONS = 'Q';
    public static final char REDETERMINE_PRECIOUS_METAL_CONDITIONS = 'U';
    public static final char RESERVED_FOR_CUSTOMER_1 = '1';
    public static final char RESERVED_FOR_CUSTOMER_2 = '2';
    public static final char RESERVED_FOR_CUSTOMER_3 = '3';
    public static final char RESERVED_FOR_CUSTOMER_4 = '4';
    public static final char RESERVED_FOR_CUSTOMER_5 = '5';
    public static final char RESERVED_FOR_CUSTOMER_6 = '6';
    public static final char RESERVED_FOR_CUSTOMER_7 = '7';
    public static final char RESERVED_FOR_CUSTOMER_8 = '8';
    public static final char RESERVED_FOR_CUSTOMER_9 = '9';
    public static final char RESERVED_FOR_CUSTOMER_X = 'X';
    public static final char RESERVED_FOR_CUSTOMER_Y = 'Y';
    public static final char RESERVED_FOR_CUSTOMER_Z = 'Z';

    public char getName();

    public boolean copyCondition(IPricingConditionType conditionType);

    public boolean copyManualCondition();

    public boolean copyCondition(char conditionCategory, char conditionClass, char scaleType, char structureCondition,
        boolean isInterCompanyBilling, boolean isVariantCondition);

    public char[] getPositiveListConditionCategory();

    public char[] getNegativeListConditionCategory();

    public char[] getPositiveListConditionClass();

    public char[] getPositiveListScaleType();

    public char[] getPositiveListStructureCondition();

    public char[] getNegativeListStructureCondition();

    public Boolean copyInterCompanyBilling();

    public Boolean copyConfiguration();
  
}
