/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.pricing.transactiondata;

import com.sap.spe.conversion.ICurrencyUnit;
import com.sap.spe.pricing.customizing.IPricingProcedure;
import com.sap.sxe.sys.SAPDate;


/**
 * This interface should be implemented by the (sales order) document/header and
 * is used for callbacks of the pricing engine. It provides information of the
 * (sales order) document which is needed for pricing.
 */
public interface IDocumentBase {

    /** @return an Id which is used to save the pricing document to the local database. The id should have
     * less or equal 10 digits.
     * (R/3-field: KONV-KNUMV)   */
    public String getId();

    /** @return the name of the local(house) currency of the (sales) document. (R/3-field: KOMK-HWAER) */
    public ICurrencyUnit getLocalCurrency();

    /** @return the name of the document currency of the (sales) document. All condition values are calculated
       in this currency.(R/3-field: KOMK-WAERK) */
    public ICurrencyUnit getDocumentCurrency();

    /** @return the name of the pricing procedure which is then used by the pricing engine to search for
     * condition records. (R/3-field: KOMK-KALSM) */
    public IPricingProcedure getPricingProcedure();

    /** @return the usage of the external document. */
    public String getUsage();

    /** @return the application of the external document. For a sales order the application is "V".
     * (R/3-field: KOMK-KAPPL) */
    public String getApplication();

    /** @return the edit mode of the (sales) document. The edit mode contains
     *  information e.g. about whether the document is editable or not.*/
    public char getEditMode();

    /** @return the partial processing flag of the (sales) document. The partial processing flag contains
     *  information about if all documents lineitems are processed or not.*/
    public boolean isPartialProcessing();

    /** @return the authority level the user has to display this document's conditions. */
    public int getAuthorityForDisplay();

    /** @return the authority level the user has to edit this document's conditions. */
    public int getAuthorityForEdit();

    /** @return the flag whether only condition records of the given usage should be accessed. */
    public boolean isOnlySpecifiedUsage();

    /** @return the flag if prices with the rate 0 are set to inactive. */
    public boolean isKeepingZeroPricesActive();

    /** @return the flag if a trace is performed or not. */
    public boolean isPerformingTrace();

    /** @return the unit to be rounded, e.g. 5 for the so-called 5-Rappen-rounding in swisse.
     *  Should be 0 if no rounding should be performed. (R/3-field: T001R-REINH)
     */
    public int getUnitToBeRoundedTo();

    /** @return a vector of Iitems of the external (sales) document. This is needed if a pricing document
     * should be loaded from the local database. */
    public IItemBase[] getItemBases();

    /** @return the name of the related business object type.
     *  All available business object types are documented in the SAP Business Object Repository (BOR) */
    public String getBusinessObjectType();

    /** @return date that will be used to check if a currency is expired
     */
    public SAPDate getExpiringCheckDate();

    /**
     * @return fielname of the variant condition field (usually "VARCOND")
     */
    public String getVarcondFieldname();
    
    /**
     * @return true in case a price simulation should be carried out 
     */
	public boolean isSimulation();  
	
	/**
	 * @return true in case items are loaded lazily while opening the document
	 */
	public boolean isLazyItemLoadMode();
	
	/**
	 * to check whether return document indicator is set at the document(header) level or not
	 * @return true if return falg is set at header level otherwise returns false
	 */
	public boolean isReturnDocument();
	
	/**
	 * get settings for optimized group process handling
	 * @return option settings for optimized group process mode
	 */
	public boolean isOptimizedGroupProcessMode();
	
	/**
	 * to check whether this document is a 'lean document' or not. Lean document is used for 
	 * interactive configuration in decoupled pricing and configuration scenario.
	 * @return TRUE if it is a lean document otherwise FALSE
	 */
	public boolean isLeanDcoument();
	
	public boolean isPricingCompleteRequiredDuringLoad();
}
