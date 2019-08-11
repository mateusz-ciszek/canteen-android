package com.canteen.app.activity.cart;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.canteen.app.service.order.OrderCartChangeListener;
import com.canteen.app.service.order.OrderCartService;
import com.canteen.app.R;
import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.CreateOrderRequestHandler;
import com.canteen.app.api.models.requests.CreateOrderRequestBody;
import com.canteen.app.api.models.responses.EmptyResponse;

import java.util.Locale;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderCartActivity extends AppCompatActivity implements OrderCartChangeListener {

    @BindView(R.id.fullOrderPriceTextView)
    TextView fullOrderPriceTextView;

    @BindView(R.id.itemsAmountTextView)
    TextView itemsAmountTextView;

    @BindView(R.id.orderItemsRecyclerView)
    RecyclerView orderItemsRecyclerView;

    @BindView(R.id.confirmOrderButton)
    Button confirmButton;

    @BindView(R.id.cancelOrderButton)
    Button cancelButton;

    private OrderCartService orderCartService = OrderCartService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cart);
        ButterKnife.bind(this);
        this.initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OrderCartService.getInstance().unregisterOnChangeListener(this);
    }

    private void initView() {
        orderCartService.registerOnChangeListener(this);

        confirmButton.setEnabled(!orderCartService.isEmpty());
        cancelButton.setEnabled(!orderCartService.isEmpty());

        cancelButton.setOnClickListener(v -> {
            orderCartService.clear();
            finish();
        });

        confirmButton.setOnClickListener(v -> {
            CreateOrderRequestBody requestBody = new CreateOrderRequestBody(orderCartService.getItems());

            HttpRequestData<CreateOrderRequestBody> requestData = HttpRequestData.<CreateOrderRequestBody>builder()
                    .method(HttpRequestMethods.POST)
                    .requestBody(requestBody)
                    .build();

            EmptyResponse response;
            try {
                response = new CreateOrderRequestHandlerImpl().execute(requestData).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                Toast.makeText(cancelButton.getContext(), getString(R.string.something_wrong),
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (response.getHttpStatusCode() == 201) {
                Toast.makeText(cancelButton.getContext(),
                        getString(R.string.order_cart_submitted_order,
                                // TODO replace with actual waiting time once implemented
                                getString(R.string.example_waiting_time)),
                        Toast.LENGTH_SHORT).show();
                orderCartService.clear();
                finish();
            } else {
                Toast.makeText(cancelButton.getContext(),
                        getString(R.string.order_cart_submitting_error),
                        Toast.LENGTH_SHORT).show();
            }
        });

        this.orderItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OrderItemsListAdapter adapter = new OrderItemsListAdapter(orderCartService.getItems());
        this.orderItemsRecyclerView.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Order");
            actionBar.setElevation(0);
        }

        this.onOrderCartChange();
    }

    @Override
    public void onOrderCartChange() {
        this.fullOrderPriceTextView.setText(String.format(Locale.getDefault(), "%.2f %s", orderCartService.getPrice(),
                orderCartService.getCurrency()));

        this.itemsAmountTextView.setText(String.format(Locale.getDefault(), "%d", orderCartService.getCount()));

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
