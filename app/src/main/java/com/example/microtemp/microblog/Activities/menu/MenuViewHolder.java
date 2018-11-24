package com.example.microtemp.microblog.Activities.menu;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.microtemp.microblog.App;
import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.models.Food;

class MenuViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView price;
    ImageButton addToCartButton;
    String _id;
    Food food;

    MenuViewHolder(final ConstraintLayout itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.foodNameTextView);
        this.price = itemView.findViewById(R.id.foodPriceTextView);
        this.addToCartButton = itemView.findViewById(R.id.addToCartButton);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(App.getContext(), _id, Toast.LENGTH_SHORT).show();
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

    void setPrice(double price) {
        this.price.setText(Double.toString(price) + " zł");
    }
}