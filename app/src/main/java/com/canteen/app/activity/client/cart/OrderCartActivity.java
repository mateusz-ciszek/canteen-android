package com.canteen.app.activity.client.cart;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.canteen.app.App;
import com.canteen.app.R;
import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.CreateOrderRequestHandler;
import com.canteen.app.api.models.requests.CreateOrderRequestBody;
import com.canteen.app.api.models.responses.EmptyResponse;
import com.canteen.app.component.DaggerAppComponent;
import com.canteen.app.service.ToastService;
import com.canteen.app.service.order.OrderCartChangeListener;
import com.canteen.app.service.order.OrderCartService;
import com.canteen.app.service.price.PriceFormatter;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private OrderCartService orderCartService = App.getComponent().getOrderCartService();

    private PriceFormatter formatter = DaggerAppComponent.create().getPriceFormatter();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cart);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        orderCartService.unregisterOnChangeListener(this);
    }

    @OnClick(R.id.cancelOrderButton)
    void cancelButtonHandler() {
        orderCartService.clear();
        finish();
    }

    @OnClick(R.id.confirmOrderButton)
    void confirmButtonHandler() {
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
            ToastService.make(getString(R.string.something_wrong));
            return;
        }

        if (response.isSuccessful()) {
            ToastService.make(getString(R.string.order_cart_submitted_order,
                    // TODO replace with actual waiting time once implemented
                    getString(R.string.example_waiting_time)));
            orderCartService.clear();
            finish();
        } else {
            ToastService.make(getString(R.string.order_cart_submitting_error));
        }
    }

    private void initView() {
        orderCartService.registerOnChangeListener(this);

        resolveButtonsState();

        orderItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OrderItemsListAdapter adapter = new OrderItemsListAdapter(orderCartService.getItems());
        orderItemsRecyclerView.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.order_cart_title);
            actionBar.setElevation(0);
        }

        onOrderCartChange();
    }

    private void resolveButtonsState() {
        confirmButton.setEnabled(!orderCartService.isEmpty());
        cancelButton.setEnabled(!orderCartService.isEmpty());
    }

    @Override
    public void onOrderCartChange() {
        fullOrderPriceTextView.setText(formatter.format(orderCartService.getPrice(), orderCartService.getCurrency()));

        itemsAmountTextView.setText(String.valueOf(orderCartService.getCount()));

        RecyclerView.Adapter adapter = orderItemsRecyclerView.getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

        resolveButtonsState();
    }

    private static class CreateOrderRequestHandlerImpl extends CreateOrderRequestHandler {
        @Override
        protected void onPostExecute(EmptyResponse result) { }
    }
}
