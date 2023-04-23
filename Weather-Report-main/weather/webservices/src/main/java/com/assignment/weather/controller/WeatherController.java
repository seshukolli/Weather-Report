package com.assignment.weather.controller;


import com.assignment.weather.dto.WeatherDetailsBean;
import com.assignment.weather.exception.DataException;
import com.assignment.weather.service.WeatherDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */

@RestController
@RequestMapping( "/weather" )
public class WeatherController {

    @Autowired
    WeatherDetailsService weatherDetailsService;

    @GetMapping( value = "/todayWeather/{zipCode}/{city}/{countryCode}" )
    public WeatherDetailsBean getWeatherDetailsForZipCode(@PathVariable( "zipCode" ) final String zipCode,
                                                          @PathVariable( "city" ) final String city,
                                                          @PathVariable( "countryCode" ) final String countryCode ) throws DataException {
        return weatherDetailsService.getTodayWeatherDetailsForZipCode(zipCode, city, countryCode);
    }


}
