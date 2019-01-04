package com.example.userr.photoweatherapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.userr.photoweatherapp.Model.Helper;
import com.example.userr.photoweatherapp.Model.WeatherItems;
import com.example.userr.photoweatherapp.R;
import com.example.userr.photoweatherapp.RecycleView.MyRecyclerViewAdapter;
import com.example.userr.photoweatherapp.RecycleView.RequestWeather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView listRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listRecyclerView = findViewById(R.id.recycle_view);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RequestWeather.requestWeather(listRecyclerView,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.history_menu_item:
                startActivity(new Intent(this,ViewActivity.class));
            break;


        }
        return super.onOptionsItemSelected(item);
    }
}
