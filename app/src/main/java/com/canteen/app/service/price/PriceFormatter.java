package com.canteen.app.service.price;

import java.math.BigDecimal;

public interface PriceFormatter {

    String DEFAULT_CURRENCY = "zł";

    default String format(final double price) {
        return format(new BigDecimal(price), DEFAULT_CURRENCY);
    }

    default String format(final double price, final String currency) {
        return format(new BigDecimal(price), currency);
    }

    default String format(final BigDecimal price) {
        return format(price, DEFAULT_CURRENCY);
    }

    String format(final BigDecimal price, final String currency);
}
