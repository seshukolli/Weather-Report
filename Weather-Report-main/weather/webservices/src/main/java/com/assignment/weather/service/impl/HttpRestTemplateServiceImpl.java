package com.assignment.weather.service.impl;

import com.assignment.weather.exception.DataException;
import com.assignment.weather.service.HttpRestTemplateService;
import com.assignment.weather.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */

@Slf4j
@Service
public class HttpRestTemplateServiceImpl implements HttpRestTemplateService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> sendHttpRequest(String url, HttpMethod httpMethod,
                                                  HttpEntity<String> httpEntity) throws DataException {
        try{
            return restTemplate.exchange(url, httpMethod,httpEntity,String.class);
        } catch (Exception e){
            log.error(e.getMessage());
            return ExceptionUtils.getGeneralException(e);
        }
    }




}
