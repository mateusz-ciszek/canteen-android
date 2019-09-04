package com.canteen.app.component;

import com.canteen.app.component.modules.AuthModule;
import com.canteen.app.component.modules.CommonModule;
import com.canteen.app.component.modules.LoginModule;
import com.canteen.app.service.auth.AuthService;
import com.canteen.app.service.login.LoginService;

import dagger.Component;

@Component(modules = {
        AuthModule.class,
        CommonModule.class,
        LoginModule.class,
})
public interface AppComponent {

    AuthService getAuthService();

    LoginService getLoginService();
}
