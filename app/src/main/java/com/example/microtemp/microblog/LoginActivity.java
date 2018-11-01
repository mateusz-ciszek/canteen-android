package com.example.microtemp.microblog;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.microtemp.microblog.api.HttpRequestData;
import com.example.microtemp.microblog.api.HttpRequestMethods;
import com.example.microtemp.microblog.api.models.RegisterRequestBody;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

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
                Toast.makeText(LoginActivity.this, "test", Toast.LENGTH_SHORT).show();
                new MakeNetworkCall().execute("http://212.191.92.88:5101/"+"email/"+email.getText() +"/haslo/"+ password.getText(), "Get");
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
                Gson gson = new Gson();
                new MakeNetworkCall().execute(gson.toJson(requestData));
                Toast.makeText(LoginActivity.this, "test", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // FIXME Wyodrębnienie tego do osobnej klasy
    // Powinno działać dla dowolnych typów zapytan i odpowiedzi
    InputStream ByGetMethod(String ServerURL) {

        InputStream DataInputStream = null;
        try {

            URL url = new URL(ServerURL);
            HttpURLConnection cc = (HttpURLConnection)
                    url.openConnection();
            cc.setReadTimeout(5000);
            cc.setConnectTimeout(5000);
            cc.setRequestMethod("GET");
            cc.setDoInput(true);


            int response = cc.getResponseCode();

            if (response == HttpURLConnection.HTTP_OK) {
                DataInputStream = cc.getInputStream();
            }

        } catch (Exception e) {
            Log.e(LOG_TAG, "Error in GetData", e);
        }
        return DataInputStream;

    }

    // FIXME Wyodrębnienie tego do osobnej klasy
    InputStream ByPostMethod(String ServerURL, String data) {

        InputStream inputStream = null;
        try {
            URL url = new URL(ServerURL);
            HttpURLConnection cc = (HttpURLConnection)
                    url.openConnection();
            cc.setConnectTimeout(5000);
            cc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            cc.setRequestMethod("POST");
            cc.setDoInput(true);
            cc.setDoOutput(true);
            cc.connect();
            DataOutputStream dos = new DataOutputStream(cc.getOutputStream());
            dos.writeBytes(data);
            dos.flush();
            dos.close();

            int response = cc.getResponseCode();
            if(response == HttpURLConnection.HTTP_OK) {
                inputStream = cc.getInputStream();
            } else {
                inputStream = cc.getErrorStream();
            }

        } catch (Exception e) {
            Log.e(LOG_TAG, "Error in GetData", e);
        }
        return inputStream;

    }

    // FIXME Wyodrębnienie tego do osobnej klasy
    String ConvertStreamToString(InputStream stream) {
        InputStreamReader isr = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder response = new StringBuilder();

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
        } finally {

            try {
                stream.close();

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error in ConvertStreamToString", e);

            } catch (Exception e) {
                Log.e(LOG_TAG, "Error in ConvertStreamToString", e);
            }
        }
        return response.toString();


    }

    // FIXME Wyodrębnienie tego do osobnej klasy
    private class MakeNetworkCall extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... arg) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Type requestDataType = new TypeToken<HttpRequestData<RegisterRequestBody>>() {}.getType();
            HttpRequestData requestData = gsonBuilder.create().fromJson(arg[0], requestDataType);
            String URL = requestData.getUrl();
            HttpRequestMethods method = requestData.getMethod();
            Gson gson = new Gson();
            String data = gson.toJson(requestData.getRequestBody());

            Log.d(LOG_TAG, "URL: " + URL);
            InputStream is;
            String res;


            if (method == HttpRequestMethods.POST) {

                is = ByPostMethod(URL, data);

            } else {

                is = ByGetMethod(URL);
            }
            if (is != null) {
                res = ConvertStreamToString(is);
            } else {
                res = "Something went wrong";
            }
            return res;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("response", result);
            startActivity(intent);
            Log.d(LOG_TAG, "Result: " + result);
        }
    }
}
