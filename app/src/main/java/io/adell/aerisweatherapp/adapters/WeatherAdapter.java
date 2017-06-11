package io.adell.aerisweatherapp.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import io.adell.aerisweatherapp.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Adell on 6/11/2017.
 */

public class WeatherAdapter extends BaseAdapter {

  private JSONArray days;
  private Context context;
  private int units = 0;
  public static int FAHRENHEIT_UNITS = 0;
  public static int CELSIUS_UNITS = 1;

  public WeatherAdapter(Context context) {
    this.context = context;
  }

  public void setDays(JSONArray days) {
    this.days = days;
  }

  public void setUnits(int units) {
    if (this.units != units) {
      this.units = units;
      notifyDataSetChanged();
    }
  }

  @Override public int getCount() {
    if (days == null) return 0;
    return days.length();
  }

  @Override public Object getItem(int i) {
    return null;
  }

  @Override public long getItemId(int i) {
    return 0;
  }

  @Override public View getView(int i, View view, ViewGroup viewGroup) {
    try {
      if (view == null) {
        LayoutInflater inflater =
            (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.weather_day_grid_item, viewGroup, false);
      }
      JSONObject day = days.getJSONObject(i);
      AppCompatTextView date =
          (AppCompatTextView) view.findViewById(R.id.weather_day_grid_item_date);
      AppCompatTextView high =
          (AppCompatTextView) view.findViewById(R.id.weather_day_grid_item_high);
      AppCompatTextView low = (AppCompatTextView) view.findViewById(R.id.weather_day_grid_item_low);
      if (units == FAHRENHEIT_UNITS) {
        high.setText(day.getString("maxTempF") + "F");
        low.setText(day.getString("minTempF") + "F");
      } else {
        high.setText(day.getString("maxTempC") + "C");
        low.setText(day.getString("minTempC") + "C");
      }
      SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-mm-dd");
      Date dateTimeISO = null;
      try {
        dateTimeISO = originalFormat.parse(day.getString("dateTimeISO"));
        String formattedDate = new SimpleDateFormat("EEEE").format(dateTimeISO);
        date.setText(formattedDate);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return view;
  }
}
