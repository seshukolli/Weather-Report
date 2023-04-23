package com.assignment.weather.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */

public final class NullEmptyUtils {

    private NullEmptyUtils()
    {
    }

    /**
     * Is nullor empty or null string boolean.
     *
     * @param val
     *            the val
     * @return the boolean
     */
    public static boolean isNullorEmptyOrNullString( String val )
    {
        return isNull(val) || val.isEmpty() || val.trim().equalsIgnoreCase("empty")
                || val.trim().equalsIgnoreCase("null");
    }

    /**
     * Is null boolean.
     *
     * @param val
     *            the val
     * @return the boolean
     */
    public static boolean isNull( Object val )
    {
        return val == null;
    }
}
