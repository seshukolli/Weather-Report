package com.assignment.weather.dto;

import lombok.Data;

import java.util.Calendar;

/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */

@Data
public class WeatherDetailsBean {

    private Calendar createdOn;

    private String mainDescription;

    private String temperature;

    private String temperatureMin;

    private String temperatureMax;

    private String humidity;

    private String windSpeed;

}
