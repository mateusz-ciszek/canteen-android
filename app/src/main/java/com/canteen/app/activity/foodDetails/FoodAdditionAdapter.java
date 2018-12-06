package com.canteen.app.activity.foodDetails;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.microtemp.microblog.R;
import com.canteen.app.models.FoodAddition;

import java.util.ArrayList;
import java.util.List;

public class FoodAdditionAdapter extends RecyclerView.Adapter<FoodAdditionViewHolder> {
    private List<FoodAddition> foodAdditions;
    private PriceContainer priceContainer;
    private List<FoodAddition> selected;

    FoodAdditionAdapter(List<FoodAddition> foodAdditions, PriceContainer priceContainer) {
        this.foodAdditions = foodAdditions;
        this.priceContainer = priceContainer;
        this.selected = new ArrayList<>();
    }

    @NonNull
    @Override
    public FoodAdditionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_food_addition, parent, false);

        return new FoodAdditionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdditionViewHolder holder, int position) {
        final FoodAddition foodAddition = this.foodAdditions.get(position);
        holder.setFoodAddition(foodAddition);
        final CheckBox checkBox = holder.getFoodAdditionCheckBox();
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selected.add(foodAddition);
                } else {
                    selected.remove(foodAddition);
                }
                double priceSum = 0;
                for (FoodAddition foodAddition : selected) {
                    priceSum += foodAddition.getPrice();
                }
                priceContainer.updatePrice(priceSum);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.foodAdditions.size();
    }

    public List<FoodAddition> getSelected() {
        return this.selected;
    }
}
