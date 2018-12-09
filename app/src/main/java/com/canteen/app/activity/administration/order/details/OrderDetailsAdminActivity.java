package com.canteen.app.activity.administration.order.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.canteen.app.R;
import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.ChangeOrderStateRequestHandler;
import com.canteen.app.api.models.requests.ChangeOrderStateRequestBody;
import com.canteen.app.api.models.responses.OrderDetailsResponse;
import com.canteen.app.models.Order;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class OrderDetailsAdminActivity extends AppCompatActivity {
    private TextView userNameTextView;
    private TextView itemsNumberTextView;
    private TextView orderStateTextView;
    private TextView priceTextView;
    private RecyclerView orderedItemsRecyclerView;
    private Button confirmButton;
    private Button rejectButton;

    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_admin);

        this.retrieveOrder();
        this.initView();
    }

    private void retrieveOrder() {
        this.order = (Order) getIntent().getSerializableExtra("order");
    }

    private void initView() {
        userNameTextView = findViewById(R.id.userNameTextView);
        itemsNumberTextView = findViewById(R.id.itemsNumberTextView);
        orderStateTextView = findViewById(R.id.orderStateTextView);
        priceTextView = findViewById(R.id.priceTextView);
        orderedItemsRecyclerView = findViewById(R.id.orderedItemsRecyclerView);
        confirmButton = findViewById(R.id.confirmButton);
        rejectButton = findViewById(R.id.rejectButton);

        this.setFields();

        OrderDetailsAdapter adater = new OrderDetailsAdapter(order.getItems());
        orderedItemsRecyclerView.setAdapter(adater);
        orderedItemsRecyclerView
                .setLayoutManager(new LinearLayoutManager(rejectButton.getContext()));

        configureButtons();
    }

    private void setFields() {
        // FIXME poprawić jak będzie zwracany pełen użytkownik
        //userNameTextView.setText(order.getUser());
        userNameTextView.setText("Jacek");
        itemsNumberTextView.setText(String.format(Locale.getDefault(),
                "%d", order.getItems().size()));
        orderStateTextView.setText(capitalize(order.getState()));
        priceTextView.setText(String.format(Locale.getDefault(), "%.2f zł", order.getTotalPrice()));
    }

    private String capitalize(String text) {
        text = text.toLowerCase();
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    private void configureButtons() {
        View.OnClickListener listener = createListener(getNextOrderState(order.getState()));
        String text = confirmButton.getText().toString();
        switch (order.getState()) {
            case "SAVED":
                text = getString(R.string.order_details_set_order_ready);
                break;
            case "READY":
                text = getString(R.string.order_details_set_order_served);
                break;
            case "SERVED":
            default:
                confirmButton.setVisibility(View.GONE);
                rejectButton.setVisibility(View.GONE);
                break;
        }
        confirmButton.setText(text);
        confirmButton.setOnClickListener(listener);
        rejectButton.setOnClickListener(createListener("REJECTED"));
    }

    private View.OnClickListener createListener(final String requestedState) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeOrderStateRequestBody requestBody = ChangeOrderStateRequestBody.builder()
                        .id(order.get_id())
                        .state(requestedState)
                        .build();

                HttpRequestData<ChangeOrderStateRequestBody> requestData = HttpRequestData
                        .<ChangeOrderStateRequestBody>builder()
                        .requestBody(requestBody)
                        .method(HttpRequestMethods.PATCH)
                        .build();

                OrderDetailsResponse response;
                try {
                    response = new ChangeOrderStateRequestHandlerImpl().execute(requestData).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return;
                }

                if (response.getHttpStatusCode() == 200) {
                    setFields();
                    Toast.makeText(rejectButton.getContext(),
                            "Order has been updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(rejectButton.getContext(), "Something went wrong",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private String getNextOrderState(String currentState) {
        Map<String, String> nextOrderState = new HashMap<>();
        nextOrderState.put("SAVED", "READY");
        nextOrderState.put("READY", "SERVED");
        nextOrderState.put("SERVED", "SERVED");
        return nextOrderState.get(currentState);
    }

    private static class ChangeOrderStateRequestHandlerImpl extends ChangeOrderStateRequestHandler {
        @Override
        protected void onPostExecute(OrderDetailsResponse result) { }
    }
}
