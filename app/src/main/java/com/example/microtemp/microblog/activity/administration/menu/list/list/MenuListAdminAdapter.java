package com.example.microtemp.microblog.activity.administration.menu.list.list;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.models.Food;
import com.example.microtemp.microblog.models.Menu;

import java.util.List;

class MenuListAdminAdapter extends RecyclerView.Adapter<MenuListAdminViewHolder> {

    private List<Food> foods;
    private Menu menu;

    MenuListAdminAdapter(Menu menu) {
        this.foods = menu.getFoods();
        this.menu=menu;
    }


    @NonNull
    @Override
    public MenuListAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_order_item_admin, parent, false);

        return new MenuListAdminViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuListAdminViewHolder holder, int position) {
        holder.setFood(this.foods.get(position),this.menu,position);
    }

    @Override
    public int getItemCount() {
        return this.foods.size();
    }
}