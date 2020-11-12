package com.example.meetmeup;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;

public class MeetMeUpApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
    }
}
