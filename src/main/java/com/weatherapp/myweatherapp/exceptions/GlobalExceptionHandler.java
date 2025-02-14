package com.weatherapp.myweatherapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(InvalidApiKeyException.class)
  public ResponseEntity<String> handleInvalidApiKeyException(InvalidApiKeyException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
  }

  @ExceptionHandler(WeatherServiceException.class)
  public ResponseEntity<String> handleWeatherServiceException(WeatherServiceException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
  }

}