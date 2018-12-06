package com.canteen.app.activity.administration.menu.list;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.microtemp.microblog.R;
import com.canteen.app.models.Menu;

import java.util.List;

public class MenusListManagementAdapter extends Adapter<MenusListManagementViewHolder> {
    private List<Menu> menus;

    MenusListManagementAdapter(List<Menu> menus) {
        this.menus = menus;
    }

    @NonNull
    @Override
    public MenusListManagementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_menu_management, parent, false);

        return new MenusListManagementViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenusListManagementViewHolder holder, int position) {
        holder.setMenu(menus.get(position),menus,position);
    }

    @Override
    public int getItemCount() {
        return this.menus.size();
    }
}
