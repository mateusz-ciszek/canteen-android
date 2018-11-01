package com.example.microtemp.microblog.api.models.responses.body;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterResponseBody implements BaseResponseBody {
    private String message;
}
