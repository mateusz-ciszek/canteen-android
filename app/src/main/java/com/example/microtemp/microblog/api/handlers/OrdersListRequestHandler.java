package com.example.microtemp.microblog.api.handlers;

import com.example.microtemp.microblog.api.models.requests.OrdersListRequestBody;
import com.example.microtemp.microblog.api.models.responses.OrdersListResponse;

public abstract class OrdersListRequestHandler
        extends HttpRequestHandler<OrdersListRequestBody, OrdersListResponse> {
}
