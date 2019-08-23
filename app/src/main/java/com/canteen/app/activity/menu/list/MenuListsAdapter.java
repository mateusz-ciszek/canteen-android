package com.canteen.app.activity.menu.list;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.canteen.app.R;
import com.canteen.app.models.Menu;

import java.util.List;

class MenuListsAdapter extends RecyclerView.Adapter<MenuListsViewHolder> {

    private List<Menu> menus;

    MenuListsAdapter(List<Menu> menus) {
        this.menus = menus;
    }


    @NonNull
    @Override
    public MenuListsViewHolder onCreateViewHolder(final @NonNull ViewGroup parent, final int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_menu, parent, false);

        return new MenuListsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final @NonNull MenuListsViewHolder holder, final int position) {
        Menu menu = this.menus.get(position);
        holder.nameTextView.setText(menu.getName());
        holder.setId(menu.get_id());
        holder.setMenu(menu);
    }

    @Override
    public int getItemCount() {
        return this.menus.size();
    }
}