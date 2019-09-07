package com.canteen.app.activity.client.food.details;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.canteen.app.App;
import com.canteen.app.R;
import com.canteen.app.activity.client.common.ActivityWithMainOptionMenu;
import com.canteen.app.component.DaggerAppComponent;
import com.canteen.app.models.Food;
import com.canteen.app.service.ToastService;
import com.canteen.app.service.order.OrderCartService;
import com.canteen.app.service.order.OrderItem;
import com.canteen.app.service.price.PriceFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodDetailsActivity extends ActivityWithMainOptionMenu implements PriceContainer {

    private Food food;

    private FoodAdditionAdapter adapter;

    @BindView(R.id.food_description_text_view)
    TextView foodDescriptionTextView;

    @BindView(R.id.food_price_text_view)
    TextView foodPriceTextView;

    @BindView(R.id.food_additions_recycler_view)
    RecyclerView foodAdditionsRecyclerView;

    private OrderCartService orderCartService = App.getComponent().getOrderCartService();

    private PriceFormatter formatter = DaggerAppComponent.create().getPriceFormatter();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        ButterKnife.bind(this);

        retrieveFood();
        initView();
        updatePrice(0);
    }

    @OnClick(R.id.add_to_cart_button)
    void addToCartButtonHandler() {
        orderCartService.addItem(OrderItem.builder()
                .food(food)
                .additions(adapter.getSelected())
                .build());
        ToastService.make(getString(R.string.food_added_to_cart));
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(food.getName());
        }

        foodAdditionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FoodAdditionAdapter(food.getAdditions(), this);
        foodAdditionsRecyclerView.setAdapter(adapter);

        foodDescriptionTextView.setText(food.getDescription());
    }

    private void retrieveFood() {
        food = (Food) getIntent().getSerializableExtra("food");
    }

    @Override
    public void updatePrice(final double priceIncrease) {
        foodPriceTextView.setText(formatter.format(food.getPrice() + priceIncrease));
    }
}
