package com.example.microtemp.microblog.activity.administration.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.microtemp.microblog.App;
import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.activity.administration.menu.list.MenusListManagementActivity;
import com.example.microtemp.microblog.activity.administration.order.list.OrdersListActivity;

public class AdminDashboardActivity extends AppCompatActivity {
    private Button newOrdersButton;
    private Button inProgressOrdersButton;
    private Button completedOrdersButton;
    private Button manageMenusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        this.newOrdersButton = findViewById(R.id.newOrdersButton);
        this.inProgressOrdersButton = findViewById(R.id.inProgressOrdersButton);
        this.completedOrdersButton = findViewById(R.id.completedOrdersButton);
        this.manageMenusButton = findViewById(R.id.manageMenusButton);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Administration panel");
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.getContext(), OrdersListActivity.class);
                startActivity(intent);
            }
        };

        this.newOrdersButton.setOnClickListener(listener);
        this.inProgressOrdersButton.setOnClickListener(listener);
        this.completedOrdersButton.setOnClickListener(listener);
        this.manageMenusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(manageMenusButton.getContext(),
                        MenusListManagementActivity.class));
            }
        });
    }
}
