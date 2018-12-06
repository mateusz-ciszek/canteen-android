package com.canteen.app.api.handlers;

import com.canteen.app.api.models.requests.AddMenuRequestBody;
import com.canteen.app.api.models.responses.AddMenuResponse;

public abstract class AddMenuRequestHandler
        extends HttpRequestHandler<AddMenuRequestBody, AddMenuResponse> {
}
