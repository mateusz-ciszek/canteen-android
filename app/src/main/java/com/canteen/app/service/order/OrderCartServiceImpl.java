package com.canteen.app.service.order;

import com.canteen.app.App;
import com.canteen.app.R;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class OrderCartServiceImpl implements OrderCartService {

    private final List<OrderItem> items = new ArrayList<>();

    private final List<OrderCartChangeListener> listeners = new ArrayList<>();

    // FIXME: remove or move to specialized provider
    private final String currency = null;

    @Override
    public void addItem(final OrderItem item) {
        items.add(item);
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public void removeItem(final OrderItem item) {
        this.items.remove(item);
        this.notifyListeners();
    }

    @Override
    public void clear() {
        this.items.clear();
        this.notifyListeners();
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public double getPrice() {
        return items.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();
    }

    @Override
    public List<OrderItem> getItems() {
        return this.items;
    }

    // TODO: should be getting this from server on login
    @Override
    public String getCurrency() {
        return currency != null ? currency : App.getContext().getString(R.string.example_currency);
    }

    @Override
    public void registerOnChangeListener(OrderCartChangeListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void unregisterOnChangeListener(OrderCartChangeListener listener) {
        this.listeners.remove(listener);
    }

    private void notifyListeners() {
        listeners.forEach(OrderCartChangeListener::onOrderCartChange);
    }
}
