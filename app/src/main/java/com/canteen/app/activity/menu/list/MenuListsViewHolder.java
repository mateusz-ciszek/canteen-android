package com.canteen.app.activity.menu.list;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.canteen.app.activity.menu.MenuActivity;
import com.canteen.app.R;
import com.canteen.app.models.Menu;

class MenuListsViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    String _id;
    Menu menu;

    MenuListsViewHolder(final ConstraintLayout itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.menuNameTextView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), MenuActivity.class);
                intent.putExtra("menu", menu);
                itemView.getContext().startActivity(intent);

            }
        });
    }
}