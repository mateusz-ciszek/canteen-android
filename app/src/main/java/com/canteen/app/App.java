package com.canteen.app;

import android.app.Application;
import android.content.Context;

import com.canteen.app.component.AppComponent;
import com.canteen.app.component.DaggerAppComponent;

public class App extends Application {

    private static Application application;

    private static AppComponent component;

    public static Application getApplication() {
        return application;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        component = DaggerAppComponent.create();
    }
}
