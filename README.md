# MyWeather App Tech Test

Welcome to the MyWeather App Tech Test.

## The Challenge

You are tasked with implementing two new features in the app:

1. **Daylight Hours Comparison:** Given two city names, compare the length of the daylight hours between them and return the city with the longest day. In this context, "daylight hours" means the time between sunrise and sunset.

2. **Rain Check:** Given two city names, check which city it is currently raining in.

In addition to implementing these 2 features, you will also need to write tests verifying that your code works as expected.

If possible, include exception handling in the controller.

Finally, you can write any documentation as you see fit, explaining your choices and how the code works.

## The Codebase

The codebase is a Java application built with the Spring framework. It includes a `WeatherController` class where you will add your new features.

## Implementation Details

You will need to implement these features by adding new endpoints to the `WeatherController`.

### Prerequisites

- [Java sdk 17](https://openjdk.java.net/projects/jdk/17/)
- [Maven 3.6.3+](https://maven.apache.org/install.html)
- API key for [Visual Crossing Weather API](https://www.visualcrossing.com/weather-data-editions). 
  - This can be done by creating a free account on the above link. Then you will need to add your key to the `weather.visualcrossing.key` field in src/main/resources/application.properties

## Submission

* Push the downloaded version of this repo to your Github
* Make a branch for your changes
* Once you're ready to submit, raise a Pull Request to merge your changes with your main branch and share the repo with us.

Good luck!

### Instructions for getting started

Operating system: macOs

Following the prerequisites, set an environment variable for your API key using `export VISUALCROSSING_API_KEY=your-api-key-here`. Test that the environment variable is set with `echo $VISUALCROSSING_API_KEY`. Setting the environment variable ensures that your API key is not committed to Git and remains secure. Remember, the environment variable must be set every time you start a new terminal session.

To run the app use `mvn spring-boot:run` from your root folder.

While the app is running, in a seperate terminal test the API using `curl -X GET "http://localhost:8080/forecast/London"`

### User stories

Daylight Hours Comparison

1. Provide the name of two cities 
2. The application fetches the sunrise and sunset data for each city from the weather API
4. The length of daylight is calculated based on the difference between sunset and sunrise times
5. The city with the longer day is returned

The sunrise and sunset times are fetched from the currentConditions.sunrise and currentConditions.sunset fields in the weather API response.

The result can be found by using `curl -X GET "http://localhost:8080/compare-daylight/{city1}/{city2}"`

For example: `curl -X GET "http://localhost:8080/compare-daylight/Tokyo/London"` 

Rain Check

1. Provide the names of two cities
2. The application fetches the curent weather conditions from each city from the weather API
3. It checks if either or both conditions contain mention of rain 
4. The city (or cities) where it is currently raining is returned

The descritption of whether it is currenty raining or not is fetched from currentConditions.conditions field in the weather API response. From looking at what the json returns, this is the most reliable option. 

The result can be found by using `curl -X GET "http://localhost:8080/compare-currently-raining/{city1}/{city2}"`

For example: `curl -X GET "http://localhost:8080/compare-currently-raining/Tokyo/London"` 

### Testing

To run tests do `mvn test` from the root.

Unit testing is done using Mockito.  