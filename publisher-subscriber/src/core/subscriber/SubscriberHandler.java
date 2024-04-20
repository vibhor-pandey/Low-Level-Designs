package core.subscriber;

import com.sun.jdi.InvalidTypeException;
import core.subscriber.model.SubscriberType;

import javax.validation.constraints.NotNull;

public interface SubscriberHandler {

    //Subscribe to a Topic by Id
    void subscribe(@NotNull final String name, @NotNull final String topic, @NotNull SubscriberType type) throws InvalidTypeException;

    //Reset
    boolean resetOffset(String topic, int offset);
}
