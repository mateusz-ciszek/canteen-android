package com.example.microtemp.microblog.activity.administration.menu.list.list;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.activity.cart.OrderCartActivity;
import com.example.microtemp.microblog.deletion.AddDishActivity;
import com.example.microtemp.microblog.models.Menu;

public class FoodListActivityAdmin extends AppCompatActivity {

    public static String menuId;
    private Menu menu;

    private RecyclerView foodsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list_admin);
        this.initRecyclerView();


        this.retrieveMenu();
        FoodListActivityAdmin.menuId=menu.get_id();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin, menu);
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
        this.foodsRecyclerView = findViewById(R.id.ordersAdminRecyclerView);
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

        MenuListAdminAdapter adapter = new MenuListAdminAdapter(this.menu);
        this.foodsRecyclerView.setAdapter(adapter);
    }

    public void addMenus(MenuItem item) {
        Intent intent = new Intent(FoodListActivityAdmin.this, AddDishActivity.class);
        startActivity(intent);

    }
}
