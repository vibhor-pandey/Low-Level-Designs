package core.subscriber.impl;

import com.sun.jdi.InvalidTypeException;
import core.queue.QueueHandler;
import core.queue.impl.QueueHandlerImpl;
import core.subscriber.SubscriberHandler;
import core.subscriber.factory.SubscriberFactory;
import core.subscriber.model.SubscriberType;
import core.subscriber.model.TopicSubscriber;

import javax.validation.constraints.NotNull;

public class SubscriberHandlerImpl implements SubscriberHandler {

    private final QueueHandler queueHandler;

    private SubscriberHandlerImpl() {
        this.queueHandler = new QueueHandlerImpl();
        this.queueHandler.init();
    }

    private static class InstanceHolder {
        private static final SubscriberHandlerImpl INSTANCE = new SubscriberHandlerImpl();
    }

    public static synchronized SubscriberHandler getSubscriberHandler() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public void subscribe(
            @NotNull final String name,
            @NotNull final String topic,
            @NotNull SubscriberType type
    ) throws InvalidTypeException {
        final TopicSubscriber subscriber = new TopicSubscriber(SubscriberFactory.getSubscriber(type, name));
        queueHandler.subscribe(subscriber, topic);
    }

    @Override
    public boolean resetOffset(String topic, int offset) {
        return false;
    }
}
