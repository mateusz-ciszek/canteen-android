package com.canteen.app.activity.client.food.details;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.canteen.app.R;
import com.canteen.app.models.FoodAddition;
import com.canteen.app.service.price.PriceFormatter;
import com.canteen.app.service.price.PriceFormatterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;

@Getter
class FoodAdditionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.food_addition_check_box)
    CheckBox foodAdditionCheckBox;

    @BindView(R.id.food_addition_price_text_view)
    TextView foodAdditionPriceTextView;

    FoodAdditionViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void setFoodAddition(final FoodAddition foodAddition) {
        foodAdditionCheckBox.setText(foodAddition.getName());

        PriceFormatter formatter = PriceFormatterImpl.of();
        foodAdditionPriceTextView.setText(formatter.format(foodAddition.getPrice()));
    }
}
