package com.example.microtemp.microblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.microtemp.microblog.api.HttpRequestData;
import com.example.microtemp.microblog.api.HttpRequestMethods;
import com.example.microtemp.microblog.api.handlers.RegisterRequestHandler;
import com.example.microtemp.microblog.api.models.requests.RegisterRequestBody;
import com.example.microtemp.microblog.api.models.responses.RegisterResponse;

public class LoginActivity extends AppCompatActivity {

    private static String LOG_TAG = "LoginActivity";
    Button loginBtn,registerBtn;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email) ;
        password = findViewById(R.id.password) ;

        loginBtn =findViewById(R.id.sign_in_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FIXME
//                Toast.makeText(LoginActivity.this, "test", Toast.LENGTH_SHORT).show();
//                new MakeNetworkCall().execute("http://212.191.92.88:5101/"+"email/"+email.getText() +"/haslo/"+ password.getText(), "Get");
            }
        });

        registerBtn = findViewById(R.id.register_button);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterRequestBody requestBody = RegisterRequestBody.builder()
                        .email(email.getText().toString())
                        .password(password.getText().toString())
                        .build();
                HttpRequestData<RegisterRequestBody> requestData = HttpRequestData.<RegisterRequestBody>builder()
                        .requestBody(requestBody)
                        .method(HttpRequestMethods.POST)
                        .build();
                new RegisterRequestHandler() {
                    @Override
                    protected void onPostExecute(RegisterResponse result) {
                        if (result.getHttpStatusCode() == 400) {
                            Toast.makeText(registerBtn.getContext(),
                                    "Nie można zarejestrować nowego użytkownika, ponieważ nie ma dostępu do internetu",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("response", result);
                            startActivity(intent);
                            Log.d(LOG_TAG, "Result: " + result);
                        }
                    }
                }.execute(requestData);
            }
        });
    }
}
