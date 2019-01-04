package com.example.userr.photoweatherapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.userr.photoweatherapp.Model.WeatherItems;
import com.example.userr.photoweatherapp.R;
import com.example.userr.photoweatherapp.Adapters.HistoryRecycleViewAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {
    @BindView(R.id.recycleview_actavity_view)
    RecyclerView recycleview;
    HistoryRecycleViewAdapter recyclerViewAdapter;
    List<WeatherItems> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        StringBuffer buffer = new StringBuffer();
        File directory = getFilesDir();
        File file = new File(directory, "mydata5");
        FileInputStream fIn = null;
        StringTokenizer stringTokenizer;
        String matcher = "\n";


        try {
            String data = "";
            fIn = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fIn));
            while ((data = bufferedReader.readLine()) != null) {
                stringTokenizer = new StringTokenizer(data, matcher);

                while (stringTokenizer.hasMoreTokens()) {
                    buffer.append(stringTokenizer.nextToken()).append("\n");
                }
            }
            String text = buffer.toString();
            String place = "", desc = "", temp = "", wind = "",image_link="";

            String[] line_splitter = text.split("\n");
            String[] line_parts = null;
            for (int i = 0; i < line_splitter.length; i++) {
                line_parts = line_splitter[i].split(Pattern.quote("|"));
                place += line_parts[0] + "\n";
                desc += line_parts[1] + "\n";
                temp += line_parts[2] + "\n";
                wind += line_parts[3] + "\n";
                image_link+=line_parts[4]+"\n";
            }
            String[] place_split = place.split("\n");
            String[] desc_split = desc.split("\n");
            String[] temp_split = temp.split("\n");
            String[] wind_split = wind.split("\n");
            String[] image_split = image_link.split("\n");
            items = new ArrayList<>();
            for (int i = 0; i < place_split.length; i++) {
                items.add(new WeatherItems(0, place_split[i], temp_split[i], desc_split[i], wind_split[i],image_split[i]));
                recyclerViewAdapter = new HistoryRecycleViewAdapter(this, items);
                recycleview.setAdapter(recyclerViewAdapter);
            }


            fIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
