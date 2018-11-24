package com.example.microtemp.microblog.models;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Food implements Serializable {
    private String _id;
    private String name;
    private String description;
    private double price;
    private List<Addition> additions;
}
