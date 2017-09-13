package io.massiv.test;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.io.IOException;

/**
 *
 */
public class ConsumerService {

    @Inject
    private ConnectionProvider provider;

    @Inject
    @Named("queueName")
    private String queueName;

    public void init() throws IOException {
        ConsumerContainer container = new ConsumerContainer(queueName, provider.getConnection().createChannel());
        container.activate();
    }

}
