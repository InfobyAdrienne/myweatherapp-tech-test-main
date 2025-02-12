package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;

import java.time.LocalTime;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

  @Autowired
  VisualcrossingRepository weatherRepo;

  public CityInfo forecastByCity(String city) {

    return weatherRepo.getByCity(city);
  }

  public int getDaylightHours(String city) {
    // Fetch city info which includes current conditions
    CityInfo cityInfo = weatherRepo.getByCity(city);

    if (cityInfo == null || cityInfo.getCurrentConditions() == null) {
      throw new RuntimeException("Unable to fetch daylight data for " + city);
    }

    // Access sunrise and sunset times from currentConditions
    String sunriseStr = cityInfo.getCurrentConditions().getSunrise();
    String sunsetStr = cityInfo.getCurrentConditions().getSunset();

    if (sunriseStr == null || sunsetStr == null) {
      throw new RuntimeException("Sunrise/Sunset data missing for " + city);
    }

    // Parse sunrise and sunset times
    LocalTime sunrise = LocalTime.parse(sunriseStr);
    LocalTime sunset = LocalTime.parse(sunsetStr);

    // Calculate daylight duration
    long daylightMinutes = Duration.between(sunrise, sunset).toMinutes();
    return (int) daylightMinutes / 60;
  }

  public String compareDaylight(String city1, String city2) {
    int daylight1 = getDaylightHours(city1);
    int daylight2 = getDaylightHours(city2);

    if (daylight1 > daylight2) {
      return city1 + " has longer daylight hours.";
    } else if (daylight2 > daylight1) {
      return city2 + " has longer daylight hours.";
    } else {
      return "Both cities have the same daylight duration.";
    }
  }

}
