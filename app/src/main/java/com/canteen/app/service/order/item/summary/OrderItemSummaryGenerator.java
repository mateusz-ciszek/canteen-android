package com.canteen.app.service.order.item.summary;

import java.util.List;

public interface OrderItemSummaryGenerator {
    String generate(final List<FoodAdditionName> names);
}
