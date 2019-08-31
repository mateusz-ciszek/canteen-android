package com.canteen.app.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu implements Serializable {

    @SerializedName("_id")
    private String _id;

    private String name;

    private List<Food> foods;
}
