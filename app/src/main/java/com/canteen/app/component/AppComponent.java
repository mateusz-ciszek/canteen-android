package com.canteen.app.component;

import com.canteen.app.component.modules.AuthModule;
import com.canteen.app.component.modules.CommonModule;
import com.canteen.app.component.modules.LoginModule;
import com.canteen.app.component.modules.OrderModule;
import com.canteen.app.service.auth.AuthService;
import com.canteen.app.service.login.LoginService;
import com.canteen.app.service.order.OrderCartService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AuthModule.class,
        CommonModule.class,
        LoginModule.class,
        OrderModule.class,
})
public interface AppComponent {

    AuthService getAuthService();

    LoginService getLoginService();

    @Singleton
    OrderCartService getOrderCartService();
}
