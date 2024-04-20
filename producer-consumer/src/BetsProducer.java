import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BetsProducer extends Thread {

    private BetQueue queue;
    private FileReader reader;

    private Scanner input;

    private int betProduced = 0;

    public BetsProducer(final BetQueue queue, final String file) {
        this.queue = queue;
        try {
            this.reader = new FileReader(file);
            input = new Scanner(this.reader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int getBetProduced() {
        return betProduced;
    }

    @Override
    public void run() {
        try {
            while (input.hasNext()) {
                queue.add(input.nextLine());
                System.out.println("[PRODUCE] Queue Size - " + queue.getQueueSize());
                betProduced++;
            }
            queue.jobDone();
            System.out.println("All Bets Produces Successfully...");
            reader.close();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isJobDone() {
        return queue.isJobDone();
    }
}
