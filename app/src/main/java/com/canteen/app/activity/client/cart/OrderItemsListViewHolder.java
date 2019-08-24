package com.canteen.app.activity.client.cart;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.canteen.app.R;
import com.canteen.app.models.FoodAddition;
import com.canteen.app.service.order.OrderCartService;
import com.canteen.app.service.order.OrderItem;
import com.canteen.app.service.order.item.summary.FoodAdditionName;
import com.canteen.app.service.order.item.summary.OrderItemSummaryUtil;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

        OrderItemSummaryUtil util = OrderItemSummaryUtil.of();
        List<FoodAdditionName> names = orderItem.getAdditions().stream()
                .map(FoodAddition::getName)
                .map(FoodAdditionName::of)
                .collect(Collectors.toList());
        String label = util.generateLabel(names);
        selectedAdditionsTextView.setText(label);
    }
}
