package com.danfsd.weatherforecastapp.ui.main;

import android.util.Log;

import com.danfsd.weatherforecastapp.Utils;
import com.danfsd.weatherforecastapp.data.model.City;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingFormatArgumentException;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;

    @Override
    public List<String> getCityList() {
        List<String> cities = new ArrayList<>();
        String assetContent = Utils.loadAsset((MainActivity) view, "cities.json");

        if (assetContent != null && !assetContent.isEmpty()) {
            Gson gson = new Gson();
            City[] citiesArray = gson.fromJson(assetContent, City[].class);
            for (City city : citiesArray) {
                String format = "%s";
                List<String> params = new ArrayList<>();

                params.add(city.getCity_name());

                if (city.getState_name() != null) {
                    format += ", %s";
                    params.add(city.getState_name());
                }

                if (city.getCountry_name() != null) {
                    format += ", %s";
                    params.add(city.getCountry_name());
                }

                String cityString = String.format(Locale.getDefault(), format, params.toArray());
                cities.add(cityString);
            }
        }

        return cities;
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }
}
