package com.example.applicationdatabaselearning;

import android.app.Application;
import android.content.Context;

public class DatabaselearningApplication extends Application {

    static Context applicationContext;

    public static Context getContext() {

        return applicationContext;
    }

    static ApplicationDatabaseHelper applicationDatabase;

    public static ApplicationDatabaseHelper getApplicationDatabase() {
        if (applicationDatabase == null) {
            applicationDatabase = new ApplicationDatabaseHelper(getContext());
        }
        return applicationDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationContext = this;
    }
}


