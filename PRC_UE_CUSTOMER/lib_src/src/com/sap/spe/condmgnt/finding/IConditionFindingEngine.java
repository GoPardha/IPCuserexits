/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.finding;

import com.sap.spe.base.cache.Engine;
import com.sap.spe.condmgnt.customizing.IApplicationField;
import com.sap.spe.condmgnt.customizing.IProcedure;
import com.sap.spe.condmgnt.exception.ConditionInconsistentDBException;
import com.sap.sxe.sys.SAPTimestamp;


/**
 * Interface of a condition finding engine. A condition finding engine is the entry
 * point for the condition finding process. The finding process itself is handled
 * by the condition finding manager.
 */
public interface IConditionFindingEngine extends Engine {

    /**
     * Gets the usage, e.g. pricing, of this condition finding engine
     */
    public String getUsage();

    /**
     * Gets a procedure for condition finding process
     * @param application for the condition finding process
     * @param name of procedure
     * @return pricing procedur object
     * @throws ConditionInconsistentDBException
     */
    public IProcedure getProcedure(String application, String name)
        throws ConditionInconsistentDBException;

    /**
     * Gets a condition finding manager for the specified procdure
     * @param procedure that should be used for condition finding
     * @return condition finding manager
     */
    public IConditionFindingManager getConditionFindingManager(IProcedure procedure);

    /**
     * Get the relevant attributes, incl. timestamps, for the specified procedure
     * @param procedure
     * @return relevant attributes
     * @throws ConditionInconsistentDBException
     */
    public IRelevantAttributes getRelevantAttributes(String application, String procedureName)
        throws ConditionInconsistentDBException;

    /**
     * Create an attribute binding
     * @param application
     * @param attributeName
     * @param attributeValues
     * @return attribute binding
     */
    public IAttributeBinding createAttributeBinding(String application, String attributeName, String[] attributeValues);

    /**
     * Create an attribute binding.
     * The method has been introduced to reuse application field objects and thus minimize the number of VMC cache read accesses.
     * @param applicationField
     * @param attributeValues
     * @return attribute binding
     */
    public IAttributeBinding createAttributeBinding(IApplicationField applicationField, String[] attributeValues);

    /**
     * Create an access timestamp
     * @param timestampName
     * @param timestampValue
     * @return condition access timestamp
     */
    public IConditionAccessTimestamp createConditionAccessTimestamp(String timestampName, String timestampValue);

    /**
     * Create an access timestamp
     * @param timestampName
     * @param timestampValue
     * @return condition access timestamp
     */
    public IConditionAccessTimestamp createConditionAccessTimestamp(String timestampName, SAPTimestamp timestampValue);
      
}
