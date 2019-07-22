/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.spe.condmgnt.customizing;

import java.io.Serializable;


/**
 * Interface for an attributeMap-object. The access contains a list of attributeMaps.
 * This attributeMap-object contains the mapping between
 * attributes of the (sales) document and the key fields of the condition table which is
 * assigned to the access.
 */
public interface IAttributeMap extends Serializable {

    /**
     * @return my access
     */
    public IAccess getAccess();

    /**
     * @return the field counter.
     * Corresponds to the database field <code>/SAPCND/T682Z-ZAEHK</code>.
     */
    public int getFieldCounter();

    /**
     * @return the attributeClass of the document (e.g. 'KOMK-PMATN').
     * Corresponds to the database fields <code>/SAPCND/T682Z-QUFNA</code>.
     */
    public IApplicationField getDocumentField();

    /**
     * @return the attributeClass of the condition table (e.g. 'MATNR').
     * Corresponds to the database fields <code>/SAPCND/T682Z-ZIFNA</code>.
     */
    public ITableField getTableField();

    /**
     * @return the direct value of the attributeMap if maintained.
     * Corresponds to the database fields <code>/SAPCND/T682Z-QUDIW</code>.
     */
    public String getDirectValue();

    /**
     * @return the access field type.
     * Corresponds to the database fields <code>/SAPCND/T682Z-FSTST</code>.
     */
    public char getAccessFieldType();

    /**
     * @return the evaluation priority.
     * Corresponds to the database fields <code>/SAPCND/T682Z-MBWRT</code>.
     */
    public int getEvaluationPriority();
    
    /**
     * @return true if an initial value is allowed
     * Corresponds to the database fields <code>/SAPCND/T682Z-KZINI</code>.   
     */
    public boolean isInitialValueAllowed();

    public interface AccessFieldType {
        public static final char FIELD_IN_FIXED_KEY_PART = ' ';
        public static final char FIELD_IN_FREE_KEY_PART = 'A';
        public static final char KEY_FIELD_NOT_USED_FOR_SELECTION = 'b';
        public final static char NOT_SUPPORTED_KEY_FIELD_TO_BE_DETERMINED_IN_ACCESS = 'B'; // only possible in R/3
        public final static char NOT_SUPPORTED_DATA_FIELD = 'C'; // only possible in R/3
    }
}
