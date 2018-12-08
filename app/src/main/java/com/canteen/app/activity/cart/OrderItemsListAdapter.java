package com.canteen.app.activity.cart;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.canteen.app.OrderCart;
import com.canteen.app.R;

import java.util.List;

public class OrderItemsListAdapter extends RecyclerView.Adapter<OrderItemsListViewHolder> {
    private List<OrderCart.OrderItem> items;

    OrderItemsListAdapter(List<OrderCart.OrderItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public OrderItemsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_order_item, parent, false);

        return new OrderItemsListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemsListViewHolder holder, int position) {
        holder.setOrderItem(this.items.get(position));
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
