package com.canteen.app.service.price;

public class InvalidPriceException extends PriceFormatterException {
    public InvalidPriceException(final String message) {
        super(message);
    }
}
