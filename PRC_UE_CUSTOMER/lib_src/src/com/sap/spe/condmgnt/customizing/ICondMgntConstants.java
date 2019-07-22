/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

/*
 * Created on 5 oct. 2004
 *
 * To change this generated comment go to
 * Window>Preferences>Java>Code Generation>Code Template
 */
package com.sap.spe.condmgnt.customizing;

import java.io.Serializable;


/**
 * @author i018282
 */
public interface ICondMgntConstants extends Serializable {
    public String getGlobalPrefix();

    public String getGlobalPrefixAccessView();

    public String getGlobalPrefixTable();

    public String getGlobalPrefixSupplement();

    public String getGlobalPrefixScale();

    public String getGlobalSuffixScaleDimension();

    public String getGlobalSuffixScaleDefinition();

    public String getGlobalSuffixScaleLevel();

    public String getGlobalSuffixScaleValue();

    public String getGlobalSuffixScaleAdditionalData();
}
