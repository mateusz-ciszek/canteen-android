package com.example.microtemp.microblog.activity.administration.order.list;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.api.HttpRequestData;
import com.example.microtemp.microblog.api.HttpRequestMethods;
import com.example.microtemp.microblog.api.handlers.OrdersListRequestHandler;
import com.example.microtemp.microblog.api.models.requests.OrdersListRequestBody;
import com.example.microtemp.microblog.api.models.responses.OrdersListResponse;
import com.example.microtemp.microblog.models.Order;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class OrdersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);

        RecyclerView ordersRecyclerView = findViewById(R.id.ordersRecyclerView);
        TextView noOrdersTextView = findViewById(R.id.noOrdersTextView);

        String state = getIntent().getStringExtra("state");

        OrdersListRequestBody requestBody = OrdersListRequestBody.builder().state(state).build();
        HttpRequestData<OrdersListRequestBody> requestData = HttpRequestData.<OrdersListRequestBody>builder()
                .method(HttpRequestMethods.GET)
                .requestBody(requestBody).build();
        AsyncTask requestHandler = new OrdersListRequestHandlerImpl().execute(requestData);

        OrdersListResponse response;
        try {
            response = (OrdersListResponse) requestHandler.get();
        } catch (InterruptedException | ExecutionException e) {
            ordersRecyclerView.setVisibility(View.GONE);
            noOrdersTextView.setVisibility(View.VISIBLE);
            return;
        }

        List<Order> orders = response.getData().getOrders();

        if (orders.isEmpty()) {
            ordersRecyclerView.setVisibility(View.GONE);
            noOrdersTextView.setVisibility(View.VISIBLE);
            return;
        }

        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(noOrdersTextView.getContext()));
        OrdersListAdapter adapter = new OrdersListAdapter(orders);
        ordersRecyclerView.setAdapter(adapter);
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
