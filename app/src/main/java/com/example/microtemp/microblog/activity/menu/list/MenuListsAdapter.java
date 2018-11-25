package com.example.microtemp.microblog.activity.menu.list;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.models.Menu;

import java.util.List;

class MenuListsAdapter extends RecyclerView.Adapter<MenuListsViewHolder> {

    private List<Menu> menus;

    MenuListsAdapter(List<Menu> menus) {
        this.menus = menus;
    }


    @NonNull
    @Override
    public MenuListsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_menu, parent, false);

        return new MenuListsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuListsViewHolder holder, int position) {
        holder.name.setText(this.menus.get(position).getName());
        holder._id = this.menus.get(position).get_id();
        holder.menu = this.menus.get(position);
    }

    @Override
    public int getItemCount() {
        return this.menus.size();
    }
}