package core.publisher.impl;

import core.publisher.PublisherHandler;
import core.queue.QueueHandler;
import core.queue.impl.QueueHandlerImpl;

import javax.validation.constraints.NotNull;

public class PublisherHandlerImpl implements PublisherHandler {
    private final QueueHandler queueHandler;

    private PublisherHandlerImpl() {
        this.queueHandler = new QueueHandlerImpl();
        this.queueHandler.init();
    }

    private static class InstanceHolder {
        private static final PublisherHandler INSTANCE = new PublisherHandlerImpl();
    }

    public static synchronized PublisherHandler getPublisherHandler() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String createTopic(@NotNull final String name) {
        return queueHandler.createTopic(name);
    }

    @Override
    public void publish(@NotNull final String message, String topic) throws InterruptedException {
        queueHandler.publish(message, topic);
    }
}
