package com.example.microtemp.microblog.api.handlers;

import com.example.microtemp.microblog.api.models.requests.RegisterRequestBody;
import com.example.microtemp.microblog.api.models.responses.RegisterResponse;

public abstract class RegisterRequestHandler
        extends HttpRequestHandler<RegisterRequestBody, RegisterResponse> {
}
