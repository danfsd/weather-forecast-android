package com.danfsd.weatherforecastapp.data.networking.response;

import com.squareup.moshi.Json;

public class WeatherResponse {

    // HH:mm
    @Json(name = "sunrise")
    private final String sunriseTimeString;

    // HH:mm
    @Json(name = "sunset")
    private final String sunsetTimeString;

    // %
    @Json(name = "rh")
    private final Integer relativeHumididy;

    // mb
    @Json(name = "pres")
    private final Double pressure;

    // m/s
    @Json(name = "wind_spd")
    private final Double windSpeed;

    @Json(name = "wind_cdir_full")
    private final String windDirection;

    // %
    @Json(name = "clouds")
    private final Integer cloudCoverage;

    // default to celsius
    @Json(name = "temp")
    private final Double temperature;

    @Json(name = "low_temp")
    private final Double lowestTemperature;

    @Json(name = "max_temp")
    private final Double highestTemperature;

    // default to celsius
    @Json(name = "app_min_temp")
    private final Double apparentTemperature;

    // d=day,n=night
    @Json(name = "pod")
    private final String partOfDay;

    // km
    @Json(name = "vis")
    private final Double visibility;

    @Json(name = "pop")
    private final Double rainProbability;

    // mm/hr
    @Json(name = "precip")
    private final Double precipitation;

    @Json(name = "weather")
    private final WeatherResponseDetail weatherDetail;

    public WeatherResponse(String sunriseTimeString, String sunsetTimeString, Integer relativeHumididy, Double pressure, Double windSpeed, String windDirection, Integer cloudCoverage, Double temperature, Double lowestTemperature, Double highestTemperature, Double apparentTemperature, String partOfDay, Double visibility, Double rainProbability, Double precipitation, WeatherResponseDetail weatherDetail) {
        this.sunriseTimeString = sunriseTimeString;
        this.sunsetTimeString = sunsetTimeString;
        this.relativeHumididy = relativeHumididy;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.cloudCoverage = cloudCoverage;
        this.temperature = temperature;
        this.lowestTemperature = lowestTemperature;
        this.highestTemperature = highestTemperature;
        this.apparentTemperature = apparentTemperature;
        this.partOfDay = partOfDay;
        this.visibility = visibility;
        this.rainProbability = rainProbability;
        this.precipitation = precipitation;
        this.weatherDetail = weatherDetail;
    }

    public String getSunriseTimeString() {
        return sunriseTimeString;
    }

    public String getSunsetTimeString() {
        return sunsetTimeString;
    }

    public Integer getRelativeHumididy() {
        return relativeHumididy;
    }

    public Double getPressure() {
        return pressure;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public Integer getCloudCoverage() {
        return cloudCoverage;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getLowestTemperature() {
        return lowestTemperature;
    }

    public Double getHighestTemperature() {
        return highestTemperature;
    }

    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    public String getPartOfDay() {
        return partOfDay;
    }

    public Double getVisibility() {
        return visibility;
    }

    public Double getRainProbability() {
        return rainProbability;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public WeatherResponseDetail getWeatherDetail() {
        return weatherDetail;
    }
}
