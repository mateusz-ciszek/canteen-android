package com.canteen.app.activity.cart;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.canteen.app.R;
import com.canteen.app.service.order.OrderCartService;
import com.canteen.app.service.order.OrderItem;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

class OrderItemsListViewHolder extends RecyclerView.ViewHolder {

    private OrderItem orderItem;

    @BindView(R.id.food_name_text_view)
    TextView foodNameTextView;

    @BindView(R.id.food_price_text_view)
    TextView foodPriceTextView;

    @BindView(R.id.selected_additions_text_view)
    TextView selectedAdditionsTextView;

    OrderItemsListViewHolder(ConstraintLayout itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.removeItemButton)
    void removeItemButtonHandler() {
        OrderCartService.getInstance().removeItem(orderItem);
    }

    void setOrderItem(final OrderItem orderItem) {
        this.orderItem = orderItem;
        foodNameTextView.setText(orderItem.getFood().getName());
        foodPriceTextView.setText(String.format(Locale.getDefault(), "%.2f zł", orderItem.getPrice()));

        StringBuilder selectedItemsText;
        if (orderItem.getAdditions().size() == 0) {
            selectedItemsText = new StringBuilder("No additions");
        } else {
            selectedItemsText = new StringBuilder("Selected additions: ");
            orderItem.getAdditions().forEach(addition -> selectedItemsText.append(addition.getName()).append(", "));
            selectedItemsText.substring(0, selectedItemsText.length() - 2);
        }
        selectedAdditionsTextView.setText(selectedItemsText.toString());
    }
}
