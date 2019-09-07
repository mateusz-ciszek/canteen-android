package com.canteen.app.service.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.canteen.app.R;
import com.canteen.app.activity.administration.dashboard.AdminDashboardActivity;
import com.canteen.app.activity.client.menu.list.MenuListsActivity;
import com.canteen.app.activity.common.MainActivity;
import com.canteen.app.service.ToastService;
import com.canteen.app.service.auth.AuthService;
import com.canteen.app.service.auth.InvalidTokenException;
import com.canteen.app.service.auth.NoTokenException;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class LoginServiceImpl implements LoginService {

    private static final String TAG = "LoginServiceImpl";

    private AuthService authService;

    @Override
    public void login(final String token, final Context context) {
        authService.setToken(token);

        boolean hasPrivileges;
        try {
            hasPrivileges = authService.hasAdministrativePrivileges();
        } catch (final NoTokenException | InvalidTokenException e) {
            Log.d(TAG, "login: token error", e);
            ToastService.make(R.string.login_error);
            return;
        }

        Intent intent;
        // TODO: remove, administrative part is disabled anyway
        if (hasPrivileges) {
            intent = new Intent(context, AdminDashboardActivity.class);
        } else {
            intent = new Intent(context, MenuListsActivity.class);
        }
        // Setting flags clearing history stack trace
        // Hitting back on activity from intent will exit the app instead of returning here
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    public void logout(final Activity activity) {
        authService.clear();
        activity.finishAffinity();
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}
