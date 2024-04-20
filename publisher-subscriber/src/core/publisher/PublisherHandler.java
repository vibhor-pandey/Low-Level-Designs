package core.publisher;



public interface PublisherHandler {

    //Returns ID of the newly created topic
    String createTopic(String Name);

    //Publishes new message to a Topic
    void publish(String  message, String topic) throws InterruptedException;
}
