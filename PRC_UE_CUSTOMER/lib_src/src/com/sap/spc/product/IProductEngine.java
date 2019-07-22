/************************************************************************

	Copyright (c) 2000 by SAP AG

	All rights to both implementation and design are reserved.

	Use and copying of this software and preparation of derivative works
	based upon this software are not permitted.

	Distribution of this software is restricted. SAP does not make any
	warranty about the software, its performance or its conformity to any
	specification.

**************************************************************************/
package com.sap.spc.product;



import com.sap.spc.product.exception.ProductInconsistentDBException;
import com.sap.spe.conversion.exc.ConversionInconsistentDataException;
import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.sxe.db.Ranges;
import com.sap.sxe.sys.SAPTimestamp;

/**
 * Interface the product engine. The product engine provides all methods for accessing
 * product related data.
 */
public interface IProductEngine {

	public String getDaoModel();
	
	public String getDataModel();

	public String getPackageName ();

	/**Create a new instance. The parameters
	 * marked as optional can be set to null.
	 * @param productGUID			the internal ID of the product to be created
	 * @return the new instance or null if no data was found in the database
	 * @exception SPCIllegalArgumentException if conversionEngine or productGUID is null
	 * @exception ConversionInconsistentDBException if a unit of measure is not found in the database
	 */
	public ISPCProduct getSPCProduct(	   String productGUID) throws ProductInconsistentDBException;

	/**Create a new instance. The parameters
	 * marked as optional can be set to null.
	 * @param productGUID			the internal ID of the product to be created
	 * @param timestamp				optional, if specified the history data is accessed (H-tables)
	 * @return the new instance or null if no data was found in the database
	 * @exception SPCIllegalArgumentException if conversionEngine or productGUID is null
	 * @exception ConversionInconsistentDBException if a unit of measure is not found in the database
	 */
	public ISPCProduct getSPCProduct(	   String productGUID,
											   SAPTimestamp timestamp) throws ProductInconsistentDBException;
	
	/**Create a new instance. The parameters
	 * marked as optional can be set to null.
	 * @param productID				the external ID of the product to be created
	 * @param productLogSys			the logical system where the product was maintained (may be null)
	 * @param productType			the type of the product, eg. "01" (may be null)
	 * @return the new instance or null if no data was found in the database
	 * @exception SPCIllegalArgumentException if conversionEngine or productID is null
	 * @exception ConversionInconsistentDBException if a unit of measure is not found in the database
	 * @exception SPCProductIdentifierNotUnique if the external Product ID is not unique and LogSys/type have
	 * not been specified.
	 */
	public ISPCProduct getSPCProductByExternalID(	String productID,
														String productLogSys,
														String productType) throws ProductInconsistentDBException;

	/**Creates new instances. The parameters marked as optional can be set to null.
	 * @param productGUID			the internal ID of the product to be created (optional)
	 * @param productID				the external ID of the product to be created (optional); SQL wildcards (%, [], ^, _) can be used
	 * @param productLogSys			the logical system where the product was maintained (may be null)
	 * @param productType			the type of the product, eg. "01" (may be null)
	 * @return an array of new instances or an array of zero length if no data was found in the database
	 * @exception SPCIllegalArgumentException if conversionEngine or productID is null
	 * @exception ConversionInconsistentDBException if a unit of measure is not found in the database
	 */
	public ISPCProduct[] getSPCProducts(	   String productGUID,
											   String productID,
											   String productLogSys,
											   String productType) throws ProductInconsistentDBException;

	/**Creates new instances. The parameters marked as optional can be set to null.
	 * This method is intended to be used by the condition selection dialog.
	 * @param productID				the external ID of the product to be created; SQL wildcards (%, [], ^, _) can be used
	 * @param productLogSys			the logical system where the product was maintained (may be null)
	 * @param productType			the type of the product, eg. "01" (may be null)
	 * @return an array of new instances or an array of zero length if no data was found in the database
	 * @exception SPCIllegalArgumentException if conversionEngine or productID is null
	 * @exception ConversionInconsistentDBException if a unit of measure is not found in the database
	 */
	public ISPCProduct[] getSPCProducts(	   String productID,
											   String logicalSystem,
											   String productType) throws ProductInconsistentDBException;
	
