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
 * Interface of a condition type object.
 */
public interface IConditionType extends Serializable {

    /**
     * @return the application.
     * Corresponds to the database field <code>/SAPCND/T685-KAPPL</code>.
     */
    public String getApplication();

    /**
     * @return the usage.
     * Corresponds to the database field <code>/SAPCND/T685-KVEWE</code>.
     */
    public String getUsage();

    /**
     * @return the name of the condition type.
     * Corresponds to the database field <code>/SAPCND/T685-KSCHL</code>.
     */
    public String getName();

    /**
     * @return the access sequence object.
     */
    public IAccessSequence getAccessSequence();

    /**
     * @return my description.
     * Corresponds to the database field <code>/SAPCND/T685T-VTEXT</code>.
     */
    public String getDescription();

    /**
     * @return the name of the referenceConditionType.
     * Corresponds to the database field <code>/SAPCND/T685-RKSCHL</code>.
     */
    public IConditionType getReferenceConditionType();

    /**
     * @return name of field for condition access timestamp
     */
    public String getConditionAccessTimestampName();
}
