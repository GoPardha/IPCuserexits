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


import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.sap.sce.front.base.Config;
import com.sap.sce.front.base.Instance;
import com.sap.sce.front.base.SCEItem;
import com.sap.sce.kbrt.ext_configuration;
import com.sap.spc.product.ISPCProduct;
import com.sap.spe.conversion.ICurrencyValue;
import com.sap.spe.conversion.exc.ConversionInconsistentDataException;
import com.sap.spe.conversion.exc.ConversionMissingDataException;
import com.sap.spe.conversion.exc.CurrencyConversionException;
import com.sap.spe.document.IItem;
import com.sap.sxe.sys.SAPDate;

/**Defines the interface to a
 * {@link SPCItem}.
 * It represents an extension of
 * {@link com.sap.spe.document.IItem}, the SPE specific view to an item.
 * It implements the communication needs of SCE, defined by <code>SCEItem</code>.
 *
 * From SPE, we have definitions of what Documents, Items, and Products must supply
 *    (i.e. the interfaces IDocument, IItem, and IPricingProduct).  Thus, our SPC
 *    objects corresponding to these items should simply implement these interfaces, and
 *    add any needed extra (SPC-Specific) calls.
 */
public interface ISPCItem extends SCEItem, IItem {

	public ISPCProduct getSPCProduct();
	public ISPCDocument getSPCDocument();
    public String getDatamodelName();

	public ISPCItem[] getSPCSubItems();
	public ISPCItem[] getSPCSubItemsRecursive();

	public ISPCItem getSPCHighLevelItem();

	public void setSPCHighLevelItem(ISPCItem highLevelItem);
	
	public void removeSPCSubItem(String itemId);

	public ISPCItem addSPCSubItem(ISPCProduct product);
		// TODO: throws  ConversionInconsistentDBException;
	
	public interface IBOMExplosionSubscriber{
		/**
		 * will be called after BOM explosion
		 * @param spcDocument SPC document instance
		 * @param subItems subitems created during explosion
		 */
		public void handlePublishedExplosion(ISPCDocument spcDocument, List subSpcItems);
	}
	
	
	public interface IToggleConfProductAndVariantSubscriber{
		/**
		 * this will be called aftet togglling between config product and variant
		 * @param spcDocument SPC document instance
		 * @param spcItem spcitem for which toggling had happend
		 */
		public void handleConfProdAndVariantToggling(ISPCDocument spcDocument, ISPCItem spcItem);
	}

	//addional methods if ISPCItem
	//--------------------------------------------------------
	/**Generates sub items according to the item's <code>Config</code>. If the item's
	 * product was changed due to the configuration the item is initialized again.
	 */
	public void updateSubItemsFromConfig()	throws	ConversionMissingDataException,
													ConversionInconsistentDataException;


	/**Creates subitems according to the item's current <code>Config</code>
	 * and performs pricing for this items and all its subitems.
	 */
	 public void pricingConfiguration() throws	ConversionMissingDataException,
												CurrencyConversionException;

	/**
	 * Changes the configuration this item holds. If the configuration doesn't fit to the
	 * product of this item or if it is null, the method will perform no operation and return
	 * false, otherwise it will return true.<p>
	 *
	 * Changing a configuration will automatically adjust the subitems of this item according
	 * to the new configuration. Essentially, all old subitems are deleted and replaced by
	 * new ones that reflect the new configuration.
	 */
	public boolean setConfig(Config c);
	
	/**
	 * Changes the configuration this item holds. If the configuration doesn't fit to the
	 * product of this item or if it is null, the method will perform no operation and return
	 * false, otherwise it will return true.<p>
	 *
	 * Changing a configuration will automatically adjust the subitems of this item according
	 * to the new configuration. Essentially, all old subitems are deleted and replaced by
	 * new ones that reflect the new configuration.
	 */
	public boolean addConfig(ext_configuration extConfig,
							String kbName,
							String kbVersion,
							String kbProfile,
							SAPDate kbDate,
							String kbId,
							Hashtable context);


	/**Copies a configurable <code>ISPCItem</code> or a high level (parent) item with all of its
	 * lower level items and its configuration and adds the copy to <code>targetDocument</code>.
	 *
	 * @param	targetDocument the document to which the item copied is added
	 * @param	revertConfig	revert sourceItem's <code>Config</code> to the empty <code>Config</code>
	 * after copying. (Currently, this parameter is ignored and assumed to be true.)
	 * @return	a copy of sourceItem
	 */
	public ISPCItem copyItem(ISPCDocument targetDocument, boolean revertConfig);
		// TODO throws	ConversionInconsistentDBException,CurrencyConversionException;

