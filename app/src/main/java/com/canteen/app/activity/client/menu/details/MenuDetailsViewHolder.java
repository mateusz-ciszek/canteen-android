package com.canteen.app.activity.client.menu.details;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.canteen.app.App;
import com.canteen.app.R;
import com.canteen.app.activity.client.food.details.FoodDetailsActivity;
import com.canteen.app.models.Food;
import com.canteen.app.service.order.OrderCartService;
import com.canteen.app.service.order.OrderItem;

import java.util.Locale;

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
        Toast.makeText(App.getContext(),
                "Food added to cart",
                Toast.LENGTH_SHORT).show();
    }

    void setFood(Food food) {
        this.food = food;
        this.name.setText(food.getName());
        this.price.setText(String.format(Locale.getDefault(), "%.2f zł", food.getPrice()));
    }
}