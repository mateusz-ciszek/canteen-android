package com.canteen.app.service.price;

public class InvalidCurrencyException extends PriceFormatterException {
    public InvalidCurrencyException(final String message) {
        super(message);
    }
}
