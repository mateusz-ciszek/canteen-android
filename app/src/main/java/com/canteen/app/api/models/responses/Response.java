package com.canteen.app.api.models.responses;

import com.canteen.app.api.models.responses.body.BaseResponseBody;
import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import lombok.Getter;

public abstract class Response<T extends BaseResponseBody> implements Serializable {

    @Getter
    protected int httpStatusCode;

    @Getter
    protected T data;

    public final void populate(int code, String data) {
        this.httpStatusCode = code;
        Type classType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.data = new Gson().fromJson(data, classType);
    }

    public boolean isSuccessful() {
        return httpStatusCode >= 200 && httpStatusCode < 300;
    }
}

