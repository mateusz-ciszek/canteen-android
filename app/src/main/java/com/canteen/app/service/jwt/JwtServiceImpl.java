package com.canteen.app.service.jwt;

import com.auth0.android.jwt.JWT;

import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "of")
public class JwtServiceImpl implements JwtService {

    private static final String ADMIN_CLAIM_KEY = "admin";

    @Override
    public boolean hasAdministrativePrivileges(final String token) throws NoSuchClaimException {
        JWT jwt = new JWT(token);
        Boolean hasPrivileges = jwt.getClaim(ADMIN_CLAIM_KEY).asBoolean();

        if (hasPrivileges == null) {
            throw new NoSuchClaimException(ADMIN_CLAIM_KEY);
        }

        return hasPrivileges;
    }
}
