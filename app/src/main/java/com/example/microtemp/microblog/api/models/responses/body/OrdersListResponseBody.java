package com.example.microtemp.microblog.api.models.responses.body;

import com.example.microtemp.microblog.models.Order;

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
