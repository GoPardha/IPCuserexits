/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.customizing;

import java.util.Map;

import com.sap.spe.condmgnt.customizing.IProcedure;


public interface IPricingProcedure extends IProcedure {

    /** @return a vector of IConditionType-Objects which are listed by the procedure in the same
     * sequence as in the procedure. This method could be used to realise a F4-help. */
    public IPricingConditionType[] getAvailableConditionTypes();

    /** @return a vector of IConditionTypes for which manual entries on the
     * pricing item level are allowed.
     */
    public IPricingConditionType[] getAvailableItemConditionTypes();

    /** @return a vector of IConditionTypes for which manual entries on the
     * pricing document level are allowed.
     */
    public IPricingConditionType[] getAvailableHeaderConditionTypes();

    /** @return an array of IConditionTypes for which a dataSource not equal conditionTechnique
     * is maintained.
     */
    public IPricingConditionType[] getConditionTypesWithDataSource();

    /** @return an array of IConditionTypes for which a purpose
     * is maintained.
     */
    public IPricingConditionType[] getConditionTypesWithPurpose();

    /** @return an ordered set of the exclusion procedures which are assign to the
     * pricing procedure. */
    public IExclusionProcedure getExclusionProcedure();

    /** @return true if Pricing Procedure contains a print ID implying printing in total. */
    public boolean containsTotalsPrintId();

    /** @return a hashtable of the conditiontypenames and steps which are mandatory. */

    // TODO remove this method
    public Map determineMandatoryConditions();
}
