package core.queue;

import core.subscriber.model.TopicSubscriber;

import javax.validation.constraints.NotNull;

public interface QueueHandler {

    void init();
    String createTopic(@NotNull final String name);

    void publish(@NotNull final String message, @NotNull final String topic) throws InterruptedException;

    String subscribe(@NotNull final TopicSubscriber subscriber, @NotNull final String topicId);
}
