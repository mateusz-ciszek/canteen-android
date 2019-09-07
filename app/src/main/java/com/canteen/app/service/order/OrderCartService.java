package com.canteen.app.service.order;

import java.util.List;

public interface OrderCartService {

    void addItem(OrderItem item);

    void removeItem(OrderItem item);

    List<OrderItem> getItems();

    void clear();

    boolean isEmpty();

    int getCount();

    double getPrice();

    String getCurrency();

    void registerOnChangeListener(OrderCartChangeListener listener);

    void unregisterOnChangeListener(OrderCartChangeListener listener);
}
