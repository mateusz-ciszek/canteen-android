package com.example.microtemp.microblog.activity.administration.order.list;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.microtemp.microblog.App;
import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.activity.administration.order.details.OrderDetailsActivity;
import com.example.microtemp.microblog.models.Order;
import com.example.microtemp.microblog.models.OrderItem;

import java.util.Locale;

class OrdersListViewHolder extends RecyclerView.ViewHolder {
    private TextView orderNameTextView;
    private TextView orderDescriptionTextView;
    private TextView priceTextView;
    private ConstraintLayout layout;

    OrdersListViewHolder(ConstraintLayout itemView) {
        super(itemView);

        orderNameTextView = itemView.findViewById(R.id.orderNameTextView);
        orderDescriptionTextView = itemView.findViewById(R.id.orderDescriptionTextView);
        priceTextView = itemView.findViewById(R.id.priceTextView);
        layout = itemView;
    }

    void setOrder(final Order order) {
        // FIXME podmienić na faktyczne imię i nazwisko jak już to będzie przekazywane
        orderNameTextView.setText(String.format("%s <userName>",
                App.getContext().getString(R.string.order_for)));

        StringBuilder sb = new StringBuilder();
        for (OrderItem food : order.getItems()) {
            sb.append(food.getFood().getName()).append(", ");
        }
        orderDescriptionTextView.setText(sb.substring(0, sb.length() - 2));

        priceTextView.setText(String.format(Locale.getDefault(), "%.2f zł", order.getTotalPrice()));

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(layout.getContext(), OrderDetailsActivity.class);
                intent.putExtra("order", order);
                layout.getContext().startActivity(intent);
            }
        });
    }

    public ConstraintLayout getLayout() {
        return layout;
    }
}
