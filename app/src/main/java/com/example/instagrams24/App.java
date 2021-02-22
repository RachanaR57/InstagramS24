package com.example.instagrams24;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("zlEddQixRglEBxadfyrBoP4Tz8DCdeHYvwmDW9zZ")
                // if defined
                .clientKey("GFDukRIyMiYAMHceBrr56yE5b0NqzF1wO4QqFphp")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
