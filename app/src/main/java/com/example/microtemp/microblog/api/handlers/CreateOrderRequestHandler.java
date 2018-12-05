package com.example.microtemp.microblog.api.handlers;

import com.example.microtemp.microblog.api.models.requests.CreateOrderRequestBody;
import com.example.microtemp.microblog.api.models.responses.EmptyResponse;

public abstract class CreateOrderRequestHandler
        extends HttpRequestHandler<CreateOrderRequestBody, EmptyResponse> {
}
