package com.example.microtemp.microblog;

import com.example.microtemp.microblog.models.Food;
import com.example.microtemp.microblog.models.FoodAddition;

import java.util.ArrayList;
import java.util.List;

public class OrderCart {
    private static final OrderCart ourInstance = new OrderCart();
    private List<OrderItem> items = new ArrayList<>();

    public static OrderCart getInstance() {
        return ourInstance;
    }

    private OrderCart() { }

    public void addItem(Food food, List<FoodAddition> additions) {
        this.items.add(new OrderItem(food, additions));
    }

    public void addItem(Food food) {
        this.addItem(food, null);
    }

    public void removeItem(OrderItem item) {
        this.items.remove(item);
    }

    public void clear() {
        this.items.clear();
    }

    public int getCount() {
        return this.items.size();
    }

    public double getPrice() {
        double price = 0.0;
        for (OrderItem item : this.items) {
            price += item.getPrice();
        }
        return price;
    }

    public class OrderItem {
        private Food food;
        private List<FoodAddition> additions;

        OrderItem(Food food, List<FoodAddition> additions) {
            this.food = food;
            this.additions = additions != null ? additions : new ArrayList<FoodAddition>();
        }

        public Food getFood() {
            return food;
        }

        public List<FoodAddition> getAdditions() {
            return additions;
        }

        public double getPrice() {
            double additionsPrice = 0.0;
            for (FoodAddition addition : this.additions) {
                additionsPrice += addition.getPrice();
            }
            return this.food.getPrice() + additionsPrice;
        }
    }
}
