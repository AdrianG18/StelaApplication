package com.example.adrian.stelaapplication;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by parkerandrews on 3/1/18.
 */

public class StelaApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(new FlowConfig.Builder(this).build());
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);

        StelaApp.context = this;
    }

//    public static StelaClient getRestClient() {
//        return (StelaClient) com.example.adrian.stelaapplication.StelaClient.getInstance(StelaClient.class, StelaApp.context);
//    }
}
