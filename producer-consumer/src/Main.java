
public class Main {

    private static final int MAX_BETS = 1_000;
    private static final String ALL_BETS_FILE = "bets.txt";
    private static final String WINNING_BETS_FILE = "winning-bets.txt";
    private static final int[] WINNING_COMBINATION = {2, 5, 7, 8, 3};

    public static void main(String[] args) throws InterruptedException {
        //Simulating All Bets to a File
        BetsSimulator.simulateBets(MAX_BETS, ALL_BETS_FILE);
        Thread.sleep(5000);

        ProduceConsume produceConsume = new ProduceConsume();
        produceConsume.init(ALL_BETS_FILE, WINNING_COMBINATION);
        System.out.println(produceConsume.getInformation());
        BetsSimulator.writeBetsToFile(produceConsume.getWinningBets(), WINNING_BETS_FILE);
    }
}
