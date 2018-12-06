package com.example.microtemp.microblog.activity.administration.order.details;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.models.OrderItem;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsViewHolder> {
    private List<OrderItem> items;

    OrderDetailsAdapter(List<OrderItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public OrderDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_order_details_item, parent, false);

        return new OrderDetailsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsViewHolder holder, int position) {
        holder.setOrderItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
