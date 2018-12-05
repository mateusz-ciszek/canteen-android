package com.example.microtemp.microblog.deletion;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.microtemp.microblog.App;
import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.activity.administration.dashboard.AdminDashboardActivity;
import com.example.microtemp.microblog.api.HttpRequestData;
import com.example.microtemp.microblog.api.HttpRequestMethods;
import com.example.microtemp.microblog.api.handlers.AddFoodRequestHandler;
import com.example.microtemp.microblog.api.models.requests.AddFoodRequestBody;
import com.example.microtemp.microblog.api.models.responses.AddFoodResponse;
import com.example.microtemp.microblog.models.Menu;


public class AddDishActivity extends AppCompatActivity {

    Button backBtn, acceptBtn;
    EditText priceEt, descriptionEt, nameEt;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);

        priceEt = findViewById(R.id.price_et);
        descriptionEt = findViewById(R.id.description_et);
        nameEt = findViewById(R.id.name_et);
        backBtn = findViewById(R.id.back_button);
        acceptBtn = findViewById(R.id.accept_button);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    AddFoodRequestBody requestBody =AddFoodRequestBody.builder()
                            .name(nameEt.getText().toString())
                            .description(descriptionEt.getText().toString())
                            .price(Integer.parseInt(priceEt.getText().toString()))
                            .build();
                    HttpRequestData<AddFoodRequestBody> requestData = HttpRequestData.<AddFoodRequestBody>builder()
                            .requestBody(requestBody)
                            .method(HttpRequestMethods.POST)
                            .build();
                    new AddFoodRequestHandler() {
                        @Override
                        protected void onPostExecute(AddFoodResponse result) {
                            if (result.getHttpStatusCode() == 400) {
                                Toast.makeText(acceptBtn.getContext(),
                                        "Cant add dish",
                                        Toast.LENGTH_LONG).show();
                               Intent intent = new Intent(App.getContext(), AdminDashboardActivity.class);
                               App.getContext().startActivity(intent);
                                Toast.makeText(acceptBtn.getContext(),
                                        "Dish added",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Intent intent = new Intent(AddDishActivity.this, AdminDashboardActivity.class);
                                intent.putExtra("response", result);
                                startActivity(intent);
                            }
                        }
                    }.execute(requestData);
                }
            } );
    }
}
