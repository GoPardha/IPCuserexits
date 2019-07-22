package com.sap.spe.conversion;

import com.sap.spe.base.util.ClassFinder;

public abstract class CurrencyConversionTracerFactory {

	/**
     * The implementation class for this abstract factory class.
     */
    private static final String IMPLEMENTATION_CLASS = "com.sap.spe.conversion.impl.CurrencyConversionTracerFactoryImpl";

    /**
     * The singleton instance of the factory.
     */
    private static CurrencyConversionTracerFactory factory = null;

    /**
     * Gets an instance of the Currency conversion tracer factory.
     *
     * @return the Currency conversion tracer factory instance or <code>null</code> if
     *         the Currency conversion tracer factory instance cannot be created
     */
    public static CurrencyConversionTracerFactory getFactory() {

        // check whether a factory has already been created
        if (factory == null) {
            factory = (CurrencyConversionTracerFactory) ClassFinder.newInstance(IMPLEMENTATION_CLASS);
        }
        return factory;
    }

    public abstract ICurrencyConversionTracer getCurrencyConversionTracer();
}
	
