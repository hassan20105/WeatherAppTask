package com.example.userr.photoweatherapp.RecycleView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userr.photoweatherapp.Model.WeatherItems;
import com.example.userr.photoweatherapp.R;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import java.util.List;


public class HistoryRecycleViewAdapter extends RecyclerView.Adapter<HistoryRecycleViewAdapter.HistoryViewHolder>
{
    Context context;
    private List<WeatherItems> mData;
    private LayoutInflater mInflater;

    public HistoryRecycleViewAdapter(Context context, List<WeatherItems> mData) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = mData;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_item_layout, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HistoryViewHolder holder, final int position) {
        WeatherItems weatherItems = mData.get(position);
        holder.view_layout_placeTV.setText(weatherItems.getPlaceName());
        holder.view_layout_descTV.setText(weatherItems.getDescription());
        holder.view_layout_tempTV.setText(weatherItems.getTemperature());
        holder.view_layout_windTV.setText(weatherItems.getWindSpeed());
        holder.containerlayout2.setBackgroundResource(RequestWeather.getImage(weatherItems.getDescription()));
       holder.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bitmap bitmap = Bitmap.createBitmap(holder.containerlayout.getWidth(), holder.containerlayout.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                Drawable bgDrawable = holder.containerlayout.getBackground();
                if (bgDrawable != null) {
                    bgDrawable.draw(canvas);
                } else {
                    canvas.drawColor(Color.WHITE);
                }
                holder.containerlayout.draw(canvas);


               // holder.containerlayout.setDrawingCacheEnabled(false);
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(bitmap)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();


                ShareDialog.show((Activity) context,content);

            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView view_layout_tempTV,view_layout_descTV,view_layout_placeTV,view_layout_windTV;
        LinearLayout containerlayout,containerlayout2;
        Button shareButton;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            view_layout_tempTV = itemView.findViewById(R.id.view_layout_tempTV);
            view_layout_descTV = itemView.findViewById(R.id.view_layout_descTV);
            view_layout_placeTV = itemView.findViewById(R.id.view_layout_placeTV);
            view_layout_windTV = itemView.findViewById(R.id.view_layout_windTV);
            containerlayout = itemView.findViewById(R.id.containerlayout);
            containerlayout2 = itemView.findViewById(R.id.containerlayout2);
             shareButton = itemView.findViewById(R.id.share_btn);


        }
    }

}

