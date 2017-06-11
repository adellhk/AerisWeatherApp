package io.adell.aerisweatherapp.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import io.adell.aerisweatherapp.R;
import java.text.SimpleDateFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Adell on 6/11/2017.
 */

public class WeatherAdapter extends BaseAdapter {

  private JSONArray days;
  private Context context;

  public WeatherAdapter(Context context) {
    this.context = context;
  }

  public void setDays(JSONArray days) {
    this.days = days;
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
    LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    if (view == null) {
      try {
        JSONObject day = days.getJSONObject(i);
        view = inflater.inflate(R.layout.weather_day_grid_item, viewGroup, false);
        AppCompatTextView date =
            (AppCompatTextView) view.findViewById(R.id.weather_day_grid_item_date);
        AppCompatTextView high =
            (AppCompatTextView) view.findViewById(R.id.weather_day_grid_item_high);
        AppCompatTextView low =
            (AppCompatTextView) view.findViewById(R.id.weather_day_grid_item_low);
        high.setText(day.getString("maxTempF"));
        low.setText(day.getString("minTempF"));
        //String formattedDate = new SimpleDateFormat("MMM dd,yyyy").format(day.getString("dateTimeISO"));
        //date.setText(formattedDate);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    } return view;
  }
}
