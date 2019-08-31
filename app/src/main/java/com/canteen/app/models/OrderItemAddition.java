package com.canteen.app.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class OrderItemAddition implements Serializable {

    @SerializedName("_id")
    private String id;

    private FoodAddition foodAddition;

    private int quantity;

    private double price;
}
