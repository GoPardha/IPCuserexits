/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.customizing;

import com.sap.spe.condmgnt.customizing.CustomizingConstants;
import com.sap.sxe.sys.SAPString;


/**
 * customizing constants
 */
public class PricingCustomizingConstants extends CustomizingConstants {
    public static final class ConditionClass {

        // condition classes (KOAID)
        public static final char PRICE = 'B';
        public static final char DISCOUNT = 'A';
        public static final char REBATE = 'C';  //not supported yet
        public static final char TAX = 'D';
        private static final String RESERVED_IS_OIL = "FQ";
        public static final char TAX1  = 'G';

        public static final boolean isReservedForISOil(char conditionClass) {
            return SAPString.contains(RESERVED_IS_OIL, conditionClass);
        }
    }

    public static final class CalculationType {

        // calculation rules (KRECH)
        public static final char PERCENTAGE = 'A';
        public static final char FIXED_AMOUNT = 'B';
        public static final char QUANTITY_DEP = 'C';
        public static final char GROSS_WEIGHT_DEP = 'D';
        public static final char NET_WEIGHT_DEP = 'E';
        public static final char VOLUME_DEP = 'F';
        public static final char FORMULA = 'G';
        public static final char PERCENTAGE_IN_HUNDREDS = 'H';
        public static final char PERCENTAGE_TRAVEL_EXPENSES = 'I';
        public static final char POINTS = 'L';
        public static final char MONTHLY = 'M';
        public static final char YEARLY = 'N';
        public static final char DAILY = 'O';
        public static final char WEEKLY = 'P';
        public static final char DISTANCE = 'R';
        public static final char SHIPPING_UNITS = 'S';
        public static final char MULTIDIMENSIONAL = 'T';
        public static final char PERCENTAGE_FINANCING = 'U';
        public static final char COMMODITY_FORMULA = 'Q';
        public static final char INITIAL = ' ';
        private static final String FIXED_AMOUNT_OR_PERCENTAGE = "ABHIU";
        private static final String FIXED_AMOUNT_OR_MULTIDIMENSIONAL =
            new String(new char[] { FIXED_AMOUNT, MULTIDIMENSIONAL });
        private static final String PERCENTAGES = "AHIU";
        private static final String FIXED_AMOUNT_OR_PERCENTAGE_OR_FORMULA = "ABGHIU";
        private static final String RELATIVE = "CDEF";
        private static final String WEIGHT_DEPENDENT = "DE";
        private static final String PHYSICAL_UNIT_DEPENDENT = "DEFL";
        private static final String TIME_DEP = "MNOP";

        public static final boolean isPercentage(char calculationRule) {
            return SAPString.contains(PERCENTAGES, calculationRule);
        }

        public static final boolean isFixedAmountOrPercentage(char calculationRule) {
            return SAPString.contains(FIXED_AMOUNT_OR_PERCENTAGE, calculationRule);
        }

        public static final boolean isFixedAmountOrMultidimensional(char calculationRule) {
            return SAPString.contains(FIXED_AMOUNT_OR_MULTIDIMENSIONAL, calculationRule);
        }

        public static final boolean isPhysicalUnitDependent(char calculationRule) {
            return SAPString.contains(PHYSICAL_UNIT_DEPENDENT, calculationRule);
        }

        public static boolean isWeightDependent(char calculationRule) {
            return SAPString.contains(WEIGHT_DEPENDENT, calculationRule);
        }

        public static final boolean isFixedAmountOrPercentageOrFormula(char calculationRule) {
            return SAPString.contains(FIXED_AMOUNT_OR_PERCENTAGE_OR_FORMULA, calculationRule);
        }

        public static final boolean isRelative(char calculationRule) {
            return SAPString.contains(RELATIVE, calculationRule);
        }

        public static final boolean isTimeDependent(char calculationRule) {
            return SAPString.contains(TIME_DEP, calculationRule);
        }
    }

    public static final class ScaleBaseType {

        // scales (KZBZG)
        public static final String VALUE_SCALE = "B";
        public static final String QUANTITY_SCALE = "C";
        public static final String GROSSWEIGHT_SCALE = "D";
        public static final String NETWEIGHT_SCALE = "E";
        public static final String VOLUME_SCALE = "F";
        public static final String NUMBER_OF_DAYS = "O";
        public static final String NUMBER_OF_WEEKS = "P";
        public static final String DISTANCE = "R";
        public static final String NUMBER_OF_POINTS = "L";
        public static final String NUMBER_OF_MONTHS = "M";
        public static final String NUMBER_OF_YEARS = "N";
        public static final String TIME_STAMP = "TC1";
        private static final String RESERVED_IS_OIL = "TX";
        private static final String PHYSICAL_UNIT_SCALE = "DEFRL";
        private static final String UNIT_SCALE = "CDEF";
        private static final String TIME_SCALES = "MNOP";
 

