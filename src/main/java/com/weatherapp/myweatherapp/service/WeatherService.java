package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;

import java.time.LocalTime;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

  @Autowired
  VisualcrossingRepository weatherRepo;

  public CityInfo forecastByCity(String city) {
    return weatherRepo.getByCity(city);
  }

  public LocalTime getSunriseTime(String city) {
    CityInfo cityInfo = weatherRepo.getByCity(city);

    if (cityInfo.currentConditions.sunrise == null) {
      throw new RuntimeException("Unable to fetch sunrise data for " + city);
    }

    String sunriseStr = cityInfo.currentConditions.sunrise;
    LocalTime sunrise = LocalTime.parse(sunriseStr);

    return sunrise;
  }

  public LocalTime getSunsetTime(String city) {
    CityInfo cityInfo = weatherRepo.getByCity(city);

    if (cityInfo.currentConditions.sunset == null) {
      throw new RuntimeException("Unable to fetch sunset data for " + city);
    }

    String sunsetStr = cityInfo.currentConditions.sunset;
    LocalTime sunset = LocalTime.parse(sunsetStr);

    return sunset;
  }

  public int getDaylightHours(String city) {
    // Fetch city info which includes current conditions
    CityInfo cityInfo = weatherRepo.getByCity(city);

    if (cityInfo == null) {
      throw new RuntimeException("Unable to fetch any data for " + city);
    }

    if (cityInfo.currentConditions == null) {
      throw new RuntimeException("Unable to fetch current conditions for " + city);
    }

    // Get sunrise and sunset times
    LocalTime sunrise = getSunriseTime(city);
    LocalTime sunset = getSunsetTime(city);

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
