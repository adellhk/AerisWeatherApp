package io.adell.aerisweatherapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
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
