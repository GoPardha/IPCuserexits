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


/**
 * @author d028100
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PricingTransactiondataConstants {
    public static final BigDecimal MINUS_ONE = new BigDecimal("-1");
    public static final BigDecimal ZERO = new BigDecimal("0");
    public static final BigDecimal ONE = new BigDecimal("1");
    public static final BigDecimal HUNDRED = new BigDecimal("100");
    public static final BigDecimal MINUS_HUNDRED = new BigDecimal("-100");

    public static final class EditMode {

        // flags for edit mode

        /**
         * document may be changed
         */
        public static final char READ_WRITE = 'A';

        /**
         * document may not be changed
         */
        public static final char READ_ONLY = 'B';
    }
}
