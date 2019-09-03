package com.canteen.app.service.jwt;

public class NoSuchClaimException extends Exception {
    NoSuchClaimException(final String claim) {
        super("No claim with key: " + claim);
    }
}