        public static final boolean isTimeScale(String scaleBaseType) {
            return (scaleBaseType != null) && (scaleBaseType.length() == 1)
                && SAPString.contains(TIME_SCALES, scaleBaseType);
        }

        public static final boolean isUnitScale(String scaleBaseType) {
            return (scaleBaseType != null) && (scaleBaseType.length() == 1)
                && SAPString.contains(UNIT_SCALE, scaleBaseType);
        }

        public static final boolean isPhysicalUnitScale(String scaleBaseType) {
            return (scaleBaseType != null) && (scaleBaseType.length() == 1)
                && SAPString.contains(PHYSICAL_UNIT_SCALE, scaleBaseType);
        }

        public static final boolean isReservedForISOil(String scaleBaseType) {
            return (scaleBaseType != null) && (scaleBaseType.length() == 1)
                && SAPString.contains(RESERVED_IS_OIL, scaleBaseType);
        }
    }

    public static final class InactiveFlag {

        // inactive (KINAK)
        public static final char NOT_INACTIVE = ' ';
        public static final char INACTIVE_DUE_TO_EXCLUSION = 'A';
        public static final char INACTIVE_DUE_TO_CALCULATION_BASIS = 'K';
        public static final char INACTIVE_DUE_TO_MANUAL_ENTRY = 'M';
        public static final char INACTIVE_DUE_TO_STATISTICAL_ITEM = 'W';
        public static final char INACTIVE_DUE_TO_ERROR = 'X';
        public static final char INACTIVE_DUE_TO_SUBSEQUENT_PRICE = 'Y';
        public static final char INVISIBLE = 'Z';
        private static final String NOT_INACTIVE_OR_INACTIVE_DUE_TO_EXCLUSION_OR_INACTIVE_DUE_TO_SUBSEQUENT_PRICE =
            new String(new char[] { NOT_INACTIVE, INACTIVE_DUE_TO_EXCLUSION, INACTIVE_DUE_TO_SUBSEQUENT_PRICE });

        public static final boolean isNotInactiveOrInactiveDueToExclusionOrInactiveDueToSubsequentPrice(
            char inactiveFlag) {
            return SAPString.contains(NOT_INACTIVE_OR_INACTIVE_DUE_TO_EXCLUSION_OR_INACTIVE_DUE_TO_SUBSEQUENT_PRICE,
                inactiveFlag);
        }
    }

    public static final class Origin {

        // origin (KHERK)
        public static final char AUTOMATIC = 'A';
        public static final char COPIED_FROM_MAIN_ITEM = 'B';
        public static final char MANUAL = 'C';
        public static final char HEADERCONDITION = 'D';
        public static final char ITEM_TOTAL = 'E';
        public static final char SUPPLEMENT = 'F';
        public static final char EX_HEADERCONDITION = 'G';
        public static final char TAXES = 'J';
    }

    public static final class Control {

        // control (KSTEU)
        public static final char ADJUST_FOR_QUANTITY_VARIANCE = 'A';
        public static final char CHANGED_MANUALLY = 'C';
        public static final char FIXED = 'D';
        public static final char VALUE_AND_BASE_FIXED = 'E';
        public static final char VALUE_FIXED_FOR_BILLED_ITEMS = 'F';
        public static final char BASE_FIXED = 'G';
        public static final char VALUE_FIXED_FOR_COST_PRICE = 'H';
        private static final String FIXED_VALUE_OR_COST_OR_BILLED = "EFH";
        private static final String FIXED_DUE_TO_COST_OR_BILLED_ITEM = "FH";

        public static final boolean isFixedValueOrCostOrBilled(char control) {
            return SAPString.contains(FIXED_VALUE_OR_COST_OR_BILLED, control);
        }

        public static final boolean isFixedDueToCostOrBilledItem(char control) {
            return SAPString.contains(FIXED_DUE_TO_COST_OR_BILLED_ITEM, control);
        }
    }

    public static final class StructureIndicator {

        // structure condition (KDUPL)
        public static final char INITIAL = ' ';
        public static final char CONDITION_TO_COPY = 'A';
        public static final char CONDITION_FOR_CUMULATION = 'B';
    }

