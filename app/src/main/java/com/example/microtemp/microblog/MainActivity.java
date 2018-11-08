package com.example.microtemp.microblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.microtemp.microblog.api.models.responses.Response;

public class MainActivity extends AppCompatActivity {

    TextView textViewResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewResponse = findViewById(R.id.response);
        Intent intent = getIntent();
        Response data = (Response) intent.getSerializableExtra("response");
        textViewResponse.setText(data.getHttpStatusCode() + ": " + data.getData().getMessage());


    }
}
