package com.example.microtemp.microblog.api.models.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequestBody extends RequestBody {
    private String email;
    private String password;

    @Override
    public String getUrl() {
        return "/user/login";
    }
}
