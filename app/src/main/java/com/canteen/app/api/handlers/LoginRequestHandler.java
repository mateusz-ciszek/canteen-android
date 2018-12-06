package com.canteen.app.api.handlers;

import com.canteen.app.api.models.requests.LoginRequestBody;
import com.canteen.app.api.models.responses.LoginResponse;

public abstract class LoginRequestHandler extends HttpRequestHandler<LoginRequestBody, LoginResponse> {
}
