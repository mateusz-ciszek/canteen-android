package com.example.microtemp.microblog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.microtemp.microblog.Activities.menu.list.MenuListsActivity;
import com.example.microtemp.microblog.api.HttpRequestData;
import com.example.microtemp.microblog.api.HttpRequestMethods;
import com.example.microtemp.microblog.api.handlers.LoginRequestHandler;
import com.example.microtemp.microblog.api.models.requests.LoginRequestBody;
import com.example.microtemp.microblog.api.models.responses.LoginResponse;


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
        if (isEmptyEmailOrPassword()) {
            Toast.makeText(LoginActivity.this,
                    "Bad mail or password",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, TypeDishActivity.class);
            startActivity(intent);
        } else if (loginAsAdminMock()) {
            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
            startActivity(intent);
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

    private boolean loginAsAdminMock() {
        return email.getText().toString().equals("admin");
    }

    private boolean isEmptyEmailOrPassword() {
        return email.getText().toString().equals("") || password.getText().toString().equals("");
    }


    private static class LoginRequestHandlerImpl extends LoginRequestHandler {

        @Override
        protected void onPostExecute(LoginResponse result) {
            if (result.getHttpStatusCode() == 200) {
                Intent intent = new Intent(App.getContext(), MenuListsActivity.class);
                App.getContext().startActivity(intent);
            } else {
                Toast.makeText(App.getContext(),
                        "Could not log in. Check your email and password",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
