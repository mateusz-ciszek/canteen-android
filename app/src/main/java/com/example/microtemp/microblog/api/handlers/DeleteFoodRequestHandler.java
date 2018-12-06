package com.example.microtemp.microblog.api.handlers;

import com.example.microtemp.microblog.api.models.requests.DeleteFoodRequestBody;
import com.example.microtemp.microblog.api.models.responses.EmptyResponse;

public abstract class DeleteFoodRequestHandler
        extends HttpRequestHandler<DeleteFoodRequestBody, EmptyResponse> { }
