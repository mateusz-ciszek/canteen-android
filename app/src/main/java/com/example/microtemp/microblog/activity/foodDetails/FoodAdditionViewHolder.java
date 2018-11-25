package com.example.microtemp.microblog.activity.foodDetails;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.models.FoodAddition;

import java.util.Locale;

class FoodAdditionViewHolder extends RecyclerView.ViewHolder {
    private FoodAddition foodAddition;
    private CheckBox foodAdditionCheckBox;
    private TextView foodAdditionPriceTextView;

    FoodAdditionViewHolder(View itemView) {
        super(itemView);

        this.foodAdditionCheckBox = itemView.findViewById(R.id.foodAdditionCheckBox);
        this.foodAdditionPriceTextView = itemView.findViewById(R.id.foodAdditionPriceTextView);
    }

    void setFoodAddition(FoodAddition foodAddition) {
        this.foodAddition = foodAddition;
        this.foodAdditionCheckBox.setText(this.foodAddition.getName());
        this.foodAdditionPriceTextView.setText(String.format(Locale.getDefault(),
                "%.2f zł",
                this.foodAddition.getPrice()));
    }

    CheckBox getFoodAdditionCheckBox() {
        return this.foodAdditionCheckBox;
    }
}
