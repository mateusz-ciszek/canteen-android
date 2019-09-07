package com.canteen.app.activity.client.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.canteen.app.R;
import com.canteen.app.activity.client.cart.OrderCartActivity;
import com.canteen.app.component.DaggerAppComponent;
import com.canteen.app.service.login.LoginService;

public abstract class ActivityWithMainOptionMenu extends AppCompatActivity {

    private LoginService loginService = DaggerAppComponent.create().getLoginService();

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
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
            case R.id.action_logout:
                loginService.logout(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
