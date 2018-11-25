package com.example.microtemp.microblog.Activities.foodDetails;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.models.FoodAddition;

import java.util.List;

public class FoodAdditionAdapter extends RecyclerView.Adapter<FoodAdditionViewHolder> {
    private List<FoodAddition> foodAdditions;

    public FoodAdditionAdapter(List<FoodAddition> foodAdditions) {
        this.foodAdditions = foodAdditions;
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
        holder.setFoodAddition(this.foodAdditions.get(position));
    }

    @Override
    public int getItemCount() {
        return this.foodAdditions.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
