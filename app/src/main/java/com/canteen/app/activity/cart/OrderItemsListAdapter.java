package com.canteen.app.activity.cart;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.canteen.app.R;
import com.canteen.app.service.order.OrderItem;

import java.util.List;

public class OrderItemsListAdapter extends RecyclerView.Adapter<OrderItemsListViewHolder> {
    private List<OrderItem> items;

    OrderItemsListAdapter(final List<OrderItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public OrderItemsListViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_order_item, parent, false);

        return new OrderItemsListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderItemsListViewHolder holder, final int position) {
        holder.setOrderItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
