package com.canteen.app.service.order;

import com.canteen.app.models.Food;
import com.canteen.app.models.FoodAddition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderCartServiceTest {

    private static final double FOOD_PRICE = 9.99;

    private static final double ADDITION_PRICE = .99;

    private OrderCartService service;

    @BeforeEach
    void setUp() {
        service = new OrderCartService();
    }

    @Test
    void shouldBeEmptyOnInit() {
        assertThat(service.getCount()).isEqualTo(0);
        assertThat(service.isEmpty()).isTrue();
    }

    @Test
    void shouldHavePriceZeroOnInit() {
        assertThat(service.getPrice()).isEqualTo(0);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 7})
    void shouldCalculatePrice(final int itemsAmount) {
        for (int item = 0; item < itemsAmount; ++item) {
            service.addItem(mockOrderItem());
        }

        assertThat(service.getPrice()).isEqualTo(FOOD_PRICE * itemsAmount);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 23})
    void shouldCalculatePriceForItemWithAdditions(final int additionsAmount) {
        List<FoodAddition> additions = new ArrayList<>();
        for (int addition = 0; addition < additionsAmount; ++addition) {
            additions.add(mockFoodAddition());
        }
        OrderItem item = mockOrderItem().toBuilder()
                .additions(additions)
                .build();
        service.addItem(item);

        assertThat(service.getPrice()).isEqualTo(FOOD_PRICE + ADDITION_PRICE * additionsAmount);
    }

    private OrderItem mockOrderItem() {
        Food food = new Food();
        food.setPrice(FOOD_PRICE);

        return OrderItem.builder()
                .food(food)
                .build();
    }

    private FoodAddition mockFoodAddition() {
        FoodAddition addition = new FoodAddition();
        addition.setPrice(ADDITION_PRICE);
        return addition;
    }
}