package com.canteen.app.component.modules;

import com.canteen.app.service.auth.AuthService;
import com.canteen.app.service.login.LoginService;
import com.canteen.app.service.login.LoginServiceImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    LoginService provideLoginService(final AuthService authService) {
        return LoginServiceImpl.of(authService);
    }
}
