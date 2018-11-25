package com.example.microtemp.microblog.activity.cart;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.microtemp.microblog.OrderCart;
import com.example.microtemp.microblog.R;

import java.util.Locale;

public class OrderCartActivity extends AppCompatActivity implements OrderCart.OnChangeListener {

    private TextView fullOrderPriceTextView;
    private TextView itemsAmountTextView;
    private RecyclerView orderItemsRecyclerView;
    private Button confirmButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cart);

        this.initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OrderCart.getInstance().unregisterOnChangeListener(this);
    }

    private void initView() {
        this.fullOrderPriceTextView = findViewById(R.id.fullOrderPriceTextView);
        this.itemsAmountTextView = findViewById(R.id.itemsAmountTextView);
        this.orderItemsRecyclerView = findViewById(R.id.orderItemsRecyclerView);
        this.confirmButton = findViewById(R.id.confirmOrderButton);
        this.cancelButton = findViewById(R.id.cancelOrderButton);

        final OrderCart orderCart = OrderCart.getInstance();
        orderCart.registerOnChangeListener(this);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderCart.clear();
                finish();
            }
        });

        this.orderItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OrderItemsListAdapter adapter
                = new OrderItemsListAdapter(OrderCart.getInstance().getItems());
        this.orderItemsRecyclerView.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Order");
            actionBar.setElevation(0);
        }

        this.onChange();
    }

    @Override
    public void onChange() {
        OrderCart orderCart = OrderCart.getInstance();

        this.fullOrderPriceTextView.setText(String.format(Locale.getDefault(),
                "Full order price: %.2f",
                orderCart.getPrice()));

        this.itemsAmountTextView.setText(String.format(Locale.getDefault(),
                "Items in cart: %d", orderCart.getCount()));

        this.orderItemsRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
