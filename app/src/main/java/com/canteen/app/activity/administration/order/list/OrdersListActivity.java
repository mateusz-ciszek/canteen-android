package com.canteen.app.activity.administration.order.list;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.canteen.app.R;
import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.OrdersListRequestHandler;
import com.canteen.app.api.models.requests.OrdersListRequestBody;
import com.canteen.app.api.models.responses.OrdersListResponse;
import com.canteen.app.models.Order;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class OrdersListActivity extends AppCompatActivity {
    private RecyclerView ordersRecyclerView;
    private TextView noOrdersTextView;

    private String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);

        state = getIntent().getStringExtra("state");

        initView();

        fillRecyclerView();
    }

    private void fillRecyclerView() {
        OrdersListResponse response = makeRequest();
        if (response == null) {
            hideRecyclerView();
            return;
        }

        List<Order> orders = response.getData().getOrders();
        if (orders.isEmpty()) {
            hideRecyclerView();
            return;
        }

        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(noOrdersTextView.getContext()));
        OrdersListAdapter adapter = new OrdersListAdapter(orders);
        ordersRecyclerView.setAdapter(adapter);
    }

    private OrdersListResponse makeRequest() {
        OrdersListRequestBody requestBody = OrdersListRequestBody.builder().state(state).build();
        HttpRequestData<OrdersListRequestBody> requestData = HttpRequestData.<OrdersListRequestBody>builder()
                .method(HttpRequestMethods.GET)
                .requestBody(requestBody).build();
        AsyncTask requestHandler = new OrdersListRequestHandlerImpl().execute(requestData);

        try {
            return (OrdersListResponse) requestHandler.get();
        } catch (InterruptedException | ExecutionException e) {
            hideRecyclerView();
            return null;
        }
    }

    private void hideRecyclerView() {
        ordersRecyclerView.setVisibility(View.GONE);
        noOrdersTextView.setVisibility(View.VISIBLE);
    }

    private void initView() {
        ordersRecyclerView = findViewById(R.id.ordersRecyclerView);
        noOrdersTextView = findViewById(R.id.noOrdersTextView);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(capitalize(state) + " orders");
        }
    }

    private String capitalize(String text) {
        text = text.toLowerCase();
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    public class OrderState {
        public static final String SAVED = "SAVED";
        public static final String READY = "READY";
        public static final String SERVED = "SERVED";
    }

    private static class OrdersListRequestHandlerImpl extends OrdersListRequestHandler {

        @Override
        protected void onPostExecute(OrdersListResponse result) { }
    }
}
