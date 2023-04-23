package com.assignment.weather.utils;

import com.assignment.weather.exception.DataException;
import org.springframework.http.HttpStatus;

import static com.assignment.weather.utils.StringConstantUtils.FAULT;
/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */

public final class ExceptionUtils {

    private ExceptionUtils()
    {
    }

    public static <T> T getGeneralException(Exception e)throws DataException
    {
        throw new DataException(FAULT, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> T getCustomException(String msg, HttpStatus httpStatus)throws DataException
    {
        throw new DataException(FAULT, msg, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
