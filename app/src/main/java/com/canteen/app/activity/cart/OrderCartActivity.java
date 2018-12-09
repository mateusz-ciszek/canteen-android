package com.canteen.app.activity.cart;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.canteen.app.OrderCart;
import com.canteen.app.R;
import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.CreateOrderRequestHandler;
import com.canteen.app.api.models.requests.CreateOrderRequestBody;
import com.canteen.app.api.models.responses.EmptyResponse;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

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

        cancelButton.setOnClickListener(v -> {
            orderCart.clear();
            finish();
        });

        confirmButton.setOnClickListener(v -> {
            CreateOrderRequestBody requestBody
                    = new CreateOrderRequestBody(orderCart.getItems());

            HttpRequestData<CreateOrderRequestBody> requestData = HttpRequestData.<CreateOrderRequestBody>builder()
                    .method(HttpRequestMethods.POST).requestBody(requestBody).build();

            EmptyResponse response;
            try {
                response = new CreateOrderRequestHandlerImpl().execute(requestData).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                Toast.makeText(cancelButton.getContext(), getString(R.string.something_wrong),
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (response.getHttpStatusCode() == 200) {
                Toast.makeText(cancelButton.getContext(),
                        getString(R.string.order_cart_submitted_order,
                                // TODO replace with actual waiting time once implemented
                                getString(R.string.example_waiting_time)),
                        Toast.LENGTH_SHORT).show();
                orderCart.clear();
                finish();
            } else {
                Toast.makeText(cancelButton.getContext(),
                        getString(R.string.order_cart_submitting_error),
                        Toast.LENGTH_SHORT).show();
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
                "%.2f %s", orderCart.getPrice(), orderCart.getCurrency()));

        this.itemsAmountTextView.setText(String.format(Locale.getDefault(),
                "%d", orderCart.getCount()));

        RecyclerView.Adapter adapter = orderItemsRecyclerView.getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private static class CreateOrderRequestHandlerImpl extends CreateOrderRequestHandler {
        @Override
        protected void onPostExecute(EmptyResponse result) { }
    }
}
