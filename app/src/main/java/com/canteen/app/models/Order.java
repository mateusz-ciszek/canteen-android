package com.canteen.app.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;

@Getter
public class Order implements Serializable {

    @SerializedName("_id")
    private String id;

    private User user;

    private List<OrderItem> items;

    private double totalPrice;

    private String state;
}
