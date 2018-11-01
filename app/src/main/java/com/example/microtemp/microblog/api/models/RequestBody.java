package com.example.microtemp.microblog.api.models;

import com.google.gson.Gson;

public abstract class RequestBody {
    @Override
    public final String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public abstract String getUrl();
}
