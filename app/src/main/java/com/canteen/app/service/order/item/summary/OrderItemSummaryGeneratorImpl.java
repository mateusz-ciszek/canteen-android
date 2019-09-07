package com.canteen.app.service.order.item.summary;

import android.content.Context;

import com.canteen.app.R;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class OrderItemSummaryGeneratorImpl implements OrderItemSummaryGenerator {

    private Context context;

    @Override
    public String generate(final List<FoodAdditionName> names) {
        if (names.isEmpty()) {
            return context.getString(R.string.order_cart_no_additions_label);
        }

        String labelBeginning = context.getString(R.string.order_cart_item_summary_begging);
        StringBuilder builder = new StringBuilder(labelBeginning).append(": ");
        names.forEach(name -> builder.append(name.getName()).append(", "));
        return builder.substring(0, builder.length() - 2);
    }
}
