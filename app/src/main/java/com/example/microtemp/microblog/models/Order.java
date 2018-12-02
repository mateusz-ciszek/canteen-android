package com.example.microtemp.microblog.models;

import java.util.List;

public class Order {
    private String _id;
    private String user;
    private List<OrderItem> items;
    private double totalPrice;
    private String state;
}
