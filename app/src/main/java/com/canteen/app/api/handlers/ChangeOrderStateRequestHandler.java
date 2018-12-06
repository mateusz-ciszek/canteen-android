package com.canteen.app.api.handlers;

import com.canteen.app.api.models.requests.ChangeOrderStateRequestBody;
import com.canteen.app.api.models.responses.OrderDetailsResponse;

public abstract class ChangeOrderStateRequestHandler
        extends HttpRequestHandler<ChangeOrderStateRequestBody, OrderDetailsResponse> { }
