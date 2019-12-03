package com.danfsd.weatherforecastapp.data.networking.response;

import com.squareup.moshi.Json;

public class WeatherResponseDetail {
    @Json(name = "icon")
    private final String icon;

    @Json(name = "code")
    private final String code;

    @Json(name = "description")
    private final String description;

    public WeatherResponseDetail(String icon, String code, String description) {
        this.icon = icon;
        this.code = code;
        this.description = description;
    }

    public String getIconPath() {
        return "https://www.weatherbit.io/static/img/icons" + icon + ".png";
    }

    public String getIcon() {
        return icon;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
