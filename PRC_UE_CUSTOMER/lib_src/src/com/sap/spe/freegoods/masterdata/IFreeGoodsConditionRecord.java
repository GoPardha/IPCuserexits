/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.freegoods.masterdata;

import java.io.Serializable;

import java.math.BigDecimal;

import com.sap.spe.condmgnt.masterdata.IConditionRecord;


/**
 * Condition record that contains the elements of freegoods agreement(business
 * perspective).
 */
public interface IFreeGoodsConditionRecord extends IConditionRecord, Serializable {
    
    public interface ExclusiveInclusiveIndicator {
        
        public static final char INCLUSIVE = '1';
        public static final char EXCLUSIVE = '2';
        public static final char INCLUSIVE_WITHOUT_ITEMGENERATION = '3';
    }

    /**
     * Return the minimum quantity value after which the free goods scale will
     * be applied.
     */
    public BigDecimal getMinimumFreeGoodsQuantityValue();

    /**
     * Return the minimum quantity unit.
     */
    public String getMinimumFreeGoodsQuantityUnitName();

    /**
     * Return the quantity value for which the additional free goods will be
     * given.
     */
    public BigDecimal getFreeGoodsQuantityValue();

    /**
     * Return the quantity value for which the additional free goods will be
     * given.
     */
    public String getFreeGoodsQuantityUnitName();

    /**
     * Return the quantity value of additional free goods that will be given.
     */
    public BigDecimal getAdditionalQuantityValue();

    /**
     * Return the quantity value of additional free goods that will be given.
     */
    public String getAdditionalQuantityUnitName();

    /**
     * Return the additional productID that will be given as free goods.
     */
    public String getAdditionalProductId();

    /**
     * Return the calculation type that will be used for the calculation of free
     * goods quantity. Predefined calculation types in R/3 for example are 1.
     * Pro rata 2. Unit reference 3. Whole units
     */
    public int getCalculationType();

    /**
     * Whether the free goods item quantity will affect the parent item quantity
     * or not.
     */
    public char getInclusiveExclusiveIndicator();
}
