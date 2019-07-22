/*
 * Created on 1 févr. 2005
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */

/**
 * @author i018282
 */
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

import com.sap.spe.conversion.IAlternativeUnitFraction;
import com.sap.spc.product.exception.ProductInconsistentDBException;
import com.sap.spe.conversion.exc.ConversionInconsistentDataException;
import com.sap.spe.conversion.exc.ConversionMissingDataException;

/**
* Interface for accessing document specific product dbata.
*/
public interface ISPCDocumentProduct extends ISPCProduct, java.io.Serializable{

/**
 * sets an additional alternative unit fraction.
 * @param alternativeUnitFraction the new fraction
 */
public void setAdditionalAlternativeUnitFraction(IAlternativeUnitFraction alternativeUnitFraction) throws ProductInconsistentDBException, ConversionMissingDataException, ConversionInconsistentDataException;

/**
 * sets an additional alternative unit fraction.
 * @param unitName the unit name
 * @param numerator the numerator
 * @param denominator the denominator
 * @param exponent10 the exponent
 */
public void setAdditionalAlternativeUnitFraction(String unitName, int numerator, int denominator, int exponent10) throws ConversionMissingDataException, ConversionInconsistentDataException, ProductInconsistentDBException;

/**
 * sets an additional alternative unit fraction.
 * @param unitName the unit name
 * @param numeratorString the numerator as string
 * @param denominatorString the denominator as string
 * @param exponent10String the exponent as string
 */
public void setAdditionalAlternativeUnitFraction(String unitName, String numeratorString, String denominatorString, String exponent10String, boolean isExternalFormat) throws ConversionMissingDataException, ConversionInconsistentDataException, ProductInconsistentDBException;

}