	/** Sets the sce-instance */
	public void setInstance(Instance instance);

	/**
	 * Returns the configuration context of this item.
	 */
	public Map getContext();

	/** Sets the product of an item to a named product.
	 */
	public void setSPCProduct(ISPCProduct spcProduct); // TODO throws ConversionInconsistentDBException;

	/**
	 * Sets if this item supports sub items. Also update the internal item structure
	 * accordingly (delete all subitems or reconstruct subitems from configuration).
	 */
	public void setSubItemsAllowed(boolean isSubItemsAllowed);

	/**
	 * Returns true if this item may have subitems.
	 */
	public boolean getSubItemsAllowed();

	/**
	 * Returns true if this item is used as a part of a configuration. This flag only affects
	 * subitems of configurable items. Subitems are removed by the configurator only if they are
	 * not fitting with the configuration state and if they are configuration relevant.
	 */
	public boolean isConfigurationPart();
	
	/**
	 * returns true if an item is a real product variant. A real product variant is a product variant that is no individual object.
	 */
	public boolean isRealProductVariant();

	/**
	 * Changes this item's being part of a configuration. Changing this flag will affect if this item
	 * survives configuration -&lt; item structure updates.
	 */
	public void setConfigurationPart(boolean isPart);
	
	
	/** 
	 * Set the reference (initial) configuration for this item.
	 */
	public void setInitialConfiguration(ext_configuration cfg); 
	
	/** 
	 * Return the reference (initial) configuration for this item (CRM 5.1).	
	 * Is used e.g. for comparison with the current configuration state.
	 */
	public ext_configuration getInitialConfiguration();
	

	/**
	 * Returns true if it is not allowed to delete this item. This flag only affects
	 * subitems of configurable items. Subitems are removed by the configurator only if they are
	 * not marked as not-deletable.
	 */
	public boolean isNotDeletable();

	/**
	 * Set whether an item is not-deletable.
	 */
	public void setNotDeletable(boolean notDeletable);

	/**for gridproducts a special handling was invented:
	 * because gridproducts are from the configuration singlelevel
	 * we do not need updateSubItemsFromConfig at all. Moreover the 
	 * gridproducts are structured as follows:
	 *    root (configurable)
	 *     |
	 *     |----------------------.............
	 *     |                      |
	 *    prodVar1             prodVar2 .......
	 * Allthough the root is configurable the subitems must not be 
	 * controlled by configuration!
	 * 
	 * Default for this setting is false.
	 */
	public void setGridProdukt(boolean gridProduct);

    public ICurrencyValue getTotalNetValue();
    public ICurrencyValue getTotalTaxValue();
    public ICurrencyValue getTotalGrossValue();
    public ICurrencyValue getTotalNetValueWithoutFreight();
    /**
     * @return the total subtotals accumulated over all items
     * 
     */
    public Map getTotalSubTotals();
    public void setSuppressBomExplosion(boolean b);
    
    /**
     * The itemType of the Item as derived from the leading Sales Document.
     * Within pricing the itemType is not processed but passed through to the callback
     * for overwriting item attributes within the lean item processing.
     * 
     * @param itemType
     */
    public void setItemType(String itemType);
    
    /**
     * The itemType of the Item as derived from the leading Sales Document.
     * Within pricing the itemType is not processed but passed through to the callback
     * for overwriting item attributes within the lean item processing.
     * 
     * @return
     */
    public String getItemType();
    
    /**
     * register listner for BOM explosion
     * @param subscriber 
     */
    public void registerBOMExplosionSubcriber(IBOMExplosionSubscriber subscriber);
    
    
    /**
     * register listner for toggling between config product and variant
     * @param subscriber
     */
    public void registerConfProdAndVariantToggleSubcriber(IToggleConfProductAndVariantSubscriber subscriber);
    
    /**
     * @return Map, which contains all key-value pairs of the dynamic return container. This method
     * returns only a copy of the dynamic return container and should not be used to change the contents
     * of this container.
     */
    // 20130206-tl: added the dynamic returns to ISPCItem
    public Map getDynamicReturnValues();
    public Map getSubtotals();
    
    public ICurrencyValue getNetValueWithoutFreight();
}