    // customizing of purposes (condition functions)
    public static final class CumulateStatisticalConditions {
        public static final char ALL = ' ';
        public static final char ONLY_NON_STATISTICAL = 'A';
        public static final char ONLY_STATISTICAL = 'B';
    }

    // customizing of purposes (condition functions)
    public static final class CumulateInactiveConditions {
        public static final char ALL = ' ';
        public static final char ONLY_ACTIVE = 'A';
        public static final char ONLY_INACTIVE = 'B';
    }

    public static final class Category {

        // category (KNTYP)
        public static final char VPRS = 'G';
        public static final char SKONTO = 'E';
        public static final char FREIGHT = 'F';
        public static final char PAYMENT = 'g';

        // ...
        public static final char VARIANTS = 'O';

        // ....
        public static final char DOWN_PAYMENT = 'e';
        public static final char FREE_GOODS_INCLUSIVE = 'f';

        // TODO:
        //public static final String  COST                             = "GST";
        public static final char INITIAL = ' ';
        private static final String EXTERNAL_TAX_CALCULATION = "1234";
        private static final String RESERVED_IS_OIL = "-0m";

        public static final boolean isExternalTaxCalculation(char category) {
            return SAPString.contains(EXTERNAL_TAX_CALCULATION, category);
        }

        public static final boolean isReservedForISOil(char category) {
            return SAPString.contains(RESERVED_IS_OIL, category);
        }
    }

    public static final class ManualEntry {

        //manual entries
        public static final char ALLOWED = ' ';
        public static final char AUTOMATIC_COND_HAS_PRIORITY = 'B';
        public static final char MANUAL_COND_HAS_PRIORITY = 'C';
        public static final char NO_CHANGES_ALLOWED = 'D';
    }

    /**
     * Controls whether and in which fields condition amounts or subtotals
     * (for example, a customer discount or the cost of a material) are stored.
     * @see IPricingStep
     */
    public static final class ConditionSubtotal {

        /**
         * No separate sub-totals
         */
        public static final char NO_SEPARATE_SUBTOTAL = ' ';

        /**
         * Carry over value to the R/3 field KOMP-KZWI1
         */
        public static final char SUBTOTAL_1 = '1';

        /**
         * Carry over value to the R/3 field KOMP-KZWI2
         */
        public static final char SUBTOTAL_2 = '2';

        /**
         * Carry over value to the R/3 field KOMP-KZWI3
         */
        public static final char SUBTOTAL_3 = '3';

        /**
         * Carry over value to the R/3 field KOMP-KZWI4
         */
        public static final char SUBTOTAL_4 = '4';

        /**
         * Carry over value to the R/3 field KOMP-KZWI5
         */
        public static final char SUBTOTAL_5 = '5';

        /**
         * Carry over value to the R/3 field KOMP-KZWI6
         */
        public static final char SUBTOTAL_6 = '6';

        /**
         * Carry over value to the R/3 field KOMP_BONBA (rebate basis 1)
         */
        public static final char REBATE_BASIS = '7';

        /**
         * Copy values according to the R/3 field KOMP-PREVA (preference value)
         */
        public static final char PREFERENCE_VALUE = '8';

        /**
         * Copy values to the R/3 field KOMP-BRTWR (gross value)
         */
        public static final char GROSS_VALUE = '9';

        /**
         * Carry over price to the R/3 field KOMP-CMPRE (credit price)
         */
        public static final char CREDIT_PRICE = 'A';

        /**
         * Carry over value to the R/3 field KOMP-WAVWR (cost)
         */
        public static final char COST = 'B';

        /**
         * Carry over value to the R/3 field KOMP-GRWRT (statistical value for foreign trade)
         */
        public static final char STATISTICAL_VALUE = 'C';

        /**
         * Copy values to the R/3 field KOMP-EFFWR (effective value)
         */
        public static final char EFFECTIVE_VALUE = 'S';

        /**
         * Copy value to the R/3 field XWORKD
         */
        public static final char SUBTOTAL_D = 'D';

        /**
         * Copy value to the R/3 field XWORKE
         */
        public static final char SUBTOTAL_E = 'E';

        /**
         * Copy value to the R/3 field XWORKF
         */
        public static final char SUBTOTAL_F = 'F';

        /**
         * Copy value to the R/3 field XWORKG
         */
        public static final char SUBTOTAL_G = 'G';

        /**
         * Copy value to the R/3 field XWORKH
         */
        public static final char SUBTOTAL_H = 'H';

