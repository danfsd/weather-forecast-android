package com.danfsd.weatherforecastapp.data.mapper;

import com.danfsd.weatherforecastapp.data.model.Weather;
import com.danfsd.weatherforecastapp.data.networking.response.WeatherResponse;

import java.util.ArrayList;
import java.util.List;

public class WeatherMapper {

    public static List<Weather> mapToWeather(List<WeatherResponse> weatherResponses) {
        List<Weather> weathers = new ArrayList<>();

        for (WeatherResponse weatherResponse : weatherResponses) {
            final Weather weather = new Weather();
            weathers.add(weather);
        }

        return weathers;
    }
}
