package com.canteen.app.service.jwt;

public interface JwtService {

    boolean hasAdministrativePrivileges(final String token) throws NoSuchClaimException;
}
