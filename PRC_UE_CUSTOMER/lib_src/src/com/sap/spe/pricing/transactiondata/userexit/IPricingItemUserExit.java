/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit;

import java.math.BigDecimal;

import com.sap.spe.base.sys.TimePeriod;
import com.sap.spe.condmgnt.finding.userexit.IConditionFindingManagerUserExit;
import com.sap.spe.pricing.transactiondata.IPricingItemCommon;


/**
 * The pricing item contains accumulated pricing information of all pricing conditions
 * and provides methods which are working on all pricing condition of this pricing item.
 * The methods of this interface can be used in the SPE userexits.
 */
public interface IPricingItemUserExit extends IConditionFindingManagerUserExit, IPricingItemCommon {

    /**
     * @return the IPricingDocument of the pricingItem.
     */
    public IPricingDocumentUserExit getUserExitDocument();

    /**
     * @return all subPricingItems which are already priced.
     */
    public IPricingItemUserExit[] getSubPricingItemsUserExitRecursive();

    /**
     *  @return my highlevel item or null
     */
    public IPricingItemUserExit getHighLevelItemUserExit();

    public IPricingConditionUserExit[] getConditionsForCumulation();

    public void setExclusionFlag(char exFlag);

    /**
     * @param subtotalFlag the subtotal flag
     * @param value the subtotal value
     */
    public void setSubtotal(char subtotalFlag, BigDecimal value);

    public IPricingConditionUserExit[] getUserExitConditions();

    public ILastPriceUserExit getUserExitLastPrice();
    
    public TimePeriod getConditionBaseTimePeriod();    
}
