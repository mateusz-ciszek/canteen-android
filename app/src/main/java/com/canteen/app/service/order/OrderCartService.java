package com.canteen.app.service.order;

import com.canteen.app.App;
import com.canteen.app.R;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCartService {
    private static final OrderCartService ourInstance = new OrderCartService();
    private List<OrderItem> items = new ArrayList<>();
    private String currency = null;
    private List<OrderCartChangeListener> listeners = new ArrayList<>();

    public static OrderCartService getInstance() {
        return ourInstance;
    }

    public void addItem(final OrderItem item) {
        items.add(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void removeItem(final OrderItem item) {
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
        return items.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();
    }

    public List<OrderItem> getItems() {
        return this.items;
    }

    // TODO should be getting this from server on login
    public String getCurrency() {
        return currency != null ? currency : App.getContext().getString(R.string.example_currency);
    }

    public void registerOnChangeListener(OrderCartChangeListener listener) {
        this.listeners.add(listener);
    }

    public void unregisterOnChangeListener(OrderCartChangeListener listener) {
        this.listeners.remove(listener);
    }

    private void notifyListeners() {
        listeners.forEach(OrderCartChangeListener::onOrderCartChange);
    }
}
