package com.assignment.weather.service;

import com.assignment.weather.exception.DataException;
import org.springframework.http.*;

/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */

public interface HttpRestTemplateService {

    public ResponseEntity<String> sendHttpRequest(String url, HttpMethod httpMethod,
                                                  HttpEntity<String> httpEntity) throws DataException;

}
