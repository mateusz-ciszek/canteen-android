package com.canteen.app.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoodAddition implements Serializable {

    @SerializedName("_id")
    private String id;

    private String name;

    private double price;
}
