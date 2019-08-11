package com.canteen.app.service.order;

import com.canteen.app.models.Food;
import com.canteen.app.models.FoodAddition;

import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItem {

    private Food food;

    @Builder.Default
    private List<FoodAddition> additions = Collections.emptyList();

    public double getPrice() {
        return food.getPrice() + additions.stream()
                .mapToDouble(FoodAddition::getPrice)
                .sum();
    }
}
