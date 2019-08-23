package com.canteen.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.canteen.app.activity.menu.list.MenuListsActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        if (sp.contains("token")) {
            finish();
            startActivity(new Intent(this, MenuListsActivity.class));
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @OnClick(R.id.login_button)
    void loginButtonHandler() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnClick(R.id.register_button)
    void registerButtonHandler() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
