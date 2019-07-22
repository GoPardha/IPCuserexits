/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.text;

import com.sap.spe.base.cache.Engine;


public interface ITextEngine extends Engine {

    /**
     * In addition to what other engines do, a Text Engine must implement
     * the following behavior:
     */
    /** @return the default language,
     * i.e. the language for which texts are drawn
     * if it does not exist in the desired language */
    public String getDefaultLanguage();

    /** @return an SAPLanguage Object. The search criterium is the SAP language code.         */
    public ISAPLanguage getLanguageForSAPCode(String language);

    /** @return an SAPLanguage Object. The search criterium is the ISO language code.         */
    public ISAPLanguage getLanguageForISOCode(String language);

    /** @return a domain object.         */
    public IDomain getDomain(String name);

    /** @return a dataelement.         */
    public IDataElement getDataElement(String name);

    /** @return a tablefield object.         */
    public ITableField getTableField(String tableName, String fieldName);

    public String getLanguageCode(String ISOLanguageCode);
}
