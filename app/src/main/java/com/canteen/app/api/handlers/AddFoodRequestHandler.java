package com.canteen.app.api.handlers;

import com.canteen.app.api.models.requests.AddFoodRequestBody;
import com.canteen.app.api.models.requests.RegisterRequestBody;
import com.canteen.app.api.models.responses.AddFoodResponse;
import com.canteen.app.api.models.responses.RegisterResponse;

public abstract class AddFoodRequestHandler
        extends HttpRequestHandler<AddFoodRequestBody, AddFoodResponse> {
}
