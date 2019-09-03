package com.canteen.app.service.auth;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.canteen.app.App;
import com.canteen.app.service.jwt.JwtService;
import com.canteen.app.service.jwt.JwtServiceImpl;
import com.canteen.app.service.jwt.NoSuchClaimException;

import java.util.Optional;

import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "of")
public class AuthServiceImpl implements AuthService {

    private static final String TOKEN = "token";

    private SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getContext());

    private JwtService jwtService = JwtServiceImpl.of();

    @Override
    public void setToken(final String token) {
        sharedPreferences.edit()
                .putString(TOKEN, token)
                .apply();
    }

    @Override
    public Optional<String> getToken() {
        return Optional.ofNullable(sharedPreferences.getString(TOKEN, null));
    }

    @Override
    public boolean hasAdministrativePrivileges() throws NoTokenException, InvalidTokenException {
        Optional<String> token = getToken();

        if (!token.isPresent()) {
            throw new NoTokenException();
        }

        try {
            return jwtService.hasAdministrativePrivileges(token.get());
        } catch (NoSuchClaimException e) {
            throw new InvalidTokenException(e);
        }
    }

    @Override
    public void clear() {
        sharedPreferences.edit()
                .remove(TOKEN)
                .apply();
    }
}
