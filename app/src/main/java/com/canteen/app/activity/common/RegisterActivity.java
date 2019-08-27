package com.canteen.app.activity.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.canteen.app.App;
import com.canteen.app.R;
import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.RegisterRequestHandler;
import com.canteen.app.api.models.requests.RegisterRequestBody;
import com.canteen.app.api.models.responses.RegisterResponse;
import com.canteen.app.service.ToastService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    @BindView(R.id.email_text_edit)
    EditText emailTextEdit;

    @BindView(R.id.password_text_edit)
    EditText passwordTextEdit;

    @BindView(R.id.last_name_text_edit)
    EditText lastNameTextEdit;

    @BindView(R.id.first_name_text_edit)
    EditText firstNameTextEdit;

    @BindView(R.id.retype_password_text_edit)
    EditText passwordRepeatTextEdit;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back_button)
    void backButtonHandler() {
        finish();
    }

    @OnClick(R.id.register_button)
    void registerButtonHandler() {
        if (check()) {
            RegisterRequestBody requestBody = RegisterRequestBody.builder()
                    .email(emailTextEdit.getText().toString())
                    .password(passwordTextEdit.getText().toString())
                    .firstName(firstNameTextEdit.getText().toString())
                    .lastName(lastNameTextEdit.getText().toString())
                    .admin(false)
                    .build();
            HttpRequestData<RegisterRequestBody> requestData = HttpRequestData.<RegisterRequestBody>builder()
                    .requestBody(requestBody)
                    .method(HttpRequestMethods.POST)
                    .build();
            new RegisterRequestHandlerImpl().execute(requestData);
        }
    }

    public boolean check() {
        return checkPassword(passwordTextEdit.getText().toString()) &&
                checkName(lastNameTextEdit.getText().toString()) &&
                checkName(firstNameTextEdit.getText().toString()) &&
                checkRetypePassword(passwordTextEdit.getText().toString(), passwordRepeatTextEdit.getText().toString());
    }

    public boolean checkPassword(final String password) {
        String patternPassword = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}";
        if (!password.matches(patternPassword)) {
            makeToast("Weak password");
            return false;
        }
        return true;
    }

    public boolean checkName(final String name) {
        String patternName = "(?=.*[a-z])(?=.*[A-Z]).{3,}";
        if (!name.matches(patternName)) {
            makeToast("Bad name");
            return false;
        }
        return true;
    }

    public boolean checkEmail(final String mail) {
        String EMAIL_VERIFICATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        if (!mail.matches(EMAIL_VERIFICATION)) {
            makeToast("Bad mail");
            return false;
        }
        return true;
    }

    public boolean checkRetypePassword(final String password, final String retypePassword) {
        if (!password.equals(retypePassword)) {
            makeToast("Password different");
            return false;
        }
        return true;
    }

    private void makeToast(final String message) {
        ToastService.make(message);
    }

    private static class RegisterRequestHandlerImpl extends RegisterRequestHandler {

        @Override
        protected void onPostExecute(final RegisterResponse result) {
            Context context = App.getContext();
            if (result.getHttpStatusCode() == 400) {
                ToastService.make("Could not register new user. No internet connection");
            } else if (result.getHttpStatusCode() == 201) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                ToastService.make("New user registered");
            } else {
                Log.d(TAG, "Result: " + result);
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("response", result);
                context.startActivity(intent);
            }
        }
    }
}