        /**
         * Copy value to the R/3 field XWORKI
         */
        public static final char SUBTOTAL_I = 'I';

        /**
         * Copy value to the R/3 field XWORKJ
         */
        public static final char SUBTOTAL_J = 'J';

        /**
         * Copy value to the R/3 field XWORKK
         */
        public static final char SUBTOTAL_K = 'K';

        /**
         * Copy value to the R/3 field XWORKL
         */
        public static final char SUBTOTAL_L = 'L';

        /**
         * Copy value to the R/3 field XWORKM
         */
        public static final char SUBTOTAL_M = 'M';

        /**
         * Reserved (IS-OIL)
         */
        public static final char RESERVED_IS_OIL_1 = 'Q';

        /**
         * Reserved (IS-OIL)
         */
        public static final char RESERVED_IS_OIL_2 = 'Y';

        /**
         * Reserved (IS-OIL)
         */
        public static final char RESERVED_IS_OIL_3 = 'Z';
    }

    public static final class StepNumber {
        public static final int MAX = 99999999;
        public static final int MAX_PLUS_ONE = 100000000;
        public static final int MIN = 0;
    }

    public static final class PrintID {

        // Print ID for condition lines of a pricing step
        public static final char NOT_TO_BE_PRINTED = ' ';
        public static final char FORMER_PROCEDURE_AT_ITEM_LEVEL = 'X';
        public static final char FORMER_PROCEDURE_IN_TOTAL = 'S';
        public static final char IN_TOTAL = 'A';
        public static final char IN_TOTAL_IF_VALUE_NON_ZERO = 'B';
        public static final char IN_TOTAL_IF_VALUE_NOT_EQUAL_PREVIOUS_VALUE = 'C';
        public static final char IN_TOTAL_IF_VALUE_NON_ZERO_AND_NOT_EQUAL_PREVIOUS_VALUE = 'D';
        public static final char AT_ITEM_LEVEL = 'a';
        public static final char AT_ITEM_LEVEL_IF_VALUE_NON_ZERO = 'b';
        public static final char AT_ITEM_LEVEL_IF_VALUE_NOT_EQUAL_PREVIOUS_VALUE = 'c';
        public static final char AT_ITEM_LEVEL_IF_VALUE_NON_ZERO_AND_NOT_EQUAL_PREVIOUS_VALUE = 'd';
        private static final String OLD_PRINT_IDS =
            new String(new char[] { FORMER_PROCEDURE_AT_ITEM_LEVEL, FORMER_PROCEDURE_IN_TOTAL });
        private static final String NEW_HEADER_PRINT_IDS =
            new String(new char[] {
                    IN_TOTAL, IN_TOTAL_IF_VALUE_NON_ZERO, IN_TOTAL_IF_VALUE_NOT_EQUAL_PREVIOUS_VALUE,
                    IN_TOTAL_IF_VALUE_NON_ZERO_AND_NOT_EQUAL_PREVIOUS_VALUE
                });
        private static final String NEW_ITEM_PRINT_IDS =
            new String(new char[] {
                    AT_ITEM_LEVEL, AT_ITEM_LEVEL_IF_VALUE_NON_ZERO, AT_ITEM_LEVEL_IF_VALUE_NOT_EQUAL_PREVIOUS_VALUE,
                    AT_ITEM_LEVEL_IF_VALUE_NON_ZERO_AND_NOT_EQUAL_PREVIOUS_VALUE
                });

        public static final boolean isOldPrintID(char printID) {
            return SAPString.contains(OLD_PRINT_IDS, printID);
        }

        public static final boolean isNewHeaderPrintID(char printID) {
            return SAPString.contains(NEW_HEADER_PRINT_IDS, printID);
        }

        public static final boolean isNewItemPrintID(char printID) {
            return SAPString.contains(NEW_ITEM_PRINT_IDS, printID);
        }
    }

    public static final class RoundingRule {
        public static final char COMMERCIAL = ' ';
        public static final char ROUND_UP = 'A';
        public static final char ROUND_DOWN = 'B';
    }

    public static final class ControlIdForSign {
        public static final char POSITIVE_AND_NEGATIVE = ' ';
        public static final char NEGATIVE = 'X';
        public static final char POSITIVE = 'A';
    }

    public static final class FixGroupItemFixRate {
        public static final char NOT_FIXATED = ' ';
        public static final char CONDITION_RECORD_AND_SCALE = 'A';
    }

    public static final class MandatoryType {
        public static final char DEFAULT = ' ';
        public static final char ERROR = 'E';
        public static final char WARNING = 'W';

