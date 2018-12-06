package com.example.microtemp.microblog.api.models.requests;

public class DeleteFoodRequestBody extends RequestBody {
    private final String id;

    public DeleteFoodRequestBody(final String id) {
        this.id = id;
    }

    @Override
    public String getUrl() {
        return "/food/" + id;
    }
}
