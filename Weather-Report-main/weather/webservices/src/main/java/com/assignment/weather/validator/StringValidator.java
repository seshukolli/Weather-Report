package com.assignment.weather.validator;

import com.assignment.weather.exception.DataException;
import com.assignment.weather.utils.ExceptionUtils;
import com.assignment.weather.utils.NullEmptyUtils;
import org.springframework.http.HttpStatus;

/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */

public final class StringValidator {

    private StringValidator()
    {
    }

    public static void validateStringsForNullOrEmpty( String ...strVals ) throws DataException
    {
        for (String val : strVals) {
            if (NullEmptyUtils.isNullorEmptyOrNullString(val)) {
                ExceptionUtils.getCustomException("String parameters cannot be null or empty",
                        HttpStatus.BAD_REQUEST);
            }
        }
    }

}
