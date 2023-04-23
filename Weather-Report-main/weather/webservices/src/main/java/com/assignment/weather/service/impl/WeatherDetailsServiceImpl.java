package com.assignment.weather.service.impl;

import com.assignment.weather.dto.WeatherDetailsBean;
import com.assignment.weather.exception.DataException;
import com.assignment.weather.model.GeoCodeModel;
import com.assignment.weather.model.WeatherReportModel;
import com.assignment.weather.respository.GeoCodeModelRepository;
import com.assignment.weather.respository.WeatherReportModelRepository;
import com.assignment.weather.service.HttpRestTemplateService;
import com.assignment.weather.service.WeatherApiService;
import com.assignment.weather.service.WeatherDetailsService;
import com.assignment.weather.utils.ExceptionUtils;
import com.assignment.weather.validator.StringValidator;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

/**
 * @author Santosh Chaluvaraju on 14/11/2020
 */

@Service
@Slf4j
public class WeatherDetailsServiceImpl implements WeatherDetailsService {

    @Autowired
    GeoCodeModelRepository geoCodeModelRepository;
    @Autowired
    HttpRestTemplateService httpRestTemplateService;
    @Autowired
    WeatherReportModelRepository weatherReportModelRepository;
    @Autowired
    WeatherApiService weatherApiService;


    @Override
    public WeatherDetailsBean getTodayWeatherDetailsForZipCode(String zipCode,
                                                        String city,
                                                        String countryCode )throws DataException {
        try {
            log.info("****** Fetching todays weather details for zipcode, city and country code ********");
            StringValidator.validateStringsForNullOrEmpty(zipCode,
                    city, countryCode);
            GeoCodeModel geoCodeModel = getGeoCodeModelForZipCode(zipCode);
            WeatherReportModel weatherReportModel = getWeatherReportModelForGeoCode(geoCodeModel,
                    city, countryCode);

            return mapWeatherReportModelToBean(weatherReportModel);
        }catch(DataException e){
            log.error(e.getMessage());
            throw e;
        } catch(Exception e){
            log.error(e.getMessage());
            return ExceptionUtils.getGeneralException(e);
        }
    }

    private WeatherDetailsBean mapWeatherReportModelToBean(WeatherReportModel weatherReportModel){
        log.info("****** Mapping weather report model to bean ********");
        WeatherDetailsBean weatherDetailsBean = new WeatherDetailsBean();
        weatherDetailsBean.setCreatedOn(weatherReportModel.getCreatedOn());
        weatherDetailsBean.setHumidity(weatherReportModel.getHumidity());
        weatherDetailsBean.setMainDescription(weatherReportModel.getMainDescription());
        weatherDetailsBean.setTemperature(weatherReportModel.getTemperature());
        weatherDetailsBean.setTemperatureMax(weatherReportModel.getTemperatureMax());
        weatherDetailsBean.setTemperatureMin(weatherReportModel.getTemperatureMin());
        weatherDetailsBean.setWindSpeed(weatherDetailsBean.getWindSpeed());

        return weatherDetailsBean;
    }

    private WeatherReportModel getWeatherReportModelForGeoCode(GeoCodeModel geoCodeModel,
                                                               String city, String countryCode) throws DataException{
        try {
            Optional<WeatherReportModel> weatherReportModelOptional =
                    weatherReportModelRepository.getWeatherReportForTodayByGeoCodes();
            if (!weatherReportModelOptional.isPresent()){
                //fetch if not present in DB
                return fetchWeatherReportForToday(geoCodeModel, city, countryCode);
            }
            //return weather report from DB if already fetched
            return weatherReportModelOptional.get();
        }catch(DataException e){
            log.error(e.getMessage());
            throw e;
        } catch(Exception e){
            log.error(e.getMessage());
            return ExceptionUtils.getGeneralException(e);
        }
    }

    private WeatherReportModel fetchWeatherReportForToday(GeoCodeModel geoCodeModel,
                                                          String city, String countryCode) throws DataException{
        try {
            log.info("****** Make API call to fetch weather details ********");
            ResponseEntity<String> responseObject = weatherApiService.
                    fetchOpenWeatherApiResponse(geoCodeModel, city, countryCode);

            return weatherReportModelRepository.save(parseResponseJsonStringToWeatherReportModel(responseObject));
        }catch(DataException e){
            log.error(e.getMessage());
            throw e;
        } catch(Exception e){
            log.error(e.getMessage());
            return ExceptionUtils.getGeneralException(e);
        }
    }

