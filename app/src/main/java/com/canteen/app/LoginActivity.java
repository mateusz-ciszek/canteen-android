package com.canteen.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.canteen.app.activity.administration.dashboard.AdminDashboardActivity;
import com.canteen.app.activity.menu.list.MenuListsActivity;
import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.LoginRequestHandler;
import com.canteen.app.api.models.requests.LoginRequestBody;
import com.canteen.app.api.models.responses.LoginResponse;
import com.canteen.app.R;


public class LoginActivity extends AppCompatActivity {
    Button loginBtn, registerBtn;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.sign_in_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButtonHandler();
            }
        });

        registerBtn = findViewById(R.id.register_button);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginButtonHandler() {
        if (isEmailOrPasswordEmpty()) {
            Toast.makeText(LoginActivity.this,
                    "Bad mail or password",
                    Toast.LENGTH_SHORT).show();
        } else {
            LoginRequestBody requestBody = LoginRequestBody.builder()
                    .email(email.getText().toString())
                    .password(password.getText().toString())
                    .build();
            HttpRequestData<LoginRequestBody> requestData = HttpRequestData.<LoginRequestBody>builder()
                    .requestBody(requestBody)
                    .method(HttpRequestMethods.POST)
                    .build();

            new LoginRequestHandlerImpl().execute(requestData);
        }
    }

    private boolean isEmailOrPasswordEmpty() {
        return email.getText().toString().equals("") || password.getText().toString().equals("");
    }


    private static class LoginRequestHandlerImpl extends LoginRequestHandler {

        @Override
        protected void onPostExecute(LoginResponse result) {
            if (result.getHttpStatusCode() == 200) {
                final String token = result.getData().getToken();

                SharedPreferences.Editor editor
                        = PreferenceManager.getDefaultSharedPreferences(App.getContext()).edit();
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
                App.getContext().startActivity(intent);
            } else {
                Toast.makeText(App.getContext(),
                        "Could not log in. Check your email and password",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
