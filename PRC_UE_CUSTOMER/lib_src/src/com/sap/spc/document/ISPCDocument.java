/************************************************************************

	Copyright (c) 1999 by SAP AG

	All rights to both implementation and design are reserved.

	Use and copying of this software and preparation of derivative works
	based upon this software are not permitted.

	Distribution of this software is restricted. SAP does not make any
	warranty about the software, its performance or its conformity to any
	specification.

**************************************************************************/
package com.sap.spc.document;

import java.util.Map;

import com.sap.spc.product.ISPCProduct;
import com.sap.spe.document.IDocument;

public interface ISPCDocument extends IDocument {
    public final static String EMPTY_STRING = "";

	/**Gets all <code>ISPCItem</code> objects of this document which are root items.
	 * A root item is an item which hasn't a parent item (high level item).
	 * @return an array of <code>ISPCItem</code> objects	 */
	//public ISPCItem[] getSPCRootItems();

	/** convenience methods, returning associated structures directly as SPC-types */
	public ISPCItem getSPCItem(String itemId);
	public ISPCItem[] getSPCItems();
	//public ISPCItem getSPCItemExternalId(String externalId);
	public void removeSPCItem(String itemId);

	/** Create new SPCItem, supplying default values for most fields:  */
	public ISPCItem addSPCItem(ISPCProduct product);
	
	/**
	 * The processType is simply an information handed in during lean document creation.
	 * This information is passed into the item overwrite call back during BOM explosion 
	 * for to allow the application to determine item details based on this. 
     * @param   processType  is a <code>Stirng</code> that simply remembers the processType
     * of the document. No follow up processing is done. 
     * <code>null</code> is converted to an empty <code>String</code>.
	 */
	public void setProcessTyp(String processType);
	/**
	 * The processType is simply an information handed in during lean document creation.
	 * This information is passed into the item overwrite call back during BOM explosion 
	 * for to allow the application to determine item details based on this. 
    * @return  the process type as set before.
	 */
	public String getProcessTyp();
	
    /**
     * The salesOrg is simply an information handed in during lean document creation.
     * This information is passed into the item overwrite call back during BOM explosion 
     * for to allow the application to determine item details based on this. 
     * @param   salesOrg  is a <code>Stirng</code> that simply remembers the salesOrg
     * of the document. No follow up processing is done. 
     */
    public void setSalesOrg(String salesOrg);
    /**
     * The salesOrg is simply an information handed in during lean document creation.
     * This information is passed into the item overwrite call back during BOM explosion 
     * for to allow the application to determine item details based on this. 
     * @return  the salesOrg as set before.
     * <code>null</code> is converted to an empty <code>String</code>.
     */
    public String getSalesOrg();
    /**
     * The distributionChannel is simply an information handed in during lean document creation.
     * This information is passed into the item overwrite call back during BOM explosion 
     * for to allow the application to determine item details based on this. 
     * @param   distributionChannel  is a <code>Stirng</code> that simply remembers the distributionChannel
     * of the document. No follow up processing is done. 
     */
    public void setDistributionChannel(String distributionChannel);
    /**
     * The distributionChannel is simply an information handed in during lean document creation.
     * This information is passed into the item overwrite call back during BOM explosion 
     * for to allow the application to determine item details based on this. 
     * @return  the salesOrg as set before.
     * <code>null</code> is converted to an empty <code>String</code>.
     */
    public String getDistributionChannel();
    
    /**
     * @return the subtotal value of the whole document.
     */
    public Map getSubtotals();
}
