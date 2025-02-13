package com.weatherapp.myweatherapp.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import com.weatherapp.myweatherapp.model.CityInfo;


@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {
  @Mock
  VisualcrossingRepository weatherRepo;

  @InjectMocks
  WeatherService weatherService;

  @Test
  void forecastByCity() {
    CityInfo cityInfo = new CityInfo();
    cityInfo.address = "Lagos";
    Mockito.when(weatherRepo.getByCity(cityInfo.address)).thenReturn(cityInfo);

    CityInfo result = weatherService.forecastByCity(cityInfo.address);

    assertEquals(cityInfo, result);
  }

  @Test
  void getSunriseTime() {
    CityInfo cityInfo = new CityInfo();
    cityInfo.currentConditions = new CityInfo.CurrentConditions();
    cityInfo.address = "Lagos";
    cityInfo.currentConditions.sunrise = "06:00:00";
    Mockito.when(weatherRepo.getByCity(cityInfo.address)).thenReturn(cityInfo);

    LocalTime sunriseTime = weatherService.getSunriseTime(cityInfo.address);
    String formattedSunriseTime = sunriseTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

    assertEquals("06:00:00", formattedSunriseTime);
  }
  
  @Test
  void getSunsetTime() {
    CityInfo cityInfo = new CityInfo();
    cityInfo.currentConditions = new CityInfo.CurrentConditions();
    cityInfo.address = "London";
    cityInfo.currentConditions.sunset = "16:00:00";
    Mockito.when(weatherRepo.getByCity(cityInfo.address)).thenReturn(cityInfo);

    LocalTime sunsetTime = weatherService.getSunsetTime(cityInfo.address);
    String formattedSunsetTime = sunsetTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

    assertEquals("16:00:00", formattedSunsetTime);
  }
  
  @Test
  void getDaylightHours() {
    CityInfo cityInfo = new CityInfo();
    cityInfo.currentConditions = new CityInfo.CurrentConditions();
    cityInfo.address = "Lagos";
    cityInfo.currentConditions.sunrise = "06:00:00";
    cityInfo.currentConditions.sunset = "18:00:00";
    Mockito.when(weatherRepo.getByCity(cityInfo.address)).thenReturn(cityInfo);

    int daylightHours = weatherService.getDaylightHours(cityInfo.address);

    assertEquals(12, daylightHours);
  }

  @Test
  void compareDaylight() {
    CityInfo cityInfo1 = new CityInfo();
    cityInfo1.currentConditions = new CityInfo.CurrentConditions();
    cityInfo1.address = "Lagos";
    cityInfo1.currentConditions.sunrise = "06:00:00";
    cityInfo1.currentConditions.sunset = "18:00:00";
    Mockito.when(weatherRepo.getByCity(cityInfo1.address)).thenReturn(cityInfo1);

    CityInfo cityInfo2 = new CityInfo();
    cityInfo2.currentConditions = new CityInfo.CurrentConditions();
    cityInfo2.address = "London";
    cityInfo2.currentConditions.sunrise = "07:00:00";
    cityInfo2.currentConditions.sunset = "16:00:00";
    Mockito.when(weatherRepo.getByCity(cityInfo2.address)).thenReturn(cityInfo2);

    String daylightComparison = weatherService.compareDaylight(cityInfo1.address, cityInfo2.address);

    assertEquals("Lagos has more daylight hours than London", daylightComparison);
  }


}