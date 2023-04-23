package com.assignment.weather.service;

import com.assignment.weather.model.GeoCodeModel;
import org.springframework.http.ResponseEntity;

/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */

public interface WeatherApiService {

    ResponseEntity<String> fetchGeoCodingApiResponse(String zipCode) throws Exception;

    ResponseEntity<String> fetchOpenWeatherApiResponse(GeoCodeModel geoCodeModel,
                                                       String city, String countryCode) throws Exception;

}
