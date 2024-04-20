package core.subscriber.model;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class TopicSubscriber {
    private final AtomicInteger offset;

    private final boolean isOffsetRequired;

    private final Subscriber subscriber;

    public TopicSubscriber(@NotNull final Subscriber subscriber) {
        this.subscriber = subscriber;
        offset = new AtomicInteger(0);
        this.isOffsetRequired = subscriber.canResetOffset();
    }
}
