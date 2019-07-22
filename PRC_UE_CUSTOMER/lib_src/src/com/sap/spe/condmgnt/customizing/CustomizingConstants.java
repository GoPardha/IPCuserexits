/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.customizing;

import com.sap.sxe.sys.SAPString;


/**
 * customizing constants
 */
public class CustomizingConstants {

    /**
     * release status
     */
    public static class ReleaseStatus {

        public static final char RELEASED = ' ';
        public static final char LOCKED = 'A';
        public static final char RELEASED_FOR_SIMULATION = 'B';
    }

    public static class ScaleType {

        // scale types (STFKZ)
        public static final char FROM_SCALE = 'A';
        public static final char TO_SCALE = 'B';
        public static final char TO_INTERVAL_SCALE = 'D';
        private static final String INTERVAL_SCALES = "CD";

        public static boolean isIntervalScale(char scaleType) {
            return SAPString.contains(INTERVAL_SCALES, scaleType);
        }
    }

    public static class UserExit {

        // event type is always REQUIREMENT 
        // range limits
        public static final int STANDARD_LOWER_LIMIT_1 = 1;
        public static final int STANDARD_UPPER_LIMIT_1 = 599;
        public static final int STANDARD_LOWER_LIMIT_2 = 10000;
        public static final int CUSTOMER_LOWER_LIMIT = 600;
        public static final int CUSTOMER_UPPER_LIMIT = 999;
        public static final int IBU_LOWER_LIMIT = 1000;
        public static final int IBU_UPPER_LIMIT = 9999;

        // check routines
        public static boolean isStandardFormula(int formula) {
            if (((formula >= STANDARD_LOWER_LIMIT_1) && (formula <= STANDARD_UPPER_LIMIT_1))
                    || (formula >= STANDARD_LOWER_LIMIT_2)) {
                return true;
            }

            return false;
        }

        public static boolean isCustomerFormula(int formula) {
            if ((formula >= CUSTOMER_LOWER_LIMIT) && (formula <= CUSTOMER_UPPER_LIMIT)) {
                return true;
            }

            return false;
        }

        public static boolean isIBUFormula(int formula) {
            if ((formula >= IBU_LOWER_LIMIT) && (formula <= IBU_UPPER_LIMIT)) {
                return true;
            }

            return false;
        }
    }
}
