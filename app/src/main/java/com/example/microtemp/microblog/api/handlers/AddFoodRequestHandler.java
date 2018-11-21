package com.example.microtemp.microblog.api.handlers;

import com.example.microtemp.microblog.api.models.requests.AddFoodRequestBody;
import com.example.microtemp.microblog.api.models.requests.RegisterRequestBody;
import com.example.microtemp.microblog.api.models.responses.AddFoodResponse;
import com.example.microtemp.microblog.api.models.responses.RegisterResponse;

public abstract class AddFoodRequestHandler
        extends HttpRequestHandler<AddFoodRequestBody, AddFoodResponse> {
}
