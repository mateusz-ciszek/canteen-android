package com.example.microtemp.microblog.activity.administration.menu.list.list;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.microtemp.microblog.App;
import com.example.microtemp.microblog.R;
import com.example.microtemp.microblog.activity.foodDetails.FoodDetailsActivityAdmin;
import com.example.microtemp.microblog.models.Food;
import com.example.microtemp.microblog.models.Menu;
import com.example.microtemp.microblog.service.FoodService;

import java.util.Locale;

class MenuListAdminViewHolder extends RecyclerView.ViewHolder {
    private Food food;
    private TextView name;
    private TextView price;
    private ImageButton removeItemButton;
    private Menu menu;
    int position;


    MenuListAdminViewHolder(final ConstraintLayout itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.foodNameTextViewAdmin);
        this.price = itemView.findViewById(R.id.foodPriceTextViewAdmin);
        this.removeItemButton= itemView.findViewById(R.id.removeItemButtonAdmin);

        this.removeItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                deleteFood();
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
                        name.getText())).show();
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), FoodDetailsActivityAdmin.class);
                intent.putExtra("food", food);
                itemView.getContext().startActivity(intent);
            }
        });


    }


    private void deleteFood() {
        FoodService foodService = FoodService.getInstance();
        FoodService.DeleteFoodRequestHandlerImpl.ResponseHandler<Boolean> handler
                = new FoodService.DeleteFoodRequestHandlerImpl.ResponseHandler<Boolean>() {
            @Override
            public void handle(Boolean response) {
                if (response) {
                    Toast.makeText(name.getContext(),
                            "Food has been deleted",
                            Toast.LENGTH_SHORT).show();
                    // TODO usunięcie food z listy

                    Intent intent = new Intent(App.getContext(), FoodListActivityAdmin.class);
                    menu.getFoods().remove(position);
                    intent.putExtra("menu", menu);
                    App.getContext().startActivity(intent);
                    // (odświeżenie/usunięcie tego samego menu z adaptera)
                } else {
                    Toast.makeText(name.getContext(),
                            "Something went wrong",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };

        foodService.deleteMenu(food, handler);

    }
    void setFood(Food food,Menu menu,int postion) {
        this.menu=menu;
        this.position=postion;
        this.food = food;
        this.name.setText(food.getName());
        this.price.setText(String.format(Locale.getDefault(), "%.2f zł", food.getPrice()));
    }
}