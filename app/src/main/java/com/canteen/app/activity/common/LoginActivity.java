package com.canteen.app.activity.common;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.canteen.app.App;
import com.canteen.app.R;
import com.canteen.app.activity.administration.dashboard.AdminDashboardActivity;
import com.canteen.app.activity.client.menu.list.MenuListsActivity;
import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.LoginRequestHandler;
import com.canteen.app.api.models.requests.LoginRequestBody;
import com.canteen.app.api.models.responses.LoginResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email_text_edit)
    EditText emailTextEdit;

    @BindView(R.id.password_text_edit)
    EditText passwordTextEdit;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_button)
    void loginButtonHandler() {
        if (isEmailOrPasswordEmpty()) {
            Toast.makeText(LoginActivity.this,
                    "Bad mail or password",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequestBody requestBody = LoginRequestBody.builder()
                .email(emailTextEdit.getText().toString())
                .password(passwordTextEdit.getText().toString())
                .build();
        HttpRequestData<LoginRequestBody> requestData = HttpRequestData.<LoginRequestBody>builder()
                .requestBody(requestBody)
                .method(HttpRequestMethods.POST)
                .build();

        new LoginRequestHandlerImpl().execute(requestData);
    }

    @OnClick(R.id.register_button)
    void registerButtonHandler() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private boolean isEmailOrPasswordEmpty() {
        return emailTextEdit.getText().toString().isEmpty() || passwordTextEdit.getText().toString().isEmpty();
    }


    private static class LoginRequestHandlerImpl extends LoginRequestHandler {

        @Override
        protected void onPostExecute(final LoginResponse result) {
            if (result.getHttpStatusCode() == 200) {
                final String token = result.getData().getToken();

                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(App.getContext()).edit();
                editor.putString("token", token);
                editor.apply();

                JWT jwt = new JWT(token);
                boolean isAdmin = jwt.getClaim("admin").asBoolean();

                Intent intent;
                if (isAdmin) {
                    intent = new Intent(App.getContext(), AdminDashboardActivity.class);
                } else {
                    intent = new Intent(App.getContext(), MenuListsActivity.class);
                }
                // Setting flags clearing history stack trace
                // Hitting back on activity from intent will exit the app instead of returning here
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                App.getContext().startActivity(intent);
            } else {
                Toast.makeText(App.getContext(),
                        "Could not log in. Check your email and password",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
