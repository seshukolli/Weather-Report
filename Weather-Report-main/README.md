WEATHER_REPORT_SPRING_BOOT

API to test from postman or any online Rest API testing tool

localhost:8080/weather/todayWeather/zipCode=560061/city=Bengaluru/countryCode=IN

Parameters:
zipCode : Replace with any zip
city : corresponding city
countryCOde : corresponding country

Even after fetching the geo codes for the ZIP from google we need city and country code for weather report from Open weather rest API's.

Replace YOUR_GOOGLE_API_KEY and YOUR_OPEN_WEATHER_MAP_API_KEY in application.properties in webservices with your API keys