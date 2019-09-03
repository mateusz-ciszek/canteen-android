package com.canteen.app.service.auth;

import java.util.Optional;

public interface AuthService {

    void setToken(final String token);

    Optional<String> getToken();

    boolean hasAdministrativePrivileges() throws NoTokenException, InvalidTokenException;

    void clear();
}
