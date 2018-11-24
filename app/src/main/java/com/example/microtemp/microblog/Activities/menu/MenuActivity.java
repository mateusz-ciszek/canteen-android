package com.example.microtemp.microblog.Activities.menu;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.models.Menu;

public class MenuActivity extends AppCompatActivity {

    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        this.menu = (Menu) intent.getSerializableExtra("menu");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(this.menu.getName());
        }
    }
}
