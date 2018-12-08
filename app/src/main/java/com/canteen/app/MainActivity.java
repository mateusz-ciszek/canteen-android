package com.canteen.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.canteen.app.api.models.responses.Response;
import com.canteen.app.R;

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
