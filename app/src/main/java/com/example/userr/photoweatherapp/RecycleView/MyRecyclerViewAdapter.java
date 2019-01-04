package com.example.userr.photoweatherapp.RecycleView;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userr.photoweatherapp.Model.WeatherItems;
import com.example.userr.photoweatherapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    Context context;
    private List<WeatherItems> mData;
    private LayoutInflater mInflater;

    public MyRecyclerViewAdapter(Context context, List<WeatherItems> mData) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.weather_items_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WeatherItems weatherItems = mData.get(position);
        holder.placeTV.setText(weatherItems.getPlaceName());
        holder.descTV.setText(weatherItems.getDescription());
        holder.tempTV.setText(weatherItems.getTemperature());
        holder.windSpeedTV.setText(weatherItems.getWindSpeed());
        holder.imageView.setImageResource(weatherItems.getImg());
        final int[] count = {0};
        holder.favourite_imageview.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                count[0]++;
                if (count[0] % 2 != 0) {
                    holder.favourite_imageview.setImageResource(R.drawable.star2);
                    String place = weatherItems.getPlaceName();
                    String desc = weatherItems.getDescription();
                    String temp = weatherItems.getTemperature();
                    String wind = weatherItems.getWindSpeed();
                    String text_to_intenal = place + "|" + desc + "|" + temp + "|" + wind ;
                    StringBuilder stringBuilder = new StringBuilder();
                    FileOutputStream fileOutputStream = null;
                    File file = null;
                    try {
                        File directory = context.getFilesDir();
                        file = new File(directory, "mydata");
                        fileOutputStream = context.openFileOutput(file.getName(), Context.MODE_APPEND);
                        stringBuilder.append(text_to_intenal);
                        PrintWriter writer = new PrintWriter(new OutputStreamWriter(fileOutputStream));
                        Toast.makeText(context, "Data Saved Successfully" + file, Toast.LENGTH_SHORT).show();
                        writer.println(stringBuilder);
                        writer.close();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        try {
                            fileOutputStream.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        try {
                            fileOutputStream.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } finally {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }

            }
        });


    }


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tempTV, descTV, placeTV, windSpeedTV;
        ImageView imageView, favourite_imageview;
        CardView carview_weather_items;

        public ViewHolder(final View itemView) {
            super(itemView);
            tempTV = itemView.findViewById(R.id.tempTV);
            descTV = itemView.findViewById(R.id.descTV);
            placeTV = itemView.findViewById(R.id.placeTV);
            windSpeedTV = itemView.findViewById(R.id.windTV);
            imageView = itemView.findViewById(R.id.imageview);
            favourite_imageview = itemView.findViewById(R.id.favourite_imageview);
            carview_weather_items = itemView.findViewById(R.id.carview_weather_items);
            //------------------------------------------------------------------
            carview_weather_items.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Done.......", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }
}


