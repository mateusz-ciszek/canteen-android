package com.example.microtemp.microblog.api.models.requests;

import lombok.Builder;

@Builder
public class ChangeOrderStateRequestBody extends RequestBody {
    private final transient String id;
    private String state;

    @Override
    public String getUrl() {
        return "/order/" + id;
    }
}
