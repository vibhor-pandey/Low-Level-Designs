import java.util.ArrayList;
import java.util.List;

public class ProduceConsume {

    private BetQueue queue;

    private BetsConsumer consumer;
    private BetsProducer producer;

    private static final int BET_QUEUE_CAPACITY = 20;

    public void init(
            String inputFile,
            int[] combinations
    ) throws InterruptedException {
        queue = new BetQueue(BET_QUEUE_CAPACITY);
        consumer = new BetsConsumer(queue, combinations);
        producer = new BetsProducer(queue, inputFile);

        Thread[] threads = new Thread[]{producer, consumer};
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("*********** END ***********");
    }

    public String getInformation() {
        return "Bets Produced - " + producer.getBetProduced()
                + "\nBets Consumed - " + consumer.getBetsConsumed()
                + "\nWinning Bets - " + getWinningBets().size()
                + "\nUnprocessed Bets - " + Math.abs(producer.getBetProduced() - consumer.getBetsConsumed());
    }

    public List<String> getWinningBets() {
        return consumer.getWinningBets();
    }

    public boolean isJobDone() {
        return producer.isJobDone();
    }
}
