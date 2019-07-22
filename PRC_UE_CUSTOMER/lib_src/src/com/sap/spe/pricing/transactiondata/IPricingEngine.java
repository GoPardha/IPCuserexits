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

import com.sap.spe.condmgnt.finding.IConditionFindingEngine;
import com.sap.spe.conversion.ICurrencyUnit;
import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.conversion.exc.CurrencyConversionException;
import com.sap.sxe.sys.SAPDate;


public interface IPricingEngine extends IConditionFindingEngine {

    /**
     * Creates and returns an IPricingDocument.
     */
    public IPricingDocument createPricingDocument(IDocumentBase doc);

    public IVariantCondition createVariantCondition(String name, double factor, String description);

    public IExternalPricingConditions createContainerForExternalConditions();

    public IExternalPricingCondition createExternalCondition();

    public IExternalDataSource getExternalDataSource(BigDecimal rateValue, String rateUnit,
        BigDecimal pricingUnitValue, String pricingUnitUnit, char calculationType, BigDecimal conditionBaseValue);

    /**
     * Convert via "triangulation" from condition currency to local currency,
     * then to foreign currency.
     */
    public IPricingCurrencyConversionResult convertTo(boolean directConversion, ICurrencyValue fromCurrencyValue,
        SAPDate fromConversionDate, BigDecimal fromExchangeRate, ICurrencyUnit localCurrencyUnit,
        SAPDate toConversionDate, BigDecimal toExchangeRate, ICurrencyUnit toCurrencyUnit, String exchangeRateTypeName)
        throws CurrencyConversionException;
}