    /**Creates a documnent sepcific product
	 * marked as optional can be set null
	 * @param  productid the internal ID of the product to be created
	 * @return the new instance or null if no data was found in the database 
	 * @exception ProductInconsistentDBException  
	 * @exception ConversionInconsistentDBException if a unit of measure is not found in the database
	 * @exception ConversionMissingDataException
	 */
	public ISPCDocumentProduct getSPCDocumentProduct(String productid) throws ProductInconsistentDBException, ConversionInconsistentDataException, ConversionMissingDataException;


	/**Creates new instances. The parameters marked as optional can be set to null.
	 * This method is intended to be used by the condition selection dialog.
	 * @param productIDRanges		Range of Product Ids to be selected
	 * @return an array of new instances or an array of zero length if no data was found in the database
	 * @exception SPCIllegalArgumentException if conversionEngine or productID is null
	 * @exception ConversionInconsistentDBException if a unit of measure is not found in the database
	 * @deprecated  use : getAllProductGUIDs
	 */
	public ISPCProduct[] getSPCProducts(Ranges productIDRanges) throws ProductInconsistentDBException;

	public void removeSPCProduct(String productGUID) throws ProductInconsistentDBException;
	public void removeSPCProduct(String[] productGUIDs);

	public ILogicalSystem getLogicalSystem(String client)  throws ProductInconsistentDBException;
	
		// Product Category Assignment
		public String[] getAllProductCategoryAssignmentsReturningGUID (String productGUID) throws ProductInconsistentDBException;
		public IProductCategoryAssignment getProductCategoryAssignment(String productGUID, String hierarchyGUID)  throws ProductInconsistentDBException;
		public void removeProductCategoryAssignment(String productGUID);
	   
		// Product Base Data
		public IProductBaseData getProductBaseData(String productGUID) throws ProductInconsistentDBException;
		public IProductBaseData[] getProductBaseDataArray(String productID, Ranges productIDRanges,  String logicalSystem, String productType, boolean NoLike) throws ProductInconsistentDBException;		
		public IProductBaseData[] getProductBaseDataByExternalID(String productID, String logicalSystem, String productType, boolean noLike) throws ProductInconsistentDBException;
		public String getRelationshipTypeFromDatabase(String relationshipTypeID) throws ProductInconsistentDBException;
		public void removeProductBaseData(String productGUID);
		
		// Product Configuration 
		public IProductSet getProductBaseSet(String productGUID) throws ProductInconsistentDBException;
		public void removeProductConfigurationData(String productGUID);
		
		// Produc Config Type
		public IConfigType getConfigType (char configFlag) throws ProductInconsistentDBException;

		// Product Description
		public String getDescription(String productGUID) throws ProductInconsistentDBException;
		public void removeProductDescription(String productGUID);

		// Product Numbering Scheme
		public IProductNumberingScheme getProductNumberingScheme(String schemeGUID) throws ProductInconsistentDBException;
		
		// Product Set Type Reference
		public IProductSetTypeReference getProductSetTypeReference () throws ProductInconsistentDBException;

		// Product Category Hierarchy
		public IProductCategoryHierarchy getProductCategoryHierarchy(String hierarchyGUID) throws ProductInconsistentDBException;
		
		// Product Type Data
		public IProductMaterialData getProductTypeData (String productGUID) throws ProductInconsistentDBException;
		public void removeProductTypeData(String productGUID);

		// Product Set Type
		public IProductSetType getProductSetType(String frgTypeID) throws ProductInconsistentDBException;

		// Product Units
		public IProductUnits getProductUnits(String productGUID, SAPTimestamp timestamp) throws ProductInconsistentDBException;
		public void removeProductUnits(String productGUID);
}
