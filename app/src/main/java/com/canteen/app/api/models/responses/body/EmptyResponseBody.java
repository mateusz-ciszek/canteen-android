package com.canteen.app.api.models.responses.body;

public final class EmptyResponseBody implements BaseResponseBody {
    @Override
    public String getMessage() {
        return "";
    }
}
