package com.canteen.app.api.handlers;

import com.canteen.app.api.models.requests.OrdersListRequestBody;
import com.canteen.app.api.models.responses.OrdersListResponse;

public abstract class OrdersListRequestHandler
        extends HttpRequestHandler<OrdersListRequestBody, OrdersListResponse> {
}
