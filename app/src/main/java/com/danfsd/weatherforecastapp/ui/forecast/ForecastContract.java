package com.danfsd.weatherforecastapp.ui.forecast;

import com.danfsd.weatherforecastapp.data.networking.response.WeatherResult;

public interface ForecastContract {
    interface View {
        void startLoading();
        void stopLoading();
        void showWeather(WeatherResult result);
        void showError();
    }

    interface Presenter {
        void fetchWeatherForCity(String city, String unit);
        void setView(ForecastContract.View view);
    }
}
