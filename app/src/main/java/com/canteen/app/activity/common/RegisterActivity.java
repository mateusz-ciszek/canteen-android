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
import com.canteen.app.service.StringUtil;
import com.canteen.app.service.ToastService;
import com.canteen.app.service.validation.regex.EmailValidator;
import com.canteen.app.service.validation.regex.NameValidator;
import com.canteen.app.service.validation.regex.PasswordValidator;
import com.canteen.app.service.validation.regex.RegexValidator;

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
        if (validateAll()) {
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

    private boolean validateAll() {
        return validatePassword(passwordTextEdit.getText().toString())
                && validateName(lastNameTextEdit.getText().toString())
                && validateName(firstNameTextEdit.getText().toString())
                && validateEmail(emailTextEdit.getText().toString())
                && validatePasswordsEquality(passwordTextEdit.getText().toString(), passwordRepeatTextEdit.getText().toString());
    }

    private boolean validatePassword(final String password) {
        RegexValidator validator = PasswordValidator.of();
        if (!validator.isValid(password)) {
            makeToast(getString(R.string.register_password_length_error));
            return false;
        }
        return true;
    }

    private boolean validateName(final String name) {
        RegexValidator validator = NameValidator.of();
        if (!validator.isValid(name)) {
            makeToast(getString(R.string.register_name_error));
            return false;
        }
        return true;
    }

    private boolean validateEmail(final String email) {
        RegexValidator validator = EmailValidator.of();
        if (!validator.isValid(email)) {
            makeToast(getString(R.string.register_email_error));
            return false;
        }
        return true;
    }

    private boolean validatePasswordsEquality(final String password, final String repeatedPassword) {
        if (!StringUtil.equal(password, repeatedPassword)) {
            makeToast(getString(R.string.register_passwords_different_error));
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
                ToastService.make(context.getString(R.string.register_no_internet_error));
            } else if (result.getHttpStatusCode() == 201) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                ToastService.make(context.getString(R.string.register_success));
            } else {
                Log.d(TAG, "Result: " + result);
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("response", result);
                context.startActivity(intent);
            }
        }
    }
}

