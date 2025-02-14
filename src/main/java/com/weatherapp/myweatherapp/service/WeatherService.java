package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.exceptions.InvalidApiKeyException;
import com.weatherapp.myweatherapp.exceptions.WeatherServiceException;
import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;

import java.time.LocalTime;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class WeatherService {

  @Autowired
  VisualcrossingRepository weatherRepo;

  public CityInfo forecastByCity(String city) {
    try {
      CityInfo cityInfo = weatherRepo.getByCity(city);
      return cityInfo;
    } catch (HttpClientErrorException.Unauthorized e) { 
      throw new InvalidApiKeyException("Invalid API key: Please check your credentials.");
    } catch (Exception e) { // Any unexpected error
      throw new WeatherServiceException("Unexpected error while processing request for " + city);
    }
  }

  public LocalTime getSunriseTime(String city) {
    CityInfo cityInfo = forecastByCity(city);

    String sunriseStr = cityInfo.currentConditions.sunrise;
    LocalTime sunrise = LocalTime.parse(sunriseStr);

    return sunrise;
  }

  public LocalTime getSunsetTime(String city) {
    CityInfo cityInfo = forecastByCity(city);

    String sunsetStr = cityInfo.currentConditions.sunset;
    LocalTime sunset = LocalTime.parse(sunsetStr);

    return sunset;
  }

  public int getDaylightHours(String city) {
    LocalTime sunrise = getSunriseTime(city);
    LocalTime sunset = getSunsetTime(city);

    long daylightMinutes = Duration.between(sunrise, sunset).toMinutes();
    return (int) daylightMinutes / 60;
  }

  public String compareDaylight(String city1, String city2) {
    int daylight1 = getDaylightHours(city1);
    int daylight2 = getDaylightHours(city2);

    if (daylight1 > daylight2) {
      return city1 + " has more daylight hours than " + city2;
    } else if (daylight2 > daylight1) {
      return city2 + " has more daylight hours than " + city1;
    } else {
      return "Both cities have the same daylight duration.";
    }
  }

  public Boolean isCurrentlyRaining(String city) {
    CityInfo cityInfo = forecastByCity(city);

    if (cityInfo.currentConditions.conditions.toLowerCase().contains("rain")) {
      return true;
    } else {
      return false;
    }
  }

  public String compareCurrentlyRaining(String city1, String city2) {
    Boolean city1Rain = isCurrentlyRaining(city1);
    Boolean city2Rain = isCurrentlyRaining(city2);

    if (city1Rain && city2Rain) {
      return "Both cities are currently experiencing rain";
    } else if (city1Rain) {
      return city1 + " is currently experiencing rain";
    } else if (city2Rain) {
      return city2 + " is currently experiencing rain";
    } else {
      return "Neither city is currently experiencing rain";
    }
  }

}
