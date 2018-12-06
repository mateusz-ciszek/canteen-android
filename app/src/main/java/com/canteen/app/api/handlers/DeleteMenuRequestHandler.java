package com.canteen.app.api.handlers;

import com.canteen.app.api.models.requests.DeleteMenuRequestBody;
import com.canteen.app.api.models.responses.EmptyResponse;

public abstract class DeleteMenuRequestHandler
        extends HttpRequestHandler<DeleteMenuRequestBody, EmptyResponse> { }
