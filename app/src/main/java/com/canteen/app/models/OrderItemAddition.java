package com.canteen.app.models;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class OrderItemAddition implements Serializable {
    private String _id;
    private FoodAddition foodAddition;
    private int quantity;
    private double price;
}
