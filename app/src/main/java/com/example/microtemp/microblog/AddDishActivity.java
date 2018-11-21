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
import com.example.microtemp.microblog.api.handlers.AddFoodRequestHandler;
import com.example.microtemp.microblog.api.models.requests.AddFoodRequestBody;
import com.example.microtemp.microblog.api.models.responses.AddFoodResponse;


public class AddDishActivity extends AppCompatActivity {

    Button backBtn, acceptBtn;
    EditText priceEt, descriptionEt, nameEt;

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
                Intent intent = new Intent(AddDishActivity.this, AdminActivity.class);
                startActivity(intent);
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
                                        "Nie można zarejestrować nowego użytkownika, ponieważ nie ma dostępu do internetu",
                                        Toast.LENGTH_LONG).show();
                            } else if (result.getHttpStatusCode() == 201) {
                                Intent intent = new Intent(AddDishActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(acceptBtn.getContext(),
                                        "Uzytkownik zarejestrowany",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Intent intent = new Intent(AddDishActivity.this, MainActivity.class);
                                intent.putExtra("response", result);
                                startActivity(intent);
                            }
                        }
                    }.execute(requestData);
                }
            } );
    }
}
