package core.queue.impl;

import core.message.MessageHandler;
import core.message.MessageHandlerImpl;
import core.message.model.Message;
import core.queue.PubSubQueue;
import core.queue.QueueHandler;
import core.subscriber.model.Subscriber;
import core.subscriber.model.TopicSubscriber;

import javax.validation.constraints.NotNull;

public class QueueHandlerImpl implements QueueHandler {

    private PubSubQueue queue;
    private MessageHandler messageHandler;

    public void init() {
        this.queue = PubSubQueue.getPubSubQueue();
        this.messageHandler = new MessageHandlerImpl();
    }

    @Override
    public String createTopic(@NotNull final String name) {
        return queue.createTopic(name);
    }

    @Override
    public void publish(@NotNull final String message, @NotNull final String topic) throws InterruptedException {
        final Message msg = messageHandler.wrapMessage(message);
        queue.publish(msg, topic);
    }

    @Override
    public String subscribe(@NotNull final TopicSubscriber subscriber, @NotNull final String topicId) {
        return queue.subscribe(subscriber, topicId);
    }
}
