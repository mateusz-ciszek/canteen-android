package com.canteen.app.activity.client.menu.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.canteen.app.R;
import com.canteen.app.activity.client.cart.OrderCartActivity;
import com.canteen.app.models.Menu;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuDetailsActivity extends AppCompatActivity {

    private Menu menu;

    @BindView(R.id.foods_recycler_view)
    RecyclerView foodsRecyclerView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);
        ButterKnife.bind(this);

        this.initRecyclerView();

        this.retrieveMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(final android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                startActivity(new Intent(this, OrderCartActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initRecyclerView() {
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

        MenuDetailsAdapter adapter = new MenuDetailsAdapter(this.menu.getFoods());
        this.foodsRecyclerView.setAdapter(adapter);
    }
}
