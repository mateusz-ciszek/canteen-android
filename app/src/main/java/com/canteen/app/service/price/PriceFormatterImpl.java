package com.canteen.app.service.price;

import java.math.BigDecimal;
import java.util.Locale;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class PriceFormatterImpl implements PriceFormatter {
    @Override
    public String format(final BigDecimal price, final String currency) {
        if (price == null) {
            throw new InvalidPriceException("Price cannot be null");
        }

        if (currency == null) {
            throw new InvalidCurrencyException("Currency cannot be null");
        }

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidPriceException("Price cannot be negative");
        }

        return String.format(Locale.getDefault(), "%.2f %s", price, currency);
    }
}
