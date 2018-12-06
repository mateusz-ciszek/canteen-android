package com.canteen.app.models;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu implements Serializable {
    private String _id;
    private String name;
    private List<Food> foods;
}
