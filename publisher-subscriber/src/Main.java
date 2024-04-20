import com.sun.jdi.InvalidTypeException;
import core.publisher.PublisherHandler;
import core.publisher.impl.PublisherHandlerImpl;
import core.subscriber.SubscriberHandler;
import core.subscriber.impl.SubscriberHandlerImpl;
import core.subscriber.model.SubscriberType;

public class Main {

    private static final String[] ANIMALS = new String[] {"COW", "DOG", "BAT", "TIGER", "OXX"};

    public static void main(String[] args) throws InterruptedException, InvalidTypeException {
        System.out.println("Hello world!");
        System.out.println("Initiating Pub-Sub");

        PublisherHandler publisherHandler = PublisherHandlerImpl.getPublisherHandler();
        SubscriberHandler subscriberHandler = SubscriberHandlerImpl.getSubscriberHandler();

        //Create TOPIC
        final String topicTd = publisherHandler.createTopic("T1");
        System.out.println("Topic Created With ID - " + topicTd);
        System.out.println("Pub-Sub Completed...");

        //Subscribe to a Topic
        System.out.println("\nInitiating SUBSCRIBE TOPIC...");
        subscriberHandler.subscribe("T1-S1", topicTd, SubscriberType.LAZY_SUBSCRIBER);
        subscriberHandler.subscribe("T1-S2", topicTd, SubscriberType.REAL_TIME_SUBSCRIBER);
        subscriberHandler.subscribe("T1-S3", topicTd, SubscriberType.LAZY_SUBSCRIBER);
        System.out.println("\nCompleted SUBSCRIBE TOPIC...");

        //Publish Message to a Topic
        System.out.println("\nInitiating PUBLISH MESSAGES...");
        for(int i = 0; i < 4; i++) {
            String message = "Message[" + i + "]";
            publisherHandler.publish(message, topicTd);
//            Thread.sleep(1100);
        }
        System.out.println("\nCompleted PUBLISH MESSAGES...");
    }
}