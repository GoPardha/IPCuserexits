/*
 * Created on 7 oct. 2004
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package com.sap.spc.product;

/**
 * @author i018282
 */

import java.io.Serializable;

public interface  IConfigType extends Serializable{
    public char getConfigFlag();

	public boolean isUseIPC();

	public boolean isOnlyVariantMatching();
}
