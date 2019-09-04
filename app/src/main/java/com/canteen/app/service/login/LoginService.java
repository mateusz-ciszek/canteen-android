package com.canteen.app.service.login;

import android.app.Activity;
import android.content.Context;

public interface LoginService {

    void login(final String token, final Context context);

    void logout(final Activity activity);
}
