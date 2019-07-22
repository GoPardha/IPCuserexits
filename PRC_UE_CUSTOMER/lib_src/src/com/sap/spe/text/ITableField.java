/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.text;

import java.io.Serializable;


public interface ITableField extends Serializable {

    /** @return the table name.*/
    public String getTableName();

    /** @return the field name.*/
    public String getFieldName();

    /** @return the dataelement the tablefield refers to.*/
    public IDataElement getDataElement();

    /** @return the domain the tablefield refers to.*/
    public String getDomainName();

    /** @return the possible values of the domain the tablefield refers to.*/
    public String[] getDomainValues();

    /** @return the domainvalue's descriptions the tablefield refers to.*/
    public String[] getDomainValueDescriptions();
}
