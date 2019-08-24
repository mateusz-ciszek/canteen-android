package com.canteen.app.service.price;

import java.math.BigDecimal;
import java.util.Locale;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class PriceFormatterImpl implements PriceFormatter {
    @Override
    public String format(final BigDecimal price, final String currency) {
        return String.format(Locale.getDefault(), "%.2f %s", price, currency);
    }
}
