package com.example.microtemp.microblog.api.handlers;

import com.example.microtemp.microblog.api.models.requests.LoginRequestBody;
import com.example.microtemp.microblog.api.models.responses.LoginResponse;

public abstract class LoginRequestHandler extends HttpRequestHandler<LoginRequestBody, LoginResponse> {
}
