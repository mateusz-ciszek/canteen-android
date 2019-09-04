package com.canteen.app.activity.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.canteen.app.App;
import com.canteen.app.R;
import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.LoginRequestHandler;
import com.canteen.app.api.models.requests.LoginRequestBody;
import com.canteen.app.api.models.responses.LoginResponse;
import com.canteen.app.service.ToastService;
import com.canteen.app.service.login.LoginService;
import com.canteen.app.service.login.LoginServiceImpl;

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
        if (emailTextEdit.getText().toString().isEmpty()) {
            ToastService.make(getString(R.string.common_empty_email));
            return;
        }

        if (passwordTextEdit.getText().toString().isEmpty()) {
            ToastService.make(getString(R.string.common_password_empty));
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


    private static class LoginRequestHandlerImpl extends LoginRequestHandler {

        private LoginService loginService = LoginServiceImpl.of();

        @Override
        protected void onPostExecute(final LoginResponse result) {
            Context context = App.getContext();
            if (!result.isSuccessful()) {
                ToastService.make(context.getString(R.string.login_error));
                return;
            }

            final String token = result.getData().getToken();
            loginService.login(token, context);
        }
    }
}
