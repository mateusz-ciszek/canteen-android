package com.canteen.app.activity.food.details;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.canteen.app.R;
import com.canteen.app.models.FoodAddition;

import java.util.ArrayList;
import java.util.List;

public class FoodAdditionAdapter extends RecyclerView.Adapter<FoodAdditionViewHolder> {
    private List<FoodAddition> foodAdditions;
    private PriceContainer priceContainer;
    private List<FoodAddition> selected;

    FoodAdditionAdapter(final List<FoodAddition> foodAdditions, final PriceContainer priceContainer) {
        this.foodAdditions = foodAdditions;
        this.priceContainer = priceContainer;
        this.selected = new ArrayList<>();
    }

    @NonNull
    @Override
    public FoodAdditionViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_food_addition, parent, false);

        return new FoodAdditionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodAdditionViewHolder holder, final int position) {
        FoodAddition foodAddition = foodAdditions.get(position);
        holder.setFoodAddition(foodAddition);

        CheckBox checkBox = holder.getFoodAdditionCheckBox();
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selected.add(foodAddition);
            } else {
                selected.remove(foodAddition);
            }
            double priceSum = selected.stream()
                    .mapToDouble(FoodAddition::getPrice)
                    .sum();
            priceContainer.updatePrice(priceSum);
        });
    }

    @Override
    public int getItemCount() {
        return foodAdditions.size();
    }

    List<FoodAddition> getSelected() {
        return selected;
    }
}
