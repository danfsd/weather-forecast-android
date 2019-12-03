package com.danfsd.weatherforecastapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.danfsd.weatherforecastapp.R;
import com.danfsd.weatherforecastapp.ui.forecast.ForecastActivity;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    static final String TAG = "MAIN_ACTIVITY";

    @BindView(R.id.city_input_layout)
    TextInputLayout cityInputLayout;

    @BindView(R.id.city_input)
    AutoCompleteTextView cityInput;

    @BindView(R.id.unit_group)
    RadioGroup temperatureUnitGroup;

    @BindView(R.id.search_button)
    Button searchButton;

    private boolean shouldSearchOnReturn = true;
    private MainContract.Presenter presenter;
    private ArrayAdapter<String> citiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter();
        presenter.setView(this);

        setupCityInput();
        setupTemperatureUnitGroup();
        setupSearchButton();
    }

    private void setupTemperatureUnitGroup() {
        temperatureUnitGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedOption = findViewById(checkedId);
                Character unit = checkedOption.getText()
                        .toString()
                        .toUpperCase()
                        .charAt(0);
                SharedPreferences.Editor preferencesEditor = getSharedPreferences(getString(R.string.pref_key), MODE_PRIVATE).edit();
                preferencesEditor.putString(getString(R.string.pref_temp_unit), String.valueOf(unit));
                preferencesEditor.apply();
            }
        });
    }

    private void setupCityInput() {
        citiesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, presenter.getCityList());
        cityInput.setAdapter(citiesAdapter);
        cityInput.setThreshold(2);
        cityInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                cityInputLayout.setError(null);
            }
        });

        cityInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH && shouldSearchOnReturn) {
                    openForecast();
                }
                return false;
            }
        });
    }

    private void setupSearchButton() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForecast();
            }
        });
    }

    private boolean validate() {
        return !cityInput.getText().toString().isEmpty();
    }

    private void openForecast() {
        String cityName = cityInput.getText().toString();

        if (!validate()) {
            cityInputLayout.setError("Campo não pode ficar vazio");
            return;
        }

        Intent intent = new Intent(this, ForecastActivity.class);
        intent.putExtra("city", cityName);
        startActivity(intent);;
    }
}
