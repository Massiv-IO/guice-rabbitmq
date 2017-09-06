package io.massiv.test;

import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * A simulated wrapper
 */
class ProducerWrapper {
    private Channel channel;

    ProducerWrapper(Channel channel) {
        this.channel = channel;
    }

    public void publish(String exchangeName, byte[] bytes) throws IOException {
        channel.basicPublish(exchangeName, "foo", null, bytes);
    }
}
