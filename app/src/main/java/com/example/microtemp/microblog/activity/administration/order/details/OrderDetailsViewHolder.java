package com.example.microtemp.microblog.activity.administration.order.details;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.models.Food;
import com.example.microtemp.microblog.models.OrderItem;
import com.example.microtemp.microblog.models.OrderItemAddition;

import java.util.List;
import java.util.Locale;

class OrderDetailsViewHolder extends RecyclerView.ViewHolder {
    private TextView foodNameTextView;
    private TextView selectedAdditionsTextView;
    private TextView foodPriceTextView;

    OrderDetailsViewHolder(ConstraintLayout itemView) {
        super(itemView);

        foodNameTextView = itemView.findViewById(R.id.foodNameTextView);
        selectedAdditionsTextView = itemView.findViewById(R.id.selectedAdditionsTextView);
        foodPriceTextView = itemView.findViewById(R.id.foodPriceTextView);
    }

    void setOrderItem(OrderItem orderItem) {
        Food food = orderItem.getFood();
        List<OrderItemAddition> additions = orderItem.getAdditions();
        foodNameTextView.setText(food.getName());
        StringBuilder sb = new StringBuilder(foodNameTextView.getContext()
                .getString(R.string.selected_additions));
        for (OrderItemAddition addition : additions) {
            sb.append(addition.getFoodAddition().getName()).append(", ");
        }
        selectedAdditionsTextView.setText(sb.substring(0, sb.length() - 2));
        foodPriceTextView.setText(String.format(Locale.getDefault(), "%.2f zł",
                orderItem.getPrice()));
    }
}
