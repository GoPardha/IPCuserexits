/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.conversion;

import java.math.BigDecimal;

import com.sap.sxe.sys.SAPDate;
import com.sap.vmc.logging.Category;
import com.sap.vmc.logging.LocalizableCategory;


/**
 * Represents some usefull constants for conversion
 */
public final class ConversionConstants {

    /**
     * The category in which logging information is stored.
     */
    public static final LocalizableCategory CATEGORY = LocalizableCategory.getCategory(Category.APPLICATIONS, "/AP/PRC/Conversion");
    
    /**
     * value:         0
     */
    public static final BigDecimal C_ZERO = new BigDecimal("0");

    /**
     * value:         1
     *
     */
    public static final BigDecimal C_ONE = new BigDecimal("1");
    public static final BigDecimal C_ONE_SCALE_5 = C_ONE.setScale(5);

    /**
     * value:         10
     */
    public static final BigDecimal C_TEN = new BigDecimal("10");

    /**
     * value:         -1
     */
    public static final BigDecimal C_MINUS_ONE = new BigDecimal("-1");

    /**
     * value:         999
     */
    public static final BigDecimal C_999 = new BigDecimal("999");

    /**
     * value:         999999999
     */
    public static final BigDecimal C_999999999 = new BigDecimal("999999999");

    /**
     * value:         -999999999
     */
    public static final BigDecimal C_MINUS_999999999 = new BigDecimal("-999999999");

    /**
     * value:         999999999999999
     */
    public static final BigDecimal C_MAX_RATE = new BigDecimal("999999999999999");

    /**
     * value:         100000000000
     */
    public static final BigDecimal C_10_EXP_11 = new BigDecimal("100000000000");

    /**
     * value:         100000000000000000000000000000
     */
//  public static final BigDecimal C_10_EXP_29 = new BigDecimal("100000000000000000000000000000");

    /**
     * value:         5
     */
    public static final int C_5_DECIMALS = 5;

    /**
     * value:         16
     */
    public static final int C_16_DECIMALS = 16;

    /**
     * value:         11
     */
    public static final int C_11_DECIMALS = 11;

    /**
     * value:         31
     */
    public static final int C_31_DECIMALS = 31;

    /**
     * default message area for reading T100 messages from R/3 <br>
     *
     * value:         SG
     */
    public static final String C_CONV_MSGAREA = "PRC_CNV";

    /**
     * oldes date used in currency conversion <br>
     *
     * value:         1.1.1800
     */
    public static final SAPDate C_OLDEST_DATE = new SAPDate(1800, 1, 1);

    /**
     * newest date used in currency conversion<br>
     *
     * value:         31.12.9999
     */
    public static final SAPDate C_NEWEST_DATE = new SAPDate(9999, 12, 31);
}
