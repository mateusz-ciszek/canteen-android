package com.example.microtemp.microblog.deletion;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.microtemp.microblog.App;
import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.activity.administration.dashboard.AdminDashboardActivity;
import com.example.microtemp.microblog.activity.administration.menu.list.list.FoodListActivityAdmin;
import com.example.microtemp.microblog.api.HttpRequestData;
import com.example.microtemp.microblog.api.HttpRequestMethods;
import com.example.microtemp.microblog.api.handlers.AddFoodRequestHandler;
import com.example.microtemp.microblog.api.models.requests.AddFoodRequestBody;
import com.example.microtemp.microblog.api.models.responses.AddFoodResponse;
import com.example.microtemp.microblog.models.AdditionAdd;
import com.example.microtemp.microblog.models.Menu;

import java.util.ArrayList;
import java.util.List;


public class AddDishActivity extends AppCompatActivity {

    Button backBtn, acceptBtn,addAdditivesbtn;
    EditText priceEt, descriptionEt, nameEt,editNameAdditives,editTextAdditivesPrice;
    Switch switchFood;
    LinearLayout linearLayout;
    TextView displayAdditives;
    String displayString;
    private List<AdditionAdd> additions=new ArrayList<AdditionAdd>();

    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);
        this.retrieveMenu();
        switchFood = findViewById(R.id.switchFood);
        linearLayout = findViewById(R.id.linearLayoutAdditives);
        displayAdditives = findViewById(R.id.displayAdditives);
        editNameAdditives = findViewById(R.id.editNameAdditives);
        editTextAdditivesPrice = findViewById(R.id.editTextAdditives);
        addAdditivesbtn=findViewById(R.id.addAdditivesbtn);
        priceEt = findViewById(R.id.price_et);
        descriptionEt = findViewById(R.id.description_et);
        nameEt = findViewById(R.id.name_et);
        backBtn = findViewById(R.id.back_button);
        acceptBtn = findViewById(R.id.accept_button);
        switchFood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
             linearLayout.setVisibility(View.VISIBLE);
                else {
                    linearLayout.setVisibility(View.INVISIBLE);

                }
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addAdditivesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(checkIsNotNull(editNameAdditives.getText().toString())&& checkIsNotNull(editTextAdditivesPrice.getText().toString()))) {
                    Toast.makeText(acceptBtn.getContext(),
                            "Cant add additives",
                            Toast.LENGTH_LONG).show();
                } else if(!(isNumber(editTextAdditivesPrice.getText().toString()))) {

                    Toast.makeText(acceptBtn.getContext(),
                            "Only Number",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    AdditionAdd additionAdd=new AdditionAdd();
                    additionAdd.setName(editNameAdditives.getText().toString());
                    additionAdd.setPrice(Double.valueOf(editTextAdditivesPrice.getText().toString()));
                    additions.add(additionAdd);
                    displayString= displayAdditives.getText().toString()+"\n"+additionAdd.getName()+ "     "+additionAdd.getPrice()+ " zł";
                    displayAdditives.setText(displayString);
                }
            }
        } );

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkAll()) {
                    AddFoodRequestBody requestBody = AddFoodRequestBody.builder()
                            .name(nameEt.getText().toString())
                            .description(descriptionEt.getText().toString())
                            .price(Integer.parseInt(priceEt.getText().toString()))
                            .additions(additions)
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
                            } else if (result.getHttpStatusCode() == 201) {

                                Toast.makeText(acceptBtn.getContext(),
                                        "Dish added",
                                        Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(App.getContext(), FoodListActivityAdmin.class);
                                intent.putExtra("menu",menu);
                                App.getContext().startActivity(intent);
                                finish();

                            } else {
                                Intent intent = new Intent(AddDishActivity.this, AdminDashboardActivity.class);
                                intent.putExtra("response", result);
                                startActivity(intent);
                            }
                        }
                    }.execute(requestData);
                }

                }
            } );
    }

    public boolean checkAll() {
        boolean flag=true;
        if(!(checkIsNotNull(nameEt.getText().toString())&&checkIsNotNull(descriptionEt.getText().toString())&&checkIsNotNull(priceEt.getText().toString())))
        {
            Toast.makeText(acceptBtn.getContext(),
                    "Null",
                    Toast.LENGTH_LONG).show();
            flag=false;
        }
        if( !isNumber(priceEt.getText().toString()))
        {
            Toast.makeText(acceptBtn.getContext(),
                    "Price is number",
                    Toast.LENGTH_LONG).show();
            flag=false;
        }

        return flag;
    }
    public boolean checkIsNotNull(String string)
    {
        if(string.length()==0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean isNumber(String string)
    {
        try
        {
            double d = Double.parseDouble(string);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    private void retrieveMenu() {
        Intent intent = getIntent();
        this.menu = (Menu) intent.getSerializableExtra("menu");


    }

}
