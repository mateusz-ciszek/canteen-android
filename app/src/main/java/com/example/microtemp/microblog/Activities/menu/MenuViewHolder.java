package com.example.microtemp.microblog.Activities.menu;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.microtemp.microblog.Activities.foodDetails.FoodDetailsActivity;
import com.example.microtemp.microblog.App;
import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.models.Food;

import java.util.Locale;

class MenuViewHolder extends RecyclerView.ViewHolder {
    private Food food;
    private TextView name;
    private TextView price;
    private ImageButton addToCartButton;

    MenuViewHolder(final ConstraintLayout itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.foodNameTextView);
        this.price = itemView.findViewById(R.id.foodPriceTextView);
        this.addToCartButton = itemView.findViewById(R.id.addToCartButton);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), FoodDetailsActivity.class);
                intent.putExtra("food", food);
                itemView.getContext().startActivity(intent);
            }
        });

        this.addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(App.getContext(), "Cart not implemented", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    void setFood(Food food) {
        this.food = food;
        this.name.setText(food.getName());
        this.price.setText(String.format(Locale.getDefault(), "%.2f zł", food.getPrice()));
    }
}