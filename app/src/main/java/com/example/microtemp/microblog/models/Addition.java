package com.example.microtemp.microblog.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Addition implements Serializable {
    private String _id;
    private String name;
    private double price;
}
