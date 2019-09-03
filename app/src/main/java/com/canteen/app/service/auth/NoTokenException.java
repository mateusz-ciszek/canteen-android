package com.canteen.app.service.auth;

public class NoTokenException extends Exception {
    NoTokenException() {
        super("Auth token has not been set");
    }
}
