package core.subscriber.factory;

import core.message.model.Message;
import core.subscriber.model.Subscriber;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class LazySubscriber implements Subscriber {

    private String id;

    private String name;

    private boolean canResetOffset;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean canResetOffset() {
        return this.canResetOffset;
    }

    @Override
    public void consume(@NotNull final Message message, @NotNull final String topic, @NotNull String subscriber) throws InterruptedException {
        System.out.println(topic + " : " + subscriber + " : " +
                "LAZY-CONSUME[" + message.getData() + "] MESSAGE Started...");
        Thread.sleep(1000);
        System.out.println(topic + " : " + subscriber + " : " +
                "LAZY-CONSUME[" + message.getData() + "] MESSAGE Completed...");
    }
}
