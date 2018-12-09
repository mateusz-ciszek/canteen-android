package com.canteen.app.activity.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.canteen.app.R;
import com.canteen.app.activity.cart.OrderCartActivity;
import com.canteen.app.models.Menu;

public class MenuActivity extends AppCompatActivity {

    private Menu menu;
    private RecyclerView foodsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.initRecyclerView();

        this.retrieveMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                startActivity(new Intent(this, OrderCartActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initRecyclerView() {
        this.foodsRecyclerView = findViewById(R.id.foodsRecyclerView);
        this.foodsRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        this.foodsRecyclerView.setLayoutManager(layoutManager);
    }

    private void retrieveMenu() {
        Intent intent = getIntent();
        this.menu = (Menu) intent.getSerializableExtra("menu");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(this.menu.getName());
        }

        MenuAdapter adapter = new MenuAdapter(this.menu.getFoods());
        this.foodsRecyclerView.setAdapter(adapter);
    }
}
