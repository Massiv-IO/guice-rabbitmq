package io.massiv.test;

import java.io.IOException;

/**
 * A simulated wrapper
 */
class DoubleProducerWrapper {
    private ProducerWrapper delegate;

    DoubleProducerWrapper(ProducerWrapper delegate) {
        this.delegate = delegate;
    }

    public void publishDouble(byte[] bytes, String exchangeName) throws IOException {
        delegate.publish(exchangeName, bytes);
    }
}
