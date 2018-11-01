package com.example.microtemp.microblog.api.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterRequestBody extends RequestBody {
    private String email;
    private String password;

    @Override
    public String getUrl() {
        return "/user/signup";
    }
}
