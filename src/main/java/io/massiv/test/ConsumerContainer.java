package io.massiv.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;

import java.io.IOException;

/**
 *
 */
public class ConsumerContainer {
    private String queueName;
    private Channel channel;

    public ConsumerContainer(String queueName, Channel channel) {
        this.queueName = queueName;
        this.channel = channel;
    }

    public void activate() throws IOException {
        channel.basicConsume(queueName, new DefaultConsumer(channel));
    }
}
