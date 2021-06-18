package com.app.goaheadapp;

import android.app.Application;

import com.google.firebase.FirebaseApp;

import io.paperdb.Paper;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(getApplicationContext());
        FirebaseApp.initializeApp(getApplicationContext());
    }
}
