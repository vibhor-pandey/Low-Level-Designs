package core.queue;

import core.message.model.Message;
import core.subscriber.model.TopicSubscriber;
import core.topic.Topic;
import core.topic.TopicHandler;
import core.topic.impl.TopicHandlerImpl;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class PubSubQueue {
    private final Map<String, Topic> topics;

    private final TopicHandler topicHandler;

    private PubSubQueue() {
        topicHandler = new TopicHandlerImpl();
        topics = new HashMap<>();
    }

    private static class InstanceHolder {
        private static final PubSubQueue INSTANCE = new PubSubQueue();
    }

    public static synchronized PubSubQueue getPubSubQueue() {
        return InstanceHolder.INSTANCE;
    }

    public String createTopic(@NotNull final String name) {
        System.out.println("CREATE NEW TOPIC["+ name +"] Started...");
        Topic topic = topicHandler.createTopic(name);
        topics.put(topic.getId(), topic);
        System.out.println("CREATED NEW TOPIC["+ name +"] Completed...");
        return topic.getId();
    }

    public void publish(@NotNull final Message message, @NotNull final String topicId) throws InterruptedException {
        final Topic topic = topics.get(topicId);
        System.out.println("PUBLISH MESSAGE[" + message.getData() + "] TOPIC["+ topic.getName() +"] Started...");
        topic.addMessage(message);
        fanOutMessage(message.getData(), topic);
    }

    private void fanOutMessage(@NotNull final String msg, @NotNull final Topic topic) throws InterruptedException {
        System.out.println("FAN-OUT MESSAGE[" + msg + "] TOPIC["+ topic.getName() +"] Started...");
        final Thread t = new Thread(() -> topicHandler.publish(topic.getId()));
        t.start();
        System.out.println("FAN-OUT MESSAGE[" + msg + "] TOPIC["+ topic.getName() +"] Completed...");
        System.out.println("PUBLISH MESSAGE[" + msg + "] TOPIC["+ topic.getName() +"] Completed...");
    }

    public String subscribe(@NotNull final TopicSubscriber subscriber, @NotNull final String topicId) {
        final Topic topic = topics.get(topicId);
        System.out.println("SUBSCRIBE[" + subscriber.getSubscriber().getName() + "] ->  TOPIC["+ topic.getName() +"] Started...");
        topic.addSubscriber(subscriber);
        System.out.println("SUBSCRIBE[" + subscriber.getSubscriber().getName() + "] ->  TOPIC["+ topic.getName() +"] Completed...");
        currentTopics();
        return subscriber.getSubscriber().getId();
    }

    public Topic getTopic(@NotNull final String topic) {
        return this.topics.get(topic);
    }

    public void currentTopics() {
        System.out.println("Current Topics - \n" + topics.values());
    }
}
