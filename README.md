# MyWeather App Tech Test

Welcome to the MyWeather App Tech Test.

## The Codebase

The codebase is a Java application built with the Spring framework. It includes a `WeatherController` class where the new features will be added.

### Prerequisites

- [Java sdk 17](https://openjdk.java.net/projects/jdk/17/)
- [Maven 3.6.3+](https://maven.apache.org/install.html)
- API key for [Visual Crossing Weather API](https://www.visualcrossing.com/weather-data-editions). 
  - This can be done by creating a free account on the above link. Then you will need to add your key to the `weather.visualcrossing.key` field in src/main/resources/application.properties

## Instructions for getting started

Operating system: macOs

Following the prerequisites, set an environment variable for your API key using `export VISUALCROSSING_API_KEY=your-api-key-here`. Test that the environment variable is set with `echo $VISUALCROSSING_API_KEY`. Setting the environment variable ensures that your API key is not committed to Git and remains secure. Remember, the environment variable must be set every time you start a new terminal session. The environment variable must be set in the terminal where the app is run. 

To run the app use `mvn spring-boot:run` from your root folder.

While the app is running, in a seperate terminal test the API using `curl -X GET "http://localhost:8080/forecast/London"`

## User stories

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

## Testing

To run tests do `mvn test` from the root.

For unit testing I have focused on testing the WeatherService class. The tests ensure the accuracy and reliability of weather-related functionalities, including: Fetching weather forecasts, sunrise and sunset times, calculating the daylight durtaion and weather comparisons for two cities (daylight duration and current weather conditions for rain). The tests are implemented using JUnit 5 and Mockito for mocking API calls to the VisualcrossingRepository.

## Other points to consider 

* When I first began working on user story one I iniitally only had cone function compareDaylight() method but when writing unit tests decided it would be better to split this method into smaller, more focused functions. This refactoring improved testability, readability, reusability, maintainability, and debugging while following best practices like the Single Responsibility Principle.

* I think a great next feauture for the current app features would be implementing fuzzy matching for city names. This would significanlty enhance the user experience and make the app more tolerant of input variations. There are probably numerous ways this can be done, but incorporating error handling to suggest corrections for inputs would be especially useful. I have seen that the API does this to a certain degree and is able to find 'LON' and 'NYC' for example. 

* A significant improvement to the current features as they stand would be better exception handling. This was a gap in my knowledge and my ability on how to test these exceptions was limited to testing them manually.

* I would have liked to make a diagram explaining the Controller-Service-Repository pattern. 

### Resources

* The most useful resource for using Mockito was [this YouTube video](https://www.youtube.com/watch?v=XVFqOFKGeGM)
* Understanding [Controller-Service-Repository: Simplifying Java Spring Boot](https://www.youtube.com/watch?v=D44si7o4ndg)





