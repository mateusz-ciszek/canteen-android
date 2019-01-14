package com.canteen.app.models;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

@Getter
public class Order implements Serializable {
    private String _id;
    private User user;
    private List<OrderItem> items;
    private double totalPrice;
    private String state;
}
