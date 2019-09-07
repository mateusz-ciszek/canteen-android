package com.canteen.app.component.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.canteen.app.App;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonModule {

    @Provides
    Context provideContext() {
        return App.getContext();
    }

    @Provides
    SharedPreferences provideSharedPreferences(final Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
