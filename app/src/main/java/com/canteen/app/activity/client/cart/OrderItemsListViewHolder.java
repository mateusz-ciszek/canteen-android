package com.canteen.app.activity.client.cart;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.canteen.app.App;
import com.canteen.app.R;
import com.canteen.app.component.AppComponent;
import com.canteen.app.models.FoodAddition;
import com.canteen.app.service.order.OrderCartService;
import com.canteen.app.service.order.OrderItem;
import com.canteen.app.service.order.item.summary.FoodAdditionName;
import com.canteen.app.service.order.item.summary.OrderItemSummaryGenerator;
import com.canteen.app.service.price.PriceFormatter;

import java.util.List;
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

    private OrderCartService orderCartService;

    private PriceFormatter formatter;

    private OrderItemSummaryGenerator summaryGenerator;

    OrderItemsListViewHolder(ConstraintLayout itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        init();
    }

    private void init() {
        AppComponent component = App.getComponent();
        orderCartService = component.getOrderCartService();
        formatter = component.getPriceFormatter();
        summaryGenerator = component.getOrderItemSummaryGenerator();
    }

    @OnClick(R.id.removeItemButton)
    void removeItemButtonHandler() {
        orderCartService.removeItem(orderItem);
    }

    void setOrderItem(final OrderItem orderItem) {
        this.orderItem = orderItem;
        foodNameTextView.setText(orderItem.getFood().getName());
        foodPriceTextView.setText(formatter.format(orderItem.getPrice()));

        List<FoodAdditionName> names = orderItem.getAdditions().stream()
                .map(FoodAddition::getName)
                .map(FoodAdditionName::of)
                .collect(Collectors.toList());
        String label = summaryGenerator.generate(names);
        selectedAdditionsTextView.setText(label);
    }
}
