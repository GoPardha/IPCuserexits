/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.customizing;

import java.io.Serializable;


/**
 * Interface for an access-object. This object is part of an access sequence. The access defines the key fields
 * and the condition table for the search of condition records.
 */
public interface IAccess extends Serializable {

    /**
     * @return my access sequence
     */
    public IAccessSequence getAccessSequence();

    /** @return the counter of the access.
     * Corresponds to the database field <code>/SAPCND/T682I-KOLNR</code>.
     */
    public int getCounter();

    /**
     * @return the number of the requirement for the access.
     * Corresponds to the database field <code>/SAPCND/T682I-KOBED</code>.
     */
    public int getRequirementNumber();
    
    /**
     * returns field processing type 
     * @return Corresponds to the database field <code>/SAPCND/T682I-GZUGR</code>.
     */
    public char getProcessingTypeForField();
    
	public IUserExitFormula getRequirement();

    /**
     * @return true if the exclusive-flag is set. This flag controls if the search of condition records
     * is stopped when a condition record was found.
     * Corresponds to the database field <code>/SAPCND/T682I-KZEXL</code>.
     */
    public boolean isExclusiveAccess();

    /**
     * @return the condition table object of the access.
     */
    public IConditionTable getConditionTable();

    /**
     * @return an array of the attribute maps.
     */
    public IAttributeMap[] getAttributeMaps();

    public IAttributeMap getAttributeMap(String tableFieldName);
}
