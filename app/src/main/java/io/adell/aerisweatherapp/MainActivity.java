package io.adell.aerisweatherapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import io.adell.aerisweatherapp.utilities.WeatherUtility;

public class MainActivity extends AppCompatActivity {

  private String ZIP = "11101";
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override public View onCreateView(String name, Context context, AttributeSet attrs) {
    View v = super.onCreateView(name, context, attrs);
    WeatherUtility.getForecast(context, ZIP);
    return v;
  }
}
