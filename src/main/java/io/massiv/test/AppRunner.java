package io.massiv.test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.IOException;

/**
 *
 */
public class AppRunner {

    public static void main(String...args) throws IOException {

        Injector injector = Guice.createInjector(new AppModule());

        ConnectionProvider connectionProvider = injector.getInstance(ConnectionProvider.class);
        connectionProvider.boot();

        ConsumerService consumerService = injector.getInstance(ConsumerService.class);
        consumerService.init();

    }
}
