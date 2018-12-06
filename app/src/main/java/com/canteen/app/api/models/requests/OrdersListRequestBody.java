package com.canteen.app.api.models.requests;

import java.util.Locale;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrdersListRequestBody extends RequestBody {
    private String state;

    @Override
    public String getUrl() {
        return String.format(Locale.getDefault(), "/order?state=%s", state);
    }
}
