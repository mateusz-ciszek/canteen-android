package com.canteen.app.service.price;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.DoubleStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PriceFormatterTest {

    private static final double[] prices = {0, 1, .99, 5345.63564};

    private static final String CURRENCY = "$";

    private PriceFormatter formatter;

    @BeforeEach
    void setUp() {
        formatter = PriceFormatterImpl.of();
    }

    @ParameterizedTest
    @MethodSource("getPrices")
    void shouldFormatDoubleWithDefaultCurrency(final double price) {
        String result = formatter.format(price);

        assertThat(result)
                .startsWith(numberFormat(price))
                .containsOnlyOnce(" ")
                .endsWith(formatter.DEFAULT_CURRENCY);
    }

    @ParameterizedTest
    @MethodSource("getPrices")
    void shouldFormatDoubleWithCustomCurrency(final double price) {
        String result = formatter.format(price, CURRENCY);

        assertThat(result)
                .startsWith(numberFormat(price))
                .containsOnlyOnce(" ")
                .endsWith(CURRENCY);
    }

    @ParameterizedTest
    @MethodSource("getPrices")
    void shouldFormatBigDecimalWithDefaultCurrency(final double price) {
        String result = formatter.format(new BigDecimal(price));

        assertThat(result)
                .startsWith(numberFormat(price))
                .containsOnlyOnce(" ")
                .endsWith(formatter.DEFAULT_CURRENCY);
    }

    @ParameterizedTest
    @MethodSource("getPrices")
    void shouldFormatBigDecimalWithCustomCurrency(final double price) {
        String result = formatter.format(new BigDecimal(price), CURRENCY);

        assertThat(result)
                .startsWith(numberFormat(price))
                .containsOnlyOnce(" ")
                .endsWith(CURRENCY);
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNull() {
        assertThatThrownBy(() -> formatter.format(null))
                .isInstanceOf(InvalidPriceException.class)
                .hasMessage("Price cannot be null");
    }

    @Test
    void shouldThrowExceptionWhenCurrencyIsNull() {
        assertThatThrownBy(() -> formatter.format(0, null))
                .isInstanceOf(InvalidCurrencyException.class)
                .hasMessage("Currency cannot be null");
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNegative() {
        assertThatThrownBy(() -> formatter.format(-1))
                .isInstanceOf(InvalidPriceException.class)
                .hasMessage("Price cannot be negative");
    }

    private static DoubleStream getPrices() {
        return DoubleStream.of(prices);
    }

    private String numberFormat(final double number) {
        return String.format("%.2f", number);
    }
}