package com.example.microtemp.microblog.activity.administration.menu.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.microtemp.microblog.R;

public class AddMenuActivity extends AppCompatActivity {

    Button acceptButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R .layout.activity_add_menu);
        acceptButton = findViewById(R.id.acceptButton);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMenuActivity.this,
                        MenusListManagementActivity.class);
                startActivity(intent);
            }
        });

    }
}
