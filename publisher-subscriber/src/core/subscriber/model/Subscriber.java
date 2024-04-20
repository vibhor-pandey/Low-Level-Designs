package core.subscriber.model;

import core.message.model.Message;

import javax.validation.constraints.NotNull;


public interface Subscriber {
    String getId();

    String getName();

    boolean canResetOffset();

    void consume(@NotNull final Message message, @NotNull final String topic, @NotNull String subscriber) throws InterruptedException;
}
