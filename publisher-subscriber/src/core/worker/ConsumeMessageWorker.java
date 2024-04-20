package core.worker;

import core.message.model.Message;
import core.subscriber.model.TopicSubscriber;
import core.topic.Topic;

import javax.validation.constraints.NotNull;

public class ConsumeMessageWorker implements Runnable {

    private final Topic topic;

    private final TopicSubscriber subscriber;

    public ConsumeMessageWorker(
            @NotNull final Topic topic,
            @NotNull final TopicSubscriber subscriber
    ) {
        this.topic = topic;
        this.subscriber = subscriber;
    }

    @Override
    public void run() {
        synchronized (subscriber) {
            do {
                System.out.println("SUBSCRIBER - " + subscriber.getSubscriber().getName());
                try {
                    consumeMessageByOffset();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } while (true);
        }
    }

    private synchronized void consumeMessageByOffset() throws InterruptedException {
        final int currentOffset = subscriber.getOffset().get();
        while (currentOffset >= topic.getMessages().size()) {
            System.out.println("WAITING...");
            subscriber.wait();
        }
        final Message message = topic.getMessages().get(currentOffset);
        subscriber.getSubscriber().consume(message, topic.getName(), subscriber.getSubscriber().getName());
        subscriber.getOffset().compareAndSet(currentOffset, currentOffset + 1);
    }

    private synchronized void consumeMessageWithoutOffset() throws InterruptedException {
        final Message message = topic.getMessages().get(topic.getMessages().size() - 1);
        subscriber.getSubscriber().consume(message, topic.getName(), subscriber.getSubscriber().getName());
    }

    public synchronized void wakeUpIfRequired() {
        synchronized (subscriber) {
            subscriber.notifyAll();
        }
    }
}