        public static final char checkValue(char mandatoryType) {
            switch (mandatoryType) {

                case DEFAULT:
                case ERROR:
                case WARNING:
                    return mandatoryType;  // given value is valid

                default:
                    return DEFAULT;  // given value not valid => return default value
            }
        }
    }
    
    public static final class ExclusionProcedure {
        public static final char BEST_BETWEEN_CONDITION_TYPES = 'A';
        public static final char BEST_WITHIN_CONDITION_TYPE = 'B';
        public static final char BEST_BETWEEN_TWO_EXCL_GROUPS = 'C';
        public static final char EXCLUDE_2ND_EXCL_GROUP_BY_MATCH_OF_1ST_EXCL_GROUP = 'D';
        public static final char LEAST_FAVORABLE_WITHIN_COND_TYPE = 'E';
        public static final char LEAST_FAVORABLE_BETWEEN_TWO_EXCL_GROUPS = 'F';
        public static final char LEAST_FAVORABLE_BETWEEN_CONDITION_TYPES = 'L';
    }

    public static class UserExit {

        // event type
        public static final char VALUE_FORMULA = 'A';
        public static final char BASE_FORMULA = 'B';
        public static final char SCALE_BASE_FORMULA = 'C';
        public static final char GROUP_KEY_FORMULA = 'D';
        public static final char COPY_FORMULA = 'E';

        // range limits
        public static final int STANDARD_LOWER_LIMIT_1 = 1;
        public static final int STANDARD_UPPER_LIMIT_1 = 599;
        public static final int STANDARD_LOWER_LIMIT_2 = 10000;
        public static final int CUSTOMER_LOWER_LIMIT = 600;
        public static final int CUSTOMER_UPPER_LIMIT = 999;
        public static final int IBU_LOWER_LIMIT = 1000;
        public static final int IBU_UPPER_LIMIT = 9999;
        public static final int GROUPKEY_STANDARD_LOWER_LIMIT_1 = 1;
        public static final int GROUPKEY_STANDARD_UPPER_LIMIT_1 = 59;
        public static final int GROUPKEY_STANDARD_LOWER_LIMIT_2 = 1000;
        public static final int GROUPKEY_CUSTOMER_LOWER_LIMIT = 60;
        public static final int GROUPKEY_CUSTOMER_UPPER_LIMIT = 99;
        public static final int GROUPKEY_IBU_LOWER_LIMIT = 100;
        public static final int GROUPKEY_IBU_UPPER_LIMIT = 999;

        // check routines
        public static boolean isStandardFormula(int formula, char type) {
            switch (type) {

                case VALUE_FORMULA:
                case BASE_FORMULA:
                case SCALE_BASE_FORMULA:
                case COPY_FORMULA:
                    return (((formula >= STANDARD_LOWER_LIMIT_1) && (formula <= STANDARD_UPPER_LIMIT_1))
                        || (formula >= STANDARD_LOWER_LIMIT_2));

                case GROUP_KEY_FORMULA:
                    return (((formula >= GROUPKEY_STANDARD_LOWER_LIMIT_1)
                        && (formula <= GROUPKEY_STANDARD_UPPER_LIMIT_1))
                        || (formula >= GROUPKEY_STANDARD_LOWER_LIMIT_2));

                default:
                    return false;
            }
        }

        public static boolean isCustomerFormula(int formula, char type) {
            switch (type) {

                case VALUE_FORMULA:
                case BASE_FORMULA:
                case SCALE_BASE_FORMULA:
                case COPY_FORMULA:
                    return ((formula >= CUSTOMER_LOWER_LIMIT) && (formula <= CUSTOMER_UPPER_LIMIT));

                case GROUP_KEY_FORMULA:
                    return ((formula >= GROUPKEY_CUSTOMER_LOWER_LIMIT) && (formula <= GROUPKEY_CUSTOMER_UPPER_LIMIT));

                default:
                    return false;
            }
        }

        public static boolean isIBUFormula(int formula, char type) {
            switch (type) {

                case VALUE_FORMULA:
                case BASE_FORMULA:
                case SCALE_BASE_FORMULA:
                case COPY_FORMULA:
                    return ((formula >= IBU_LOWER_LIMIT) && (formula <= IBU_UPPER_LIMIT));

                case GROUP_KEY_FORMULA:
                    return ((formula >= GROUPKEY_IBU_LOWER_LIMIT) && (formula <= GROUPKEY_IBU_UPPER_LIMIT));

                default:
                    return false;
            }
        }
    }
}
