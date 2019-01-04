package com.example.userr.photoweatherapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.userr.photoweatherapp.Model.Helper;
import com.example.userr.photoweatherapp.Model.WeatherItems;
import com.example.userr.photoweatherapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestWeather {

    public static void requestWeather(final RecyclerView listRecyclerView, final Context context) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(context);
        final List weatherItemsList = new ArrayList<WeatherItems>();

        final MyRecyclerViewAdapter[] recyclerViewAdapter = {new MyRecyclerViewAdapter(context, weatherItemsList)};

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Helper.Base_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("list");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject lists = jsonArray.getJSONObject(i);
                                JSONObject main = lists.getJSONObject("main");
                                JSONObject wind = lists.getJSONObject("wind");
                                JSONArray weather = lists.getJSONArray("weather");
                                JSONObject weatherObj = weather.getJSONObject(0);
                                String temp = main.getString("temp");
                                String placeName = lists.getString("name");
                                String windSpeed = wind.getString("speed");
                                String description = weatherObj.getString("description");
                                //weatherItemsList.add(new WeatherItems(,getImage(description), placeName, temp + " C", description, windSpeed));
                                weatherItemsList.add(new WeatherItems(getImage(description),R.drawable.star1,placeName,temp,description,windSpeed));
                            }
                            Log.e("Success", jsonArray.length() + "");

                            recyclerViewAdapter[0] = new MyRecyclerViewAdapter(context, weatherItemsList);
                            listRecyclerView.setAdapter(recyclerViewAdapter[0]);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("ERROR", e.getStackTrace() + " " + e.getMessage());
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    public static int getImage(String description) {
        int img = 0;
        if (description.contains("scattered"))
            img = R.drawable.clear;
        else if (description.contains("overcast"))
            img = R.drawable.raincloud;
        else if (description.contains("rain"))
            img = R.drawable.winter;
        else if(description.contains("broken")||description.contains("few"))
            img = R.drawable.summer;
        else img = -1;

        return img;
    }


}
