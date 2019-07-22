package com.sap.spe.freegoods.transactiondata.userexit;

import java.math.BigDecimal;

public interface IFreeGoodsConditionUserExit {

    public interface ReasonForZeroQuantity {
	    public static final char OKAY = ' ';
	    public static final char MIN_QUANTITY_NOTMET = '1';
	    public static final char QUANTITY_NOT_INWHOLEUNITS = '2';
	    public static final char CALCULATION_ERROR = '3';
	    public static final char CALCULATION_TYPE_NOTSUPPORTED = '4';
	    public static final char INACTIVE_DUE_TO_ERROR = '5';
	    public static final char CALCULATION_TYPE_RESULTS_ZERO = '6';
    }
	
	/**
	 * Sets the rounding difference.
	 * Corresponds to KONDN_FRM-NRRUND (R/3)
	 * @param roundingDifference the rounding difference
	 */
	public void setRoundingDifference(BigDecimal roundingDifference);

	/**
	 * Sets the reason why the calculated free quantity is zero.
	 * @param reason the reason
	 */
    public void setReasonForZeroQuantity(char reason);

	/**
	 * Returns the sales quantity. Depending on inclusive or exclusive
	 * it is in base unit of measurement (exclusive) or in sales unit of measurement (inclusive).
	 * Corresponds to KONDN_FRM-MGLME (R/3)
	 */
	public BigDecimal getSalesQuantityValue();
	
    /**
     * @return the free goods quantity. Depending on inclusive or exclusive
	 * it is in base unit of measurement (exclusive) or in sales unit of measurement (inclusive).
	 * Corresponds to KONDN_FRM-KNRNM (R/3)
     */
    public BigDecimal getFreeGoodsQuantityValue();

    /**
     * @return the additional free goods quantity. Depending on inclusive or exclusive
	 * it is in base unit of measurement (exclusive) or in sales unit of measurement (inclusive).
	 * Corresponds to KONDN_FRM-KNRZM (R/3)
     */
    public BigDecimal getAdditionalQuantityValue();
}
