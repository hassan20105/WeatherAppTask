package com.example.userr.photoweatherapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.userr.photoweatherapp.R;
import com.example.userr.photoweatherapp.Adapters.RequestWeather;

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
                startActivity(new Intent(this,HistoryActivity.class));
            break;
            case R.id.add_weather_menu_item:
                startActivity(new Intent(this,NewWeatherActivity.class));
                break;
                }
        return super.onOptionsItemSelected(item);
    }
}
