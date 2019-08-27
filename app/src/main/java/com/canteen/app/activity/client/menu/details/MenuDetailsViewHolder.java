package com.canteen.app.activity.client.menu.details;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.canteen.app.R;
import com.canteen.app.activity.client.food.details.FoodDetailsActivity;
import com.canteen.app.models.Food;
import com.canteen.app.service.ToastService;
import com.canteen.app.service.order.OrderCartService;
import com.canteen.app.service.order.OrderItem;
import com.canteen.app.service.price.PriceFormatter;
import com.canteen.app.service.price.PriceFormatterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

class MenuDetailsViewHolder extends RecyclerView.ViewHolder {

    private Food food;

    @BindView(R.id.food_name_text_view)
    TextView name;

    @BindView(R.id.food_price_text_view)
    TextView price;

    MenuDetailsViewHolder(final ConstraintLayout itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(itemView.getContext(), FoodDetailsActivity.class);
            intent.putExtra("food", food);
            itemView.getContext().startActivity(intent);
        });
    }

    @OnClick(R.id.add_to_cart_button)
    void addToCartButtonHandler() {
        OrderCartService.getInstance().addItem(OrderItem.builder()
                .food(food)
                .build());
        ToastService.make(name.getContext().getString(R.string.food_added_to_cart));
    }

    void setFood(Food food) {
        this.food = food;
        this.name.setText(food.getName());

        PriceFormatter formatter = PriceFormatterImpl.of();
        this.price.setText(formatter.format(food.getPrice()));
    }
}