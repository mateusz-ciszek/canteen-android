package com.canteen.app.activity.client.menu.details;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.canteen.app.R;
import com.canteen.app.models.Food;

import java.util.List;

class MenuDetailsAdapter extends RecyclerView.Adapter<MenuDetailsViewHolder> {

    private List<Food> foods;

    MenuDetailsAdapter(final List<Food> foods) {
        this.foods = foods;
    }


    @NonNull
    @Override
    public MenuDetailsViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_food, parent, false);

        return new MenuDetailsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuDetailsViewHolder holder, final int position) {
        holder.setFood(this.foods.get(position));
    }

    @Override
    public int getItemCount() {
        return this.foods.size();
    }
}