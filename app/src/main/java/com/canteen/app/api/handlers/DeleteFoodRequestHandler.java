package com.canteen.app.api.handlers;

import com.canteen.app.api.models.requests.DeleteFoodRequestBody;
import com.canteen.app.api.models.responses.EmptyResponse;

public abstract class DeleteFoodRequestHandler
        extends HttpRequestHandler<DeleteFoodRequestBody, EmptyResponse> { }
