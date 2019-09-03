package com.canteen.app.activity.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.canteen.app.R;
import com.canteen.app.activity.client.menu.list.MenuListsActivity;
import com.canteen.app.service.auth.AuthService;
import com.canteen.app.service.auth.AuthServiceImpl;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private AuthService authService = AuthServiceImpl.of();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (authService.getToken().isPresent()) {
            finish();
            startActivity(new Intent(this, MenuListsActivity.class));
            return;
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
