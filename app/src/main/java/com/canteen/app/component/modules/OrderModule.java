package com.canteen.app.component.modules;

import com.canteen.app.service.order.OrderCartService;
import com.canteen.app.service.order.OrderCartServiceImpl;
import com.canteen.app.service.price.PriceFormatter;
import com.canteen.app.service.price.PriceFormatterImpl;

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

    @Provides
    PriceFormatter providePriceFormatter() {
        return PriceFormatterImpl.of();
    }
}
