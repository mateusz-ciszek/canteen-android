package com.example.microtemp.microblog.models;

import lombok.Getter;

@Getter
public class OrderItemAddition {
    private String _id;
    private FoodAddition foodAddition;
    private int quantity;
    private double price;
}
