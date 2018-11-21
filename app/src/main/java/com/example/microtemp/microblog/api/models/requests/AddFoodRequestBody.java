package com.example.microtemp.microblog.api.models.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddFoodRequestBody extends RequestBody {
    private String name;
    private int price;
    private String description;


    @Override
    public String getUrl() {
        return "/menu/5bef0f8a2230ef670c779b05/food";
    }
}
