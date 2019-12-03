package com.danfsd.weatherforecastapp;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class WeatherForecastApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        setupRealm();
    }

    /**
     * Initializes Realm default instance
     */
    private void setupRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("weather.realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
