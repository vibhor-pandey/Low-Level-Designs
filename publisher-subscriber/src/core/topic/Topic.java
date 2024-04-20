package core.topic;

import core.message.model.Message;
import core.subscriber.model.TopicSubscriber;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
public class Topic {

    @Setter
    private String id;

    @Setter
    private String name;

    private List<Message> messages;

    private List<TopicSubscriber> subscribers;

    public void initTopic() {
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public void addMessage(@NotNull final Message message) {
        //TODO - If Queue is Full then wait()
        messages.add(message);
    }

    public void addSubscriber(@NotNull final TopicSubscriber subscriber) {
        subscribers.add(subscriber);
    }
}
