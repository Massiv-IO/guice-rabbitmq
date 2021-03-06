package io.massiv.test;


import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 *
 */
public class ProducerService {


    @Inject
    @Named("exchangeName")
    private String exchangeName;

    @Inject
    private ConnectionProvider provider;

    @Inject
    private ExchangeConfiguration exchangeConfiguration;

//    private ExchangeConfiguration getFoo(){
//        return exchangeConfiguration;
//    }

    public void doIt() throws IOException {
        Connection connection = provider.getConnection();
        Channel channel = connection.createChannel();
        ProducerWrapper wrapper = new ProducerWrapper(channel);

        DoubleProducerWrapper doubleWrapper = new DoubleProducerWrapper(wrapper);

        doubleWrapper.publishDouble(new byte[0], exchangeName);

        doubleWrapper.publishDouble(new byte[0], exchangeConfiguration.exchangeName);
    }
}
