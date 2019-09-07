package com.canteen.app.component.modules;

import android.content.Context;

import com.canteen.app.service.order.OrderCartService;
import com.canteen.app.service.order.OrderCartServiceImpl;
import com.canteen.app.service.order.item.summary.OrderItemSummaryGenerator;
import com.canteen.app.service.order.item.summary.OrderItemSummaryGeneratorImpl;
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

    @Provides
    OrderItemSummaryGenerator provideOrderItemSummaryGenerator(final Context context) {
        return OrderItemSummaryGeneratorImpl.of(context);
    }
}
