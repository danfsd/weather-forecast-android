package com.danfsd.weatherforecastapp.ui.forecast;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.danfsd.weatherforecastapp.data.networking.response.WeatherResponse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.danfsd.weatherforecastapp.R;
import com.danfsd.weatherforecastapp.data.networking.response.WeatherResult;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastActivity extends AppCompatActivity implements ForecastContract.View {

    static final String TAG = "FORECAST_ACTIVITY";

    @BindView(R.id.forecast_container)
    ConstraintLayout forecastContainer;

    @BindView(R.id.city_indicator)
    TextView cityIndicator;

    @BindView(R.id.weather_icon)
    ImageView weatherIcon;

    @BindView(R.id.current_temperature)
    TextView currentTemperature;

    @BindView(R.id.min_temperature)
    TextView minimumTemperature;

    @BindView(R.id.max_temperature)
    TextView maximumTemperature;

    @BindView(R.id.apparent_temperature)
    TextView apparentTemperature;

    @BindView(R.id.rain_probability)
    TextView rainProbability;

    @BindView(R.id.precipitation)
    TextView precipitation;

    @BindView(R.id.wind_details)
    TextView windDetails;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private ForecastContract.Presenter presenter;
    private String unit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        ButterKnife.bind(this);

        setupToolbar();

        presenter = new ForecastPresenter();
        presenter.setView(this);

        String city = getIntent().getStringExtra("city");
        setTitle(String.format("Previsão para %s", city));
        unit = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE).getString(getString(R.string.pref_temp_unit), "C");
        presenter.fetchWeatherForCity(city, unit);
    }

    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void startLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showWeather(WeatherResult result) {
        // 0 - today; 1 - tomorrow; 2 - day after, etc
        WeatherResponse currentForecast = result.getData().get(1);

        cityIndicator.setText(Html.fromHtml(String.format(Locale.getDefault(), "Amanhã em <b>%s, %s</b>:", result.getCity(), result.getCountryCode())));
        weatherIcon.setImageResource(getResources().getIdentifier(currentForecast.getWeatherDetail().getIcon(), "drawable", getPackageName()));
        currentTemperature.setText(
            String.format(Locale.getDefault(), "%.1f˚%s", currentForecast.getTemperature(), unit)
        );
        minimumTemperature.setText(
            Html.fromHtml(String.format(Locale.getDefault(), "Temp. Mínima: <b>%.1f˚%s</b>", currentForecast.getLowestTemperature(), unit))
        );
        maximumTemperature.setText(
            Html.fromHtml(String.format(Locale.getDefault(), "Temp. Máxima: <b>%.1f˚%s</b>", currentForecast.getHighestTemperature(), unit))
        );
        apparentTemperature.setText(
            Html.fromHtml(String.format(Locale.getDefault(), "Min. Sensação Térmica: <b>%.1f˚%s</b>", currentForecast.getApparentTemperature(), unit))
        );
        rainProbability.setText(
            Html.fromHtml(String.format(Locale.getDefault(), "Probabilidade de Chuva: <b>%.1f%%</b>", currentForecast.getRainProbability()))
        );
        precipitation.setText(
            Html.fromHtml(String.format(Locale.getDefault(), "Precipitação: <b>%.1fmm</b>", currentForecast.getPrecipitation()))
        );
        windDetails.setText(
            Html.fromHtml(String.format(Locale.getDefault(), "Vento: <b>%.1fm/s para o %s</b>", currentForecast.getWindSpeed(), currentForecast.getWindDirection()))
        );

        forecastContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Erro ao obter dados, tente novamente", Toast.LENGTH_LONG).show();
        finish();
    }
}
