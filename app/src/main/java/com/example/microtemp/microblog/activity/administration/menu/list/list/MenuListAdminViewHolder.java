package com.example.microtemp.microblog.activity.administration.menu.list.list;


import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.activity.foodDetails.FoodDetailsActivity;
import com.example.microtemp.microblog.models.Food;

import java.util.Locale;

class MenuListAdminViewHolder extends RecyclerView.ViewHolder {
    private Food food;
    private TextView name;
    private TextView price;

    MenuListAdminViewHolder(final ConstraintLayout itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.foodNameTextViewAdmin);
        this.price = itemView.findViewById(R.id.foodPriceTextViewAdmin);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), FoodDetailsActivity.class);
                intent.putExtra("food", food);
                itemView.getContext().startActivity(intent);
            }
        });


    }

    void setFood(Food food) {
        this.food = food;
        this.name.setText(food.getName());
        this.price.setText(String.format(Locale.getDefault(), "%.2f zł", food.getPrice()));
    }
}