package com.canteen.app.activity.cart;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.canteen.app.R;
import com.canteen.app.models.FoodAddition;
import com.canteen.app.service.order.OrderCartService;
import com.canteen.app.service.order.OrderItem;

import java.util.Locale;

class OrderItemsListViewHolder extends RecyclerView.ViewHolder {
    private OrderItem orderItem;
    private TextView foodNameTextView;
    private TextView foodPriceTextView;
    private TextView selectedAdditionsTextView;
    private ImageButton removeItemButton;

    OrderItemsListViewHolder(ConstraintLayout itemView) {
        super(itemView);

        this.foodNameTextView = itemView.findViewById(R.id.foodNameTextView);
        this.foodPriceTextView = itemView.findViewById(R.id.foodPriceTextView);
        this.selectedAdditionsTextView = itemView.findViewById(R.id.selectedAdditionsTextView);
        this.removeItemButton = itemView.findViewById(R.id.removeItemButton);
    }

    void setOrderItem(final OrderItem orderItem) {
        this.orderItem = orderItem;

        this.foodNameTextView.setText(this.orderItem.getFood().getName());
        this.foodPriceTextView.setText(String.format(Locale.getDefault(), "%.2f zł", this.orderItem.getPrice()));

        StringBuilder selectedItemsText;
        if (this.orderItem.getAdditions().size() == 0) {
            selectedItemsText = new StringBuilder("No additions");
        } else {
            selectedItemsText = new StringBuilder("Selected additions: ");
            for (FoodAddition addition : this.orderItem.getAdditions()) {
                selectedItemsText.append(addition.getName()).append(", ");
            }
            selectedItemsText.substring(0, selectedItemsText.length() - 2);
        }
        this.selectedAdditionsTextView.setText(selectedItemsText.toString());

        this.removeItemButton.setOnClickListener(v -> OrderCartService.getInstance().removeItem(orderItem));
    }
}
