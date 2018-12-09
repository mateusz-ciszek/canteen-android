package com.canteen.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.canteen.app.activity.menu.list.MenuListsActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if (sp.contains("token")) {
            finish();
            startActivity(new Intent(this, MenuListsActivity.class));
        }

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(v ->
                startActivity(new Intent(this, LoginActivity.class)));
        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
