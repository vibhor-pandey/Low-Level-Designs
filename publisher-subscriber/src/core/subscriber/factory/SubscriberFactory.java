package core.subscriber.factory;

import com.sun.jdi.InvalidTypeException;
import core.subscriber.model.Subscriber;
import core.subscriber.model.SubscriberType;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class SubscriberFactory {
    public static Subscriber getSubscriber(@NotNull final SubscriberType type, @NotNull final String name) throws InvalidTypeException {
        switch (type) {
            case LAZY_SUBSCRIBER -> {
                return new LazySubscriber(UUID.randomUUID().toString(), name, true);
            }
            case REAL_TIME_SUBSCRIBER -> {
                return new RealTimeSubscriber(UUID.randomUUID().toString(), name, false);
            }
            default -> {
                throw new InvalidTypeException("Invalid Subscriber Type - " + type);
            }
        }
    }
}
