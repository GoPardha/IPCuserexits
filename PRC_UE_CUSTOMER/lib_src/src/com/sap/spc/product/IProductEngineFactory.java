/*
 * Created on 11 oct. 2004
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package com.sap.spc.product;

/**
 * @author i018282
 */
	import com.sap.spe.base.util.ClassFinder;
	import com.sap.vmc.logging.Location;
	import java.io.Serializable;


	public abstract class IProductEngineFactory implements Serializable{
			    
	    /**
		 * The location in which tracing information is stored.
		 */
		private static final Location location = Location.getLocation(IProductEngineFactory.class);

		/**
		 * The implementation class for this abstract factory class.
		 */
		// should be moved to vmc configuration management
		private static final String IMPLEMENTATION_CLASS = "com.sap.spc.product.impl.ProductEngineFactory";

		/**
		 * The singleton instance of the factory.
		 */
		private static IProductEngineFactory factory = null;

		/**
		 * Gets an instance of the condition customizing engine factory.
		 * 
		 * @return the factory instance or <code>null</code> if 
		 *         the factory instance cannot be created
		 */
		public static synchronized IProductEngineFactory getFactory() {
			// check whether a factory has already been created
			if (factory == null) {
			    factory = (IProductEngineFactory) ClassFinder.newInstance(IMPLEMENTATION_CLASS);
			}
			return factory;
		}

		public abstract IProductEngine getProductEngine(String productParam);	
	}
