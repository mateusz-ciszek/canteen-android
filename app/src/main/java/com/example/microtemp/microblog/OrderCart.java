package com.example.microtemp.microblog;

import com.example.microtemp.microblog.models.Food;
import com.example.microtemp.microblog.models.FoodAddition;

import java.util.ArrayList;
import java.util.List;

public class OrderCart {
    private static final OrderCart ourInstance = new OrderCart();
    private List<OrderItem> items = new ArrayList<>();
    private List<OnChangeListener> listeners = new ArrayList<>();

    public static OrderCart getInstance() {
        return ourInstance;
    }

    private OrderCart() { }

    public void addItem(Food food, List<FoodAddition> additions) {
        this.items.add(new OrderItem(food, additions));
        this.notifyListeners();
    }

    public void addItem(Food food) {
        this.addItem(food, null);
    }


    public void removeItem(OrderItem item) {
        this.items.remove(item);
        this.notifyListeners();
    }

    public void clear() {
        this.items.clear();
        this.notifyListeners();
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

    public List<OrderItem> getItems() {
        return this.items;
    }

    public void registerOnChangeListener(OnChangeListener listener) {
        this.listeners.add(listener);
    }

    public void unregisterOnChangeListener(OnChangeListener listener) {
        this.listeners.remove(listener);
    }

    private void notifyListeners() {
        for (OnChangeListener listener : this.listeners) {
            listener.onChange();
        }
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

    public interface OnChangeListener {
        void onChange();
    }
}
