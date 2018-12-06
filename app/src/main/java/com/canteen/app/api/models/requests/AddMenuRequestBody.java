package com.canteen.app.api.models.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddMenuRequestBody extends RequestBody{
    private String name;


    @Override
    public String getUrl() {
        return "/menu";
    }

}
