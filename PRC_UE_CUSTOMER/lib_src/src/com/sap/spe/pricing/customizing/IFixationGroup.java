/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.customizing;


/**
 *  Interface of the FixationGroup-object.
 */
public interface IFixationGroup {
    public String getApplication();

    public String getName();

    public IFixationGroupEntry[] getEntries();

    public IFixationGroupEntry getEntry(String conditionTypeName);
}
