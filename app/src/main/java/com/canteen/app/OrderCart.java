package com.canteen.app;

import com.canteen.app.models.Food;
import com.canteen.app.models.FoodAddition;

import java.util.ArrayList;
import java.util.List;

public class OrderCart {
    private static final OrderCart ourInstance = new OrderCart();
    private List<OrderItem> items = new ArrayList<>();
    private List<OnChangeListener> listeners = new ArrayList<>();
    private String currency = null;

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

    public boolean isEmpty() {
        return items.isEmpty();
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

    // TODO should be getting this from server on login
    public String getCurrency() {
        return currency != null ? currency : App.getContext().getString(R.string.example_currency);
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
            this.additions = additions != null ? additions : new ArrayList<>();
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
