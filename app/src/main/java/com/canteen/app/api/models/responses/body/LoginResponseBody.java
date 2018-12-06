package com.canteen.app.api.models.responses.body;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponseBody implements BaseResponseBody {
    private String message;
    private String token;
}
