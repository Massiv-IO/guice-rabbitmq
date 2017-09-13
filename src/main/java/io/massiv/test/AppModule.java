package io.massiv.test;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.rabbitmq.client.Address;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class AppModule extends AbstractModule {

    protected void configure() {
        bind(ProducerService.class);
        bind(ConnectionProvider.class);
        bind(ConsumerService.class);
        bind(new TypeLiteral<Set<Address>>() {
        }).annotatedWith(Names.named("hostAddresses")).toInstance(getHosts());
        bindConstant().annotatedWith(Names.named("queueName")).to("resolved.queue.name");
        bindConstant().annotatedWith(Names.named("exchangeName")).to(System.getProperty("resolved.exchange", "resolved.exchange"));
    }

    private Set<Address> getHosts() {
        // make things complicated for testing...
        String[] hosts = System.getProperty("resolved.hosts", "localhost").split(",");
        Set<Address> addresses = new HashSet<>();
        for (String host : hosts) {
            addresses.add(new Address(host));
        }
        return addresses;
    }
}