    private GeoCodeModel getGeoCodeModelForZipCode(String zipCode) throws DataException{
        try {
            Optional<GeoCodeModel> geoCodeModelOptional = geoCodeModelRepository.findByZipCode(zipCode);
            if (!geoCodeModelOptional.isPresent()) {
                // fetch geo codes if not present in DB
                return fetchGeoCodeModelForZipCode(zipCode);
            }
            //fetch geo codes from DB if already present
            return geoCodeModelOptional.get();
        }catch(DataException e){
            log.error(e.getMessage());
            throw e;
        } catch(Exception e){
            log.error(e.getMessage());
            return ExceptionUtils.getGeneralException(e);
        }
    }

    private GeoCodeModel fetchGeoCodeModelForZipCode(String zipCode) throws DataException{
        try {
            log.info("****** Make Google API call to fetch Geo codes ********");
            ResponseEntity<String> responseObject = weatherApiService.fetchGeoCodingApiResponse(zipCode);

            return geoCodeModelRepository.save(parseResponseJsonStringToGeoCodeModel(responseObject, zipCode));
        }catch(DataException e){
            log.error(e.getMessage());
            throw e;
        } catch(Exception e){
            log.error(e.getMessage());
            return ExceptionUtils.getGeneralException(e);
        }
    }

    private WeatherReportModel parseResponseJsonStringToWeatherReportModel (ResponseEntity<String> responseObject){
        log.info("****** Parsing response from Weather report ********");
        WeatherReportModel weatherReportModel = new WeatherReportModel();
        JSONObject jsonObject = new JSONObject(responseObject.getBody());
        JSONArray jsonArray = (JSONArray) jsonObject.get("weather");
        extractWeatherDescription(jsonArray, weatherReportModel);
        extractTemperatureAndHumidity(jsonObject.getJSONObject("main"), weatherReportModel);
        extractWindData(jsonObject.getJSONObject("wind"), weatherReportModel);
        weatherReportModel.setCreatedOn(Calendar.getInstance());
        return weatherReportModelRepository.save(weatherReportModel);
    }

    private void extractWeatherDescription(JSONArray jsonArray, WeatherReportModel weatherReportModel){
        for (Object o : jsonArray) {
            JSONObject jsonObject1 = (JSONObject) o;
            weatherReportModel.setMainDescription(jsonObject1.get("description").toString());

            break;
        }
    }

    private void extractTemperatureAndHumidity(JSONObject jsonObject, WeatherReportModel weatherReportModel){
        weatherReportModel.setHumidity(jsonObject.get("humidity").toString());
        weatherReportModel.setTemperature(jsonObject.get("temp").toString());
        weatherReportModel.setTemperatureMin(jsonObject.get("temp_min").toString());
        weatherReportModel.setTemperatureMax(jsonObject.get("temp_max").toString());
    }

    private void extractWindData(JSONObject jsonObject, WeatherReportModel weatherReportModel){
        weatherReportModel.setWindSpeed(jsonObject.get("speed").toString());
    }

    private GeoCodeModel parseResponseJsonStringToGeoCodeModel (ResponseEntity<String> responseObject,
                                                                String zipCode){
        log.info("****** Parsing geo codes from response ********");
        GeoCodeModel geoCodeModel = new GeoCodeModel();
        geoCodeModel.setZipCode(zipCode);
        JSONObject jsonObject = new JSONObject(responseObject.getBody());
        JSONArray jsonArray = (JSONArray) jsonObject.get("results");
        extractGeoCodes(jsonArray, geoCodeModel);
        return geoCodeModelRepository.save(geoCodeModel);
    }

    private void extractGeoCodes(JSONArray jsonArray, GeoCodeModel geoCodeModel){
        for (Object o : jsonArray) {
            JSONObject jsonObject1 = (JSONObject) o;
            JSONObject location = getLocationObject(getGeometryObject(jsonObject1));
            setGeoCodes(geoCodeModel, location);

            break;
        }
    }

    private JSONObject getGeometryObject(JSONObject jsonObject){
        return jsonObject.getJSONObject("geometry");
    }

    private JSONObject getLocationObject(JSONObject jsonObject){
        return jsonObject.getJSONObject("location");
    }

    private void setGeoCodes(GeoCodeModel geoCodeModel, JSONObject location){
        geoCodeModel.setLatitude(location.get("lat").toString());
        geoCodeModel.setLongitude(location.get("lng").toString());
    }

}
