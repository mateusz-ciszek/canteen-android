package com.canteen.app.service;

import android.content.Context;
import android.widget.Toast;

import com.canteen.app.App;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ToastService {

    public static void make(final String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private static Context getContext() {
        return App.getContext();
    }
}
