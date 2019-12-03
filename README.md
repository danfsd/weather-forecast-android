# weather-forecast-android

## Requirements

- Android API 23 or higher

## Tests

No tests were providden for the time being. The goal was to at least make unit tests for the Presenter layer.

## Usage

Simply type in the name of the city you want to get tomorrow's weather forecast

### Known Issues

The radio group for units on the search screen was supposed to only change the temperature unit. Instead it's changing units of temperature, measure, etc.

If you select Fahrenheit, not only the temperature units will be changed but the other ones as well. The catch is that all other units (aside from temperature) are hardcoded. So you will get:

- millimiter `mm` when it should've been inches `in`
- meters per second `m/s` when it should've been miles per hour `mph`
- etc
