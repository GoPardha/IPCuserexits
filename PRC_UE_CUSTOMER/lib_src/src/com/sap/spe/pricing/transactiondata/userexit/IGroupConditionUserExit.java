/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata.userexit;

import com.sap.spe.pricing.transactiondata.IPricingConditionCommon;


/**
 * A group condition is a special pricing condition (e.g. the scale base is accumulated
 * for more than one line item). A group condition contains information copied from the pricing knowledge base
 * (from the customizing of the condition type and the pricing procedure), from the
 * condition record, and from the result of the calculation. The methods of this interface
 * can be used in the SPE userexits.
 */
public interface IGroupConditionUserExit extends IPricingConditionCommon {
    public void setConditionTypeName(String name);

    public void setCalculationType(char calcType);

    public void setCategory(char category);

    public void setOrigin(char origin);

    public void setConditionControl(char control);

    public void setConditionClass(char condClass);

    public void setId(String id);
  
    /**
     * when this is set all the items belonging to this group will be recalculated
     * during group process mode item calculation.
     * valid only in optimized (fast) group process mode
     * @param sourceIndicator source indicator true or false
     */
    public void setAsSourcecForGrpModeItmCalculation(boolean sourceIndicator);
  
}
