package core.topic;


import javax.validation.constraints.NotNull;

public interface TopicHandler {

    Topic createTopic(@NotNull final String name);

    void publish(@NotNull final String topic);
}
