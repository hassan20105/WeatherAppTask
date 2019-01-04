package com.example.userr.photoweatherapp.Model;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.example.userr.photoweatherapp.R;


public class Dailogs {

    public static Dialog createLoadingBar(Context context) {

        Dialog loadingBar = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        loadingBar.setCancelable(false);
        loadingBar.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = LayoutInflater.from(context);
        View loadingView = inflater.inflate(R.layout.dialog_loading_bar, null);
        loadingBar.setContentView(loadingView);
        loadingBar.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#00ffffff")));
        return loadingBar;
    }

}
