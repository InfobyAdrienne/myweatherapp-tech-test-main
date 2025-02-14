package com.weatherapp.myweatherapp.exceptions;

public class InvalidApiKeyException extends RuntimeException {
  public InvalidApiKeyException(String message) {
    super(message);
  }
}