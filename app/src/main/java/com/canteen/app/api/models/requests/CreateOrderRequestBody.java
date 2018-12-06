package com.canteen.app.api.models.requests;

import com.canteen.app.OrderCart;
import com.canteen.app.models.FoodAddition;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequestBody extends RequestBody {
    private List<Item> items;

    @Override
    public String getUrl() {
        return "/order";
    }

    public CreateOrderRequestBody(List<OrderCart.OrderItem> orderItems) {
        items = new ArrayList<>();
        for (OrderCart.OrderItem cartItem : orderItems) {
            items.add(new Item(cartItem));
        }
    }

    // TODO refactor?
    private class Item {
        String _id;
        int quantity = 1;
        List<ItemAddition> additions;

        Item(OrderCart.OrderItem cartItem) {
            _id = cartItem.getFood().get_id();
            additions = new ArrayList<>();
            for (FoodAddition foodAddition : cartItem.getAdditions()) {
                additions.add(new ItemAddition(foodAddition));
            }
        }
    }

    private class ItemAddition {
        String _id;
        int quantity = 1;

        ItemAddition(FoodAddition foodAddition) {
            _id = foodAddition.get_id();
        }
    }
}
