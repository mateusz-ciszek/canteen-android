package com.canteen.app.api.handlers;

import com.canteen.app.api.models.requests.CreateOrderRequestBody;
import com.canteen.app.api.models.responses.EmptyResponse;

public abstract class CreateOrderRequestHandler
        extends HttpRequestHandler<CreateOrderRequestBody, EmptyResponse> {
}
