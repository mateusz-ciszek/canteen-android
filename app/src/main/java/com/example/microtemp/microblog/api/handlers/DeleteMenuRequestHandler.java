package com.example.microtemp.microblog.api.handlers;

import com.example.microtemp.microblog.api.models.requests.DeleteMenuRequestBody;
import com.example.microtemp.microblog.api.models.responses.EmptyResponse;

public abstract class DeleteMenuRequestHandler
        extends HttpRequestHandler<DeleteMenuRequestBody, EmptyResponse> { }
