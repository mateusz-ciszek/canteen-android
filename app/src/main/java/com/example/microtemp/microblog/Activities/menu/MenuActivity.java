package com.example.microtemp.microblog.Activities.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.models.Menu;

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
