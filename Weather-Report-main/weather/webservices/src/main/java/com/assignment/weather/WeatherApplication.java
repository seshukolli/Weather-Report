package com.assignment.weather;

import com.assignment.weather.exception.DataException;
import com.assignment.weather.service.WeatherDetailsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) throws DataException {
//		ApplicationContext x = SpringApplication.run(WeatherApplication.class, args);
		SpringApplication.run(WeatherApplication.class, args);
//		WeatherDetailsService weatherDetailsService = x.getBean(WeatherDetailsService.class);
//		weatherDetailsService.getTodayWeatherDetailsForZipCode("560061", "Bengaluru", "IN");
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
