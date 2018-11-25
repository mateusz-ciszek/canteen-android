package com.example.microtemp.microblog.Activities.menu;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.models.Food;

import java.util.List;

class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    private List<Food> foods;

    MenuAdapter(List<Food> foods) {
        this.foods = foods;
    }


    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_food, parent, false);

        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.setFood(this.foods.get(position));
    }

    @Override
    public int getItemCount() {
        return this.foods.size();
    }
}