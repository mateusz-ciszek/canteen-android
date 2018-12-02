package com.example.microtemp.microblog.activity.administration.order.list;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.microtemp.microblog.App;
import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.models.Order;

import java.util.List;

public class OrdersListAdapter extends RecyclerView.Adapter<OrdersListViewHolder> {
    private List<Order> orders;

    OrdersListAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrdersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_order, parent, false);

        return new OrdersListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrdersListViewHolder holder, final int position) {
        holder.setOrder(orders.get(position));

        // FIXME wysyłanie zapytania na serwer o zmaianę statusu zamówienia
        holder.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(App.getContext(), "Order changed to ready", Toast.LENGTH_SHORT)
                        .show();
                int position = holder.getAdapterPosition();
                orders.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
