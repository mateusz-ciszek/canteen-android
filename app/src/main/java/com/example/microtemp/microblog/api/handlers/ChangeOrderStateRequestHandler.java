package com.example.microtemp.microblog.api.handlers;

import com.example.microtemp.microblog.api.models.requests.ChangeOrderStateRequestBody;
import com.example.microtemp.microblog.api.models.responses.OrderDetailsResponse;

public abstract class ChangeOrderStateRequestHandler
        extends HttpRequestHandler<ChangeOrderStateRequestBody, OrderDetailsResponse> { }
