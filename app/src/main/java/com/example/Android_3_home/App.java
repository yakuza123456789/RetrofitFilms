package com.example.Android_3_home;

import android.app.Application;

import com.example.Android_3_home.data.network.GhibliService;

public class App extends Application {

   public static GhibliService ghibliService;

    @Override
    public void onCreate() {
        super.onCreate();
        ghibliService = new GhibliService();
    }
}
