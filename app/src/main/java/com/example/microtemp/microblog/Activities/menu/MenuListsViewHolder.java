package com.example.microtemp.microblog.Activities.menu;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.microtemp.microblog.R;

class MenuListsViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    String _id;

    MenuListsViewHolder(ConstraintLayout itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.menuNameTextView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), _id, Toast.LENGTH_SHORT).show();
            }
        });
    }
}