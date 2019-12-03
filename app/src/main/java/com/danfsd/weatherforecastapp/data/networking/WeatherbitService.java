package com.danfsd.weatherforecastapp.data.networking;

import com.danfsd.weatherforecastapp.data.networking.response.WeatherResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherbitService {

    @GET("current")
    Call<WeatherResult> getCurrentWeatherForCity(
            @Query("city") String city,
            @Query("key") String apiKey,
            @Query("lang") String language
    );

    @GET("current")
    Call<WeatherResult> getCurrentWeatherForCoordinates(
            @Query("lat") Double latitude,
            @Query("lon") Double longitude,
            @Query("key") String apiKey,
            @Query("lang") String language
    );

    @GET("forecast/daily")
    Call<WeatherResult> getForecastForNextDays(
            @Query("city") String city,
            @Query("days") int numberOfDays,
            @Query("unit") String unit,
            @Query("key") String apiKey,
            @Query("lang") String language
    );
}
