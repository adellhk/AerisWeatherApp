package io.adell.aerisweatherapp.utilities;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Adell on 6/11/2017.
 */

public class WeatherUtility {
  private static String clientId = "BayMxU3cAiWqpt97jHhhL";
  private static String clientSecret = "yjoCIjdwKcZRC4NZEhL7p8s12DpGRl3SzroYfytm";
  private static String aerisUrl = "http://api.aerisapi.com/";

  public static void getForecast(Context context, String zip) {
    JsonObjectRequest forecastRequest =
        new JsonObjectRequest(Request.Method.GET, forecastsUrl(zip), null,
            new Response.Listener<JSONObject>() {
              @Override public void onResponse(JSONObject response) {
                parseWeekForecast(response);
                Log.d("WeatherUtility", "onResponse: " + response);

              }
            }, new Response.ErrorListener() {
          @Override public void onErrorResponse(VolleyError error) {
            Log.d("WeatherUtility", "onErrorResponse: " + error);
          }
        });

    RequestQueue queue = Volley.newRequestQueue(context);
    queue.add(forecastRequest);
  }

  private static JSONArray parseWeekForecast(JSONObject response) {
    try {
      JSONObject forecast = response.getJSONArray("response").getJSONObject(0);
      JSONArray days = forecast.getJSONArray("periods");
      return days;
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static String forecastsUrl(String zip) {
    return aerisUrl
        + "forecasts/"
        + zip
        + "?client_id="
        + clientId
        + "&client_secret="
        + clientSecret;
  }
}
