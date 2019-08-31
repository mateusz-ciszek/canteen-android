package com.canteen.app.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItem implements Serializable {

    @SerializedName("_id")
    private String id;

    private Food food;

    private int quantity;

    private double price;

    private List<OrderItemAddition> additions;
}
