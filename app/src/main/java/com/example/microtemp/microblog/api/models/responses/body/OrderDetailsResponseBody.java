package com.example.microtemp.microblog.api.models.responses.body;

import com.example.microtemp.microblog.models.OrderItem;

import java.util.List;

public class OrderDetailsResponseBody implements BaseResponseBody {
    private String _id;
    private String user;
    private List<OrderItem> items;
    private double totalPrice;
    private String state;

    @Override
    public String getMessage() {
        return _id != null ? "Retrieved order: " + _id : "Something went wrong";
    }
}
