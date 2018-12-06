package com.canteen.app.api.models.responses.body;

import com.canteen.app.models.Order;

import java.util.List;

import lombok.Getter;

@Getter
public class OrdersListResponseBody implements BaseResponseBody {
    List<Order> orders;

    @Override
    public String getMessage() {
        return null;
    }
}
