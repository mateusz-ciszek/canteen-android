package com.canteen.app.api.models.requests;

public class DeleteMenuRequestBody extends RequestBody {
    private final String id;

    public DeleteMenuRequestBody(final String id) {
        this.id = id;
    }

    @Override
    public String getUrl() {
        return "/menu/" + id;
    }
}
