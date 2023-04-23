package com.assignment.weather.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */
public class DataException extends Exception {

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;

    /**
     * Instantiates a new Data exception.
     *
     * @param errorCode
     *            the error code
     * @param errorMessage
     *            the error message
     * @param httpStatus
     *            the http status
     */
    public DataException( String errorCode, String errorMessage, HttpStatus httpStatus )
    {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

}
