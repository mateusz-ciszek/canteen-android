package com.canteen.app.api.models.requests;

public class AllMenusRequestBody extends RequestBody {
    @Override
    public String getUrl() {
        return "/menu";
    }
}
