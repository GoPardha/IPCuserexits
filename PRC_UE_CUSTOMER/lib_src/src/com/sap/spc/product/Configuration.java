/*
 * Created on 13 mai 2005
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package com.sap.spc.product;

/**
 * @author i018282
 */
/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/


import com.sap.vmc.logging.Category;
import com.sap.vmc.logging.LocalizableCategory;


public class Configuration {
    /**
     * The category in which logging information is stored.
     */
    //public static final Category CATEGORY = Category.getCategory(Category.APPLICATIONS, "/AP/SPC/Product");

     public static final LocalizableCategory CATEGORY =
        LocalizableCategory.getCategory(Category.APPLICATIONS, "/AP/SPC/Product");
    public static final String MESSAGE_CLASS = "SPC_PROD";
}
