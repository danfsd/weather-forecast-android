package com.danfsd.weatherforecastapp.ui.forecast;

import android.util.Log;

import com.danfsd.weatherforecastapp.data.networking.ApiService;
import com.danfsd.weatherforecastapp.data.networking.response.WeatherResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastPresenter implements ForecastContract.Presenter {

    static final String TAG = "FORECAST_PRESENTER";

    private ForecastContract.View view;

    private final int NUMBER_OF_DAYS = 7;
    private final String API_KEY = "8751d9d8221d455a8646fa2818e72661";
    private final String LANGUAGE = "pt";


    @Override
    public void fetchWeatherForCity(String city, String unit) {
        view.startLoading();
        ApiService.getInstance()
                .getForecastForNextDays(city, NUMBER_OF_DAYS, unit, API_KEY, LANGUAGE)
                .enqueue(new Callback<WeatherResult>() {
                    @Override
                    public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                        view.stopLoading();
                        if (response.isSuccessful() && response.body() != null) {
                            view.showWeather(response.body());
                        } else {
                            view.showError();
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherResult> call, Throwable t) {
                        Log.w(TAG, t.getLocalizedMessage());
                        view.stopLoading();
                        view.showError();
                    }
                });
    }

    @Override
    public void setView(ForecastContract.View view) {
        this.view = view;
    }
}
