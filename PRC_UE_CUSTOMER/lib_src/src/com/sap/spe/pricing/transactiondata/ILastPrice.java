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

import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.conversion.IDimensionalValue;
import com.sap.spe.conversion.IExchangeRate;
import com.sap.spe.conversion.IPhysicalValue;
import com.sap.sxe.sys.SAPTimestamp;
import com.sap.sxe.util.math.Fraction;


/**
 * Interface for last price object. The last price is determined for each
 * pricing item and available in some SPE userexits. It corresponds to the
 * pricing condition which holds the last price on the pricing item.
 */
public interface ILastPrice {

    /**
     * @return the condition type name of the last price.
     */
    public String getConditionTypeName();

    /**
     * @return the identifier of the condition record which was used for
     * the construction of the last price.
     */
    public String getConditionRecordId();

    /**
     * @return the step number of the last price.
     */
    public int getStepNumber();

    /**
     * @return the counter of the last price. If more than one pricing condition
     * appears for the same step number the counter identifies the pricing condition.
     */
    public int getCounter();

    /**
     * @return the exchange rate of the last price.
     */
    public IExchangeRate getExchangeRate();

    /**
     * @return the conversion fraction of the last price.
     */
    public Fraction getFraction();

    /**
     * @return the calculation type of the last price.
     */
    public char getCalculationType();

    /**
     * @return the quantity of the last price.
     */
    public IDimensionalValue getQuantity();

    /**
     * @return the base value of the last price.
     */
    public IDimensionalValue getBase();

    /**
     * @return the pricing unit of the last price.
     */
    public IPhysicalValue getPricingUnit();

    /**
     * @return the condition rate of the last price.
     */
    public ICurrencyValue getRate();

    /**
     * @return the condition value of the last price.
     */
    public ICurrencyValue getValue();

    /**
     * @return the factor of the last price.
     */
    public BigDecimal getFactor();

    /**
     * @return the condition control of the last price.
     */
    public char getConditionControl();

    /**
     * @return the variant condition factor of the last price.
     */
    public BigDecimal getVariantConditionFactor();

    public SAPTimestamp getTimestamp();    
}
