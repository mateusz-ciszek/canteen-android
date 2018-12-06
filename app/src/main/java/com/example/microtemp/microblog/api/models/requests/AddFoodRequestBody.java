package com.example.microtemp.microblog.api.models.requests;

import com.example.microtemp.microblog.activity.administration.menu.list.list.FoodListActivityAdmin;
import com.example.microtemp.microblog.models.AdditionAdd;

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
    private List<AdditionAdd> additions ;


    @Override
    public String getUrl() {
        return "/menu/"+ FoodListActivityAdmin.menuId+"/food";
    }
}
