package core.topic.impl;

import core.queue.PubSubQueue;
import core.subscriber.model.TopicSubscriber;
import core.topic.Topic;
import core.topic.TopicHandler;
import core.worker.ConsumeMessageWorker;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class TopicHandlerImpl implements TopicHandler {

    private final Map<String, ConsumeMessageWorker> subscriberWorkers;

    public TopicHandlerImpl() {
        subscriberWorkers = new HashMap<>();
    }

    private Topic getTopic(@NotNull final String topic) {
        return PubSubQueue.getPubSubQueue().getTopic(topic);
    }

    @Override
    public Topic createTopic(String name) {
        final Topic topic = new Topic();
        final Random random = new Random();
        topic.setId(
                random.nextInt(10000)
                + "-" + random.nextInt(10000)
                + "-" + random.nextInt(10000)
                + "-" + random.nextInt(10000)
        );
        topic.setName(name.toUpperCase());
        topic.initTopic();
        return topic;
    }

    @Override
    public void publish(@NotNull final String topic) {
        final Topic t = getTopic(topic);
        for (final TopicSubscriber subscriber : t.getSubscribers()) {
            startConsumeMessageWorkers(t, subscriber);
        }
    }

    private synchronized void startConsumeMessageWorkers(
            @NotNull final Topic topic,
            @NotNull final TopicSubscriber subscriber
    ) {
        ConsumeMessageWorker worker;
        if (!subscriberWorkers.containsKey(subscriber.getSubscriber().getId())) {
            worker = new ConsumeMessageWorker(topic, subscriber);
            subscriberWorkers.put(subscriber.getSubscriber().getId(), worker);
            new Thread(worker).start();
        }
        worker = subscriberWorkers.get(subscriber.getSubscriber().getId());
        worker.wakeUpIfRequired();
    }
}
