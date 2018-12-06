package com.example.microtemp.microblog.activity.administration.menu.list;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.activity.administration.menu.list.list.FoodListActivityAdmin;
import com.example.microtemp.microblog.models.Menu;
import com.example.microtemp.microblog.service.MenuService;

import java.util.List;
import java.util.Locale;

class MenusListManagementViewHolder extends RecyclerView.ViewHolder {
    private TextView menuNameTextView;
    private Button deleteButton;
    private List<Menu> menus;
    private int position;
    private Menu menu;

    MenusListManagementViewHolder(final ConstraintLayout itemView) {
        super(itemView);

        this.menuNameTextView =  itemView.findViewById(R.id.menuNameTextView);
        this.deleteButton = itemView.findViewById(R.id.deleteButton);

        this.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                deleteMenu();
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

        menuNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), FoodListActivityAdmin.class);
                intent.putExtra("menu", menu);
                itemView.getContext().startActivity(intent);
            }
        });
    }


    void setMenu(Menu menu,List<Menu> menus,int position) {
        this.menu = menu;
        this.position=position;
        this.menus=menus;
        this.menuNameTextView.setText(menu.getName());
    }

    private void deleteMenu() {
        MenuService menuService = MenuService.getInstance();
        MenuService.DeleteMenuRequestHandlerImpl.ResponseHandler<Boolean> handler
                = new MenuService.DeleteMenuRequestHandlerImpl.ResponseHandler<Boolean>() {
            @Override
            public void handle(Boolean response) {
                if (response) {
                    Toast.makeText(menuNameTextView.getContext(),
                            "Menu has been deleted",
                            Toast.LENGTH_SHORT).show();
                    menus.remove(position);
                    Intent intent = new Intent(itemView.getContext(), MenusListManagementActivity.class);
                    itemView.getContext().startActivity(intent);

                    // TODO usunięcie menu z listy
                    // (odświeżenie/usunięcie tego samego menu z adaptera)
                } else {
                    Toast.makeText(menuNameTextView.getContext(),
                            "Something went wrong",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };

        menuService.deleteMenu(menu, handler);

    }


}
