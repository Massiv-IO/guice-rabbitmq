package io.massiv.test;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/**
 *
 */
public class ConnectionProvider {

    @Inject
    @Named("hostAddresses")
    private Set<Address> hosts;

    @Inject
    @Named("exchangeName")
    private String exchangeName;

    @Inject
    @Named("queueName")
    private String queueName;

    private ConnectionFactory factory;

    private volatile Connection connection;

    public ConnectionProvider() {
        factory = new ConnectionFactory();
        factory.setRequestedHeartbeat(100000);
        factory.setConnectionTimeout(1000000);
    }

    public void boot() {
        try {
            Channel channel = getConnection().createChannel();
            channel.exchangeDeclare(exchangeName, "direct", true);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, "foo");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        if (connection != null && connection.isOpen()) {
            return connection;
        }
        return createConnection();
    }

    private synchronized Connection createConnection() {
        try {
            Connection connection = factory.newConnection(hosts.toArray(new Address[0]));
            this.connection = connection;
            return connection;
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
