package com.canteen.app.activity.administration.menu.list.list;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.canteen.app.App;
import com.example.microtemp.microblog.R;
import com.canteen.app.models.Food;
import com.canteen.app.models.Menu;
import com.canteen.app.service.FoodService;

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

                   // ((Activity) App.getContext()).finish();
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