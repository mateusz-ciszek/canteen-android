package com.canteen.app.api.models.requests;

import com.canteen.app.activity.administration.menu.list.list.FoodListActivityAdmin;
import com.canteen.app.models.FoodAddition;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddFoodRequestBody extends RequestBody {
    private String name;
    private int price;
    private String description;
    private List<FoodAddition> additions ;


    @Override
    public String getUrl() {
        return "/menu/"+ FoodListActivityAdmin.menuId+"/food";
    }
}
