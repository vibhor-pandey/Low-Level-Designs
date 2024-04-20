import java.util.ArrayList;
import java.util.List;

public class BetsConsumer extends Thread {

    private final BetQueue queue;

    private final int[] winningBetsCombination;

    private final List<String> winningBets;

    private int betsConsumed = 0;

    public BetsConsumer(final BetQueue queue, final int[] combinations) {
        this.queue = queue;
        this.winningBetsCombination = combinations;
        this.winningBets = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (queue.isEmpty() && queue.isJobDone()) {
                    System.out.println("All Bets Consumed Successfully...");
                    break;
                }
                String bet = queue.remove();
                betsConsumed++;
                if(isWinningBet(bet)) {
                    System.out.println("----- Hurray Winning Bet -----");
                    winningBets.add(bet);
                }
                System.out.println("[CONSUME] Queue Size - " + queue.getQueueSize());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isWinningBet(final String bet) {
        int matchedNums = 4;
        for(int num : winningBetsCombination) {
            if(matchedNums == 0) return true;
            if(bet.contains(num + "")) matchedNums--;
        }
        return false;
    }

    public List<String> getWinningBets() {
        return winningBets;
    }

    public int getBetsConsumed() {
        return betsConsumed;
    }
}
