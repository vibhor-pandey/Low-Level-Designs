import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

class BetQueue {

    private static final int QUEUE_CAPACITY = 15;
    private Queue<String> queue;

    private int capacity;

    private volatile boolean isEmpty = true;
    private volatile boolean isFull = false;
    private volatile boolean isJobDone = false;


    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isFull() {
        return isFull;
    }

    public BetQueue(int capacity) {
        queue = new ArrayDeque<>(capacity);
        this.capacity = capacity;
    }

    public BetQueue() {
        queue = new ArrayDeque<>(QUEUE_CAPACITY);
        this.capacity = QUEUE_CAPACITY;
    }

    public synchronized int getQueueSize() {
        return queue.size();
    }

    public synchronized void add(String element) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("************ [WAITING] -- Queue Capacity Reached ***********");
            Thread.sleep(50);
            wait();
        }

        System.out.println("ADD[" + element + "] -- Success...");
        queue.add(element);
        isEmpty = false;
        if (queue.size() == capacity) isFull = true;
        notify();
    }

    public synchronized String remove() throws InterruptedException {
        //If queue is Empty | Not Done -> Wait
        while (queue.size() == 0 && !isJobDone) {
            System.out.println("************ [WAITING] -- Queue is Empty ************");
            Thread.sleep(50);
            if (isJobDone) return "";
            wait();
        }

        String element = queue.remove();
        System.out.println("REMOVE[" + element + "] -- Success...");
        if (queue.size() == 0) isEmpty = true;
        notify();
        return element;
    }

    public void jobDone() {
        System.out.println("Job Done Successfully...");
        this.isJobDone = true;
    }

    public boolean isJobDone() {
        return isJobDone;
    }

    public String getInformation() {
        return "";
    }
}