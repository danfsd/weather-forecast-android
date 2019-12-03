package com.danfsd.weatherforecastapp.data.networking;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiService {
    private static WeatherbitService instance;

    public static WeatherbitService getInstance() {
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.weatherbit.io/v2.0/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();
            instance = retrofit.create(WeatherbitService.class);
        }
        return instance;
    }

}
