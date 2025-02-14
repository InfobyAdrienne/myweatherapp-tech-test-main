package com.weatherapp.myweatherapp.exceptions;

public class WeatherServiceException extends RuntimeException{
  public WeatherServiceException(String message) {
    super(message);
  }
}
