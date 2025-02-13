package com.weatherapp.myweatherapp.service;

import static org.junit.jupiter.api.Assertions.*;

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

}