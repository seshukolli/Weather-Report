package com.assignment.weather.service;

import com.assignment.weather.dto.WeatherDetailsBean;
import com.assignment.weather.exception.DataException;

/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */

public interface WeatherDetailsService {

    WeatherDetailsBean getTodayWeatherDetailsForZipCode(String zipCode,
                                                        String city,
                                                        String countryCode) throws DataException;

}
