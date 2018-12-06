package com.example.microtemp.microblog.api.handlers;

import com.example.microtemp.microblog.api.models.requests.AddMenuRequestBody;
import com.example.microtemp.microblog.api.models.responses.AddMenuResponse;

public abstract class AddMenuRequestHandler
        extends HttpRequestHandler<AddMenuRequestBody, AddMenuResponse> {
}
