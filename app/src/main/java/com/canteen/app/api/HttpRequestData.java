package com.canteen.app.api;

import com.canteen.app.api.models.requests.RequestBody;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HttpRequestData<T extends RequestBody> {
    private static final String BASE_ADDRESS = "https://fathomless-hollows-91988.herokuapp.com";
    private HttpRequestMethods method;
    private T requestBody;

    public String getUrl() {
        return BASE_ADDRESS + this.requestBody.getUrl();
    }
}
