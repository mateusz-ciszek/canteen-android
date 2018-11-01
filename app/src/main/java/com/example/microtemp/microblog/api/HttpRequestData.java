package com.example.microtemp.microblog.api;

import com.example.microtemp.microblog.api.models.RequestBody;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HttpRequestData<T extends RequestBody> {
    private static final String BASE_ADDRESS = "http://212.191.92.88:51010";
    private HttpRequestMethods method;
    private T requestBody;

    public String getUrl() {
        return BASE_ADDRESS + this.requestBody.getUrl();
    }
}
