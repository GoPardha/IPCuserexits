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
import java.util.Vector;;

public interface IProcedure extends Serializable {

    /**
     * @return the application.
     * Corresponds to the database field <code>/SAPCND/CTLIST-KAPPL</code>.
     */
    public String getApplication();

    /**
     * @return the usage.
     * Corresponds to the database field <code>/SAPCND/CTLIST-KVEWE</code>.
     */
    public String getUsage();

    /**
     * @return the usage.
     * Corresponds to the database field <code>/SAPCND/CTLIST-CTLIST</code>.
     */
    public String getName();

    public IStep[] getSteps();

    public String getDescription();

    public IStep determineStepOfConditionType(String conditionTypeName);

    /**
     * @return error message
     */
    public Vector getErrorMessage();
}
