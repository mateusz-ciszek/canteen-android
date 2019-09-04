package com.canteen.app.component.modules;

import android.content.SharedPreferences;

import com.canteen.app.service.auth.AuthService;
import com.canteen.app.service.auth.AuthServiceImpl;
import com.canteen.app.service.jwt.JwtService;
import com.canteen.app.service.jwt.JwtServiceImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthModule {

    @Provides
    JwtService provideJwtService() {
        return JwtServiceImpl.of();
    }

    @Provides
    AuthService provideAuthService(final SharedPreferences sharedPreferences, final JwtService jwtService) {
        return AuthServiceImpl.of(sharedPreferences, jwtService);
    }
}
