/*

    Copyright (c) 2005 by SAP AG

    All rights to both implementation and design are reserved.

    Use and copying of this software and preparation of derivative works based
    upon this software are not permitted.

    Distribution of this software is restricted. SAP does not make any warranty
    about the software, its performance or its conformity to any specification.

*/

package com.sap.sxe.db;

public interface column {

    /**
       @roseuid 3742B7A5025D
     */
    public String get_name();

    /**
       @roseuid 3742B7A901D0
     */
    public type_info get_data_type();

    /**
       @roseuid 3742B7BF0357
     */
    public int get_size();

    /**
       @roseuid 3742B7C7001A
     */
    public int get_digits();

    /**
       @roseuid 3742B6F503C4
     */
    public boolean is_nullable();

    /**
       @roseuid 3742B7D101B1
     */
    public String get_comment();

    /**
       @roseuid 3742B7E300F5
     */
    public String get_default_value();

    /**
       @roseuid 3742B7F402AB
     */
    public int get_ordinal_position();

    public void set_name(String name);

    public void set_data_type(type_info data_type);

    public void set_size(int size);

    public void set_digits(int digits);

    public void set_is_nullable(boolean nullable);

    public void set_comment(String comment);

    public void set_default_value(String default_value);

    public void set_ordinal_position(int ordinal_position);
}
