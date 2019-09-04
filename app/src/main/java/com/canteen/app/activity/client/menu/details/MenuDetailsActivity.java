package com.canteen.app.activity.client.menu.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.canteen.app.R;
import com.canteen.app.activity.client.common.ActivityWithMainOptionMenu;
import com.canteen.app.models.Menu;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuDetailsActivity extends ActivityWithMainOptionMenu {

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

    private void initRecyclerView() {
        this.foodsRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        this.foodsRecyclerView.setLayoutManager(layoutManager);
    }

    private void retrieveMenu() {
        Intent intent = getIntent();
        Menu menu = (Menu) intent.getSerializableExtra("menu");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(menu.getName());
        }

        MenuDetailsAdapter adapter = new MenuDetailsAdapter(menu.getFoods());
        this.foodsRecyclerView.setAdapter(adapter);
    }
}
