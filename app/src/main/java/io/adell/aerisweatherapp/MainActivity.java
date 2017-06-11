package io.adell.aerisweatherapp;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.Switch;
import io.adell.aerisweatherapp.adapters.WeatherAdapter;
import io.adell.aerisweatherapp.utilities.WeatherUtility;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
  private WeatherAdapter weatherAdapter;

  private String ZIP = "11101";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    bindViews();
    WeatherUtility.getForecast(this, ZIP);
  }

  private void bindViews() {
    GridView gridView = (GridView) findViewById(R.id.activity_main_grid_view);
    gridView.setAdapter(getWeatherAdapter());
    Switch unitsToggle = (Switch) findViewById(R.id.activity_main_units_toggle);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      unitsToggle.setShowText(true);
    }
    unitsToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
          getWeatherAdapter().setUnits(WeatherAdapter.CELSIUS_UNITS);
        } else {
          getWeatherAdapter().setUnits(WeatherAdapter.FAHRENHEIT_UNITS);
        }
        getWeatherAdapter().notifyDataSetChanged();
      }
    });
  }

  private WeatherAdapter getWeatherAdapter() {
    if (weatherAdapter == null) weatherAdapter = new WeatherAdapter(this);
    return weatherAdapter;
  }

  public void showForecast(JSONArray days) {
    getWeatherAdapter().setDays(days);
    getWeatherAdapter().notifyDataSetChanged();
  }
}
