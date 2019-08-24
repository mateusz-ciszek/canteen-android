package com.canteen.app.service.order.item.summary;

import com.canteen.app.App;
import com.canteen.app.R;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class OrderItemSummaryUtil {

    public String generateLabel(final List<FoodAdditionName> names) {
        if (names.isEmpty()) {
            return getStringResource(R.string.order_cart_no_additions_label);
        }

        String labelBeginning = getStringResource(R.string.order_cart_item_summary_begging);
        StringBuilder builder = new StringBuilder(labelBeginning).append(": ");
        names.forEach(name -> builder.append(name.getName()).append(", "));
        return builder.substring(0, builder.length() - 2);
    }

    private String getStringResource(final int resource) {
        return App.getContext().getString(resource);
    }
}
