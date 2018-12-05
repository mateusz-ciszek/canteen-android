package com.example.microtemp.microblog.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItem {
    private String _id;
    private Food food;
    private int quantity;
    private double price;
    private List<OrderItemAddition> additions;
}
