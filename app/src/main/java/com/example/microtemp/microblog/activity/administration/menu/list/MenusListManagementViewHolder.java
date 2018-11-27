package com.example.microtemp.microblog.activity.administration.menu.list;

import android.content.DialogInterface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.models.Menu;

import java.util.Locale;

class MenusListManagementViewHolder extends RecyclerView.ViewHolder {
    private TextView menuNameTextView;
    private Button deleteButton;
    private Menu menu;

    MenusListManagementViewHolder(final ConstraintLayout itemView) {
        super(itemView);

        this.menuNameTextView = itemView.findViewById(R.id.menuNameTextView);
        this.deleteButton = itemView.findViewById(R.id.deleteButton);

        this.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                builder.setPositiveButton("Delete", listener)
                        .setNegativeButton("Keep", listener);

                builder.setMessage(String.format(Locale.getDefault(),
                        "Are you sure you want to delete menu \"%s\"?\n" +
                                "This can't be reverted!",
                        menuNameTextView.getText())).show();
            }
        });
    }

    void setMenu(Menu menu) {
        this.menu = menu;
        this.menuNameTextView.setText(menu.getName());
    }
}
