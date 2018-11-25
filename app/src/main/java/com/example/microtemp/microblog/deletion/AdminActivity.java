package com.example.microtemp.microblog.deletion;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.microtemp.microblog.R;


public class AdminActivity extends AppCompatActivity {
    Button addDishButton;
    Button currentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        addDishButton=findViewById(R.id.add_dish_button);
        currentButton=findViewById(R.id.current_order_button);


        addDishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AddDishActivity.class);
                startActivity(intent);
            }
        });
        currentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, CurrentOrderActivity.class);
                startActivity(intent);
            }
        });
    }
}
