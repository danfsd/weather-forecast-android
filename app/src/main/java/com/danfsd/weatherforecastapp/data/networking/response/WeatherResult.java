package com.danfsd.weatherforecastapp.data.networking.response;

import com.squareup.moshi.Json;

import java.util.List;


public class WeatherResult {
    @Json(name = "data")
    private final List<WeatherResponse> data;

    @Json(name = "city_name")
    private final String city;

    @Json(name = "country_code")
    private final String countryCode;

    public WeatherResult(List<WeatherResponse> data, String city, String countryCode) {
        this.data = data;
        this.city = city;
        this.countryCode = countryCode;
    }

    public List<WeatherResponse> getData() {
        return data;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
