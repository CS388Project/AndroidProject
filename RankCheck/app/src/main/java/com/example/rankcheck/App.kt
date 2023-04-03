package com.example.rankcheck

import android.app.Application
import com.parse.Parse;

private const val CLIENT_KEY = BuildConfig.CLIENT_KEY
private const val APP_ID = BuildConfig.APP_ID

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(APP_ID)
                .clientKey(CLIENT_KEY)
                .server(getString(R.string.back4app_server_url))
                .build());
    }
}