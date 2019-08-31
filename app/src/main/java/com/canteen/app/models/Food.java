package com.canteen.app.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Food implements Serializable {

    @SerializedName("_id")
    private String id;

    private String name;

    private String description;

    private double price;

    private List<FoodAddition> additions;
}
