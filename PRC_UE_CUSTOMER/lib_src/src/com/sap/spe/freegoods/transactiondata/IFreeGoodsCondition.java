/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.freegoods.transactiondata;

import java.io.Serializable;

import com.sap.spe.condmgnt.finding.ICondition;
import com.sap.spe.conversion.IPhysicalValue;
import com.sap.spe.conversion.IQuantityValue;
import com.sap.spe.freegoods.masterdata.IFreeGoodsConditionRecord;
import com.sap.spe.freegoods.transactiondata.userexit.IFreeGoodsConditionUserExit;


/**
 * A free goods condition contains information from the free goods condition
 * record, and from the result of the calculations.
 */
public interface IFreeGoodsCondition extends ICondition, Serializable {
    
    public interface ReasonForZeroQuantity extends IFreeGoodsConditionUserExit.ReasonForZeroQuantity {
//	    public static final char OKAY = ' ';
//	    public static final char MIN_QUANTITY_NOTMET = '1';
//	    public static final char QUANTITY_NOT_INWHOLEUNITS = '2';
//	    public static final char CALCULATION_ERROR = '3';
//	    public static final char CALCULATION_TYPE_NOTSUPPORTED = '4';
//	    public static final char INACTIVE_DUE_TO_ERROR = '5';    	
//	    public static final char CALCULATION_TYPE_RESULTS_ZERO = '6';
    }

    /**
     * @return the quantity of the product to be given free.
     */
    public IPhysicalValue getCalculatedFreeQuantity();

    /**
     * @return the minimum quantity required for the item to get the free good.
     */
    public IQuantityValue getMinimumFreeGoodsQuantity();

    /**
     * @return the free goods quantity.
     */
    public IQuantityValue getFreeGoodsQuantity();

    /**
     * @return the additional free goods quantity.
     */
    public IPhysicalValue getAdditionalQuantity();

    /**
     * @return the product to be given with the item as free good.
     */
    public String getAdditionalProduct();

    /**
     * @return the exclusiveInclusive indicator for freegoods.
     */
    public char getExclusiveInclusiveIndicator();

    /**
     * @return the calculation rule number used to calculate the condition
     *         value.
     */
    public int getCalculationType();

    /**
     * @returns the error message text. This explains the reason for the
     *          condition to be inactive or some other error in determination.
     */
    public String getErrorMessage();

    /**
     * @returns the reason for the freegoods quantity to be zero.
     */
    public char getReasonForZeroQuantity();

    /**
     * @return the item to which the condition belongs.
     */
    public IFreeGoodsItem getFreeGoodsItem();

    /**
     * @return the freegoods condition record.
     */
    public IFreeGoodsConditionRecord getFreeGoodsConditionRecord();
}
