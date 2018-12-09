package com.canteen.app.activity.administration.menu.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.canteen.app.App;
import com.canteen.app.R;
import com.canteen.app.activity.administration.dashboard.AdminDashboardActivity;
import com.canteen.app.api.HttpRequestData;
import com.canteen.app.api.HttpRequestMethods;
import com.canteen.app.api.handlers.AddMenuRequestHandler;
import com.canteen.app.api.models.requests.AddMenuRequestBody;
import com.canteen.app.api.models.responses.AddMenuResponse;

public class AddMenuActivity extends AppCompatActivity {
    private Button acceptBtn;
    private EditText nameEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        acceptBtn = findViewById(R.id.acceptButton);
        nameEt = findViewById(R.id.et_addMenu);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEt.getText().toString().length() != 0) {
                    AddMenuRequestBody requestBody = AddMenuRequestBody.builder()
                            .name(nameEt.getText().toString())
                            .build();

                    HttpRequestData<AddMenuRequestBody> requestData = HttpRequestData.<AddMenuRequestBody>builder()
                            .requestBody(requestBody)
                            .method(HttpRequestMethods.POST)
                            .build();
                    new AddMenuRequestHandler() {
                        @Override
                        protected void onPostExecute(AddMenuResponse result) {
                            if (result.getHttpStatusCode() == 400) {
                                Toast.makeText(acceptBtn.getContext(),
                                        "Cant add menu",
                                        Toast.LENGTH_LONG).show();
                            } else if (result.getHttpStatusCode() == 201) {

                                Toast.makeText(acceptBtn.getContext(),
                                        "Dish added",
                                        Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(App.getContext(), AdminDashboardActivity.class);
                                App.getContext().startActivity(intent);

                            } else {
                                Intent intent = new Intent(AddMenuActivity.this, AdminDashboardActivity.class);
                                intent.putExtra("response", result);
                                 startActivity(intent);
                            }
                        }
                    }.execute(requestData);
                }
            }


        });
    }
}
