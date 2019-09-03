package com.canteen.app.service.auth;

public class InvalidTokenException extends Exception {
    InvalidTokenException(final Throwable cause) {
        super("Property not in token", cause);
    }
}
