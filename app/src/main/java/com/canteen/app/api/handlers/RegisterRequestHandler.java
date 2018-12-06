package com.canteen.app.api.handlers;

import com.canteen.app.api.models.requests.RegisterRequestBody;
import com.canteen.app.api.models.responses.RegisterResponse;

public abstract class RegisterRequestHandler
        extends HttpRequestHandler<RegisterRequestBody, RegisterResponse> {
}
