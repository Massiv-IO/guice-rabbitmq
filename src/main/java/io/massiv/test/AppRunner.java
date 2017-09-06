package io.massiv.test;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 *
 */
public class AppRunner {

    public static void main(String...args){

        Injector injector = Guice.createInjector(new AppModule());
        ConnectionProvider connectionProvider = injector.getInstance(ConnectionProvider.class);
        connectionProvider.boot();

    }
}
