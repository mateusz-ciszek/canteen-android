package com.canteen.app.activity.foodDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.canteen.app.App;
import com.canteen.app.R;
import com.canteen.app.activity.cart.OrderCartActivity;
import com.canteen.app.models.Food;

import java.util.Locale;

public class FoodDetailsActivityAdmin extends AppCompatActivity implements PriceContainer {

    private Food food;
    private TextView foodDescriptionTextView;
    private TextView foodPriceTextView;
    private RecyclerView foodAdditionsRecyclerView;
    private Button deleteFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details_admin);

        this.retrieveFood();
        this.initView();
        this.updatePrice(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_food, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                startActivity(new Intent(this, OrderCartActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initView() {
        this.foodDescriptionTextView = findViewById(R.id.foodDescriptionTextViewAdmin);
        this.foodAdditionsRecyclerView = findViewById(R.id.foodAdditionsRecyclerViewAdmin);
        this.foodPriceTextView = findViewById(R.id.foodPriceTextViewButton);
        this.deleteFood = findViewById(R.id.deleteFoodButton);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(this.food.getName());
        }

        this.foodAdditionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        final FoodAdditionAdapter adapter
                = new FoodAdditionAdapter(this.food.getFoodAdditions(), this);
        this.foodAdditionsRecyclerView.setAdapter(adapter);

        this.foodDescriptionTextView.setText(this.food.getDescription());
        this.deleteFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(App.getContext(), "Food delete", Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        });
    }

    private void retrieveFood() {
        Intent intent = getIntent();
        this.food = (Food) intent.getSerializableExtra("food");
    }

    @Override
    public void updatePrice(double priceIncrease) {
        this.foodPriceTextView.setText(String.format(Locale.getDefault(),
                "%.2f zł",
                food.getPrice() + priceIncrease));
    }
}
