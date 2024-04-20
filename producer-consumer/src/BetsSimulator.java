import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BetsSimulator {

    private static String generateRandomBet(int size) {
        StringBuilder bet = new StringBuilder();

        for (int i = 0; i < size; i++) {
            bet.append((int) (Math.random() * 9));
        }
        return bet.toString();
    }

    private static List<String> generateBets(int n) {
        List<String> bets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            bets.add(generateRandomBet(10));
        }
        System.out.println("All bets are generated!");
        return bets;
    }

    public static void writeBetsToFile(List<String> bets, String fileName) {
        try(FileWriter writer = new FileWriter(fileName)) {
            for (String bet : bets) {
                writer.write(bet);
                writer.write("\n");
            }
            System.out.println("All bets have been Pushed to File -> " + fileName);
            writer.close();
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> readBetsFromFile(String fileName) {
        List<String> bets = new ArrayList<>();
        try(FileReader reader = new FileReader(fileName)) {
            Scanner sc = new Scanner(reader);
            while(sc.hasNext()) {
                bets.add(sc.nextLine());
            }
            System.out.println("All bets have been Read.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return bets;
    }

    public static void simulateBets(int betsSize, String file) {
        System.out.println("Simulating all Bets -> " + file);
        List<String> bets = generateBets(betsSize);
        writeBetsToFile(bets, file);
    }
}
