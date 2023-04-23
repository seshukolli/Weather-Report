package com.assignment.weather.service.impl;

import com.assignment.weather.model.GeoCodeModel;
import com.assignment.weather.service.HttpRestTemplateService;
import com.assignment.weather.service.WeatherApiService;
import com.assignment.weather.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */

@Slf4j
@Service
public class WeatherApiServiceImpl implements WeatherApiService {

    @Autowired
    HttpRestTemplateService httpRestTemplateService;

    @Value("${google.api.key}")
    private String googleApiKey;
    @Value("${google.geocoding.api.url}")
    private String googleGeoCodingApiUrl;
    @Value("${open.weather.api.url}")
    private String openWeatherApiUrl;
    @Value("${open.weather.map.api.key}")
    private String openWeatherMapApiKey;
    @Value("${open.weather.map.host}")
    private String openWeatherMapHost;

    @Override
    public ResponseEntity<String> fetchGeoCodingApiResponse(String zipCode) throws Exception {
        try {
            return httpRestTemplateService.sendHttpRequest(
                    getGeoCodingApiUrl(zipCode),
                    HttpMethod.GET, getHttpEntity(getHeadersForGeoCodingRequest()));
        }catch(Exception e){
            log.error(e.getMessage());
            return ExceptionUtils.getGeneralException(e);
        }
    }

    @Override
    public ResponseEntity<String> fetchOpenWeatherApiResponse(GeoCodeModel geoCodeModel,
                                                              String city, String countryCode) throws Exception {
        try {
            return httpRestTemplateService.sendHttpRequest(
                    getOpenWeatherApiUrl(geoCodeModel, city, countryCode),
                    HttpMethod.GET, getHttpEntity(getHeadersForGetDailyWeatherRequest()));
        }catch(Exception e){
            log.error(e.getMessage());
            return ExceptionUtils.getGeneralException(e);
        }
    }

    private String getGeoCodingApiUrl(String zipCode){
        return (UriComponentsBuilder.fromHttpUrl(googleGeoCodingApiUrl)
                .queryParam("address", zipCode)
                .queryParam("key", googleApiKey)).toUriString();
    }

    private HttpHeaders getHeadersForGeoCodingRequest()  {
        return new HttpHeaders();
    }



    private HttpEntity<String> getHttpEntity(HttpHeaders httpHeaders){
        return new HttpEntity<>("",httpHeaders);
    }

    private String getOpenWeatherApiUrl(GeoCodeModel geoCodeModel, String city, String countryCode){

        return (UriComponentsBuilder.fromHttpUrl(openWeatherApiUrl)
                .queryParam("q", city+","+countryCode)
                .queryParam("lat", geoCodeModel.getLatitude())
                .queryParam("lon", geoCodeModel.getLongitude())
                .queryParam("id", geoCodeModel.getZipCode())).toUriString();

    }

    private HttpHeaders getHeadersForGetDailyWeatherRequest()  {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("x-rapidapi-key", openWeatherMapApiKey);
        httpHeaders.add("x-rapidapi-host", openWeatherMapHost);
        return httpHeaders;
    }

}
