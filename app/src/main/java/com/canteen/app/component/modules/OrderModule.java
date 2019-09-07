package com.canteen.app.component.modules;

import com.canteen.app.service.order.OrderCartService;
import com.canteen.app.service.order.OrderCartServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class OrderModule {

    @Provides
    @Singleton
    OrderCartService provideOrderCartService() {
        return OrderCartServiceImpl.of();
    }
}
