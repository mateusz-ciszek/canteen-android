package com.example.microtemp.microblog.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {
    private String _id;
    private String name;
    private List<Food> foods;
}
