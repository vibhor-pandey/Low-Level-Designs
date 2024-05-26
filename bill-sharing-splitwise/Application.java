import managers.ExpenseManager;
import managers.ParticipantManager;
import models.SplitType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        //Create User base
        final ParticipantManager manager = new ParticipantManager();
        createUserBase(manager);

        final ExpenseManager expenseManager = new ExpenseManager(manager);
        for (int i = 1; i < 2; i++) {
            createSampleInputForEqual(expenseManager, i);

            createSampleInputForExact(expenseManager, i);
            createSampleInputForExact2(expenseManager, i);
            createSampleInputForEqual2(expenseManager, i);
        }
//        System.out.println(expenseManager.showAllExpensesOfParticipant("Vibhor"));
//        System.out.println(expenseManager.showAllExpensesOfParticipant("Vibhor"));

        List<String> result = expenseManager.showAllExpensesOptimized();
        System.out.println(result);

        expenseManager.payExpense("Karan", "Rent-1");
        expenseManager.payExpense("Vibhor", "Rent-1");

        List<String> result2 = expenseManager.showAllExpensesOfParticipant("Neeraj");
        System.out.println(result2);

    }

    private static void createSampleInputForExact(final ExpenseManager expenseManager, int index) {
        Map<String, Double> share = new HashMap<>();
        share.put("Karan", 2500.0);
        share.put("Vibhor", 3500.0);
        expenseManager.addExpense("Rent-" + index, 8000, SplitType.valueOf("EXACT"), share, "Neeraj");
    }

    private static void createSampleInputForEqual(final ExpenseManager expenseManager, int index) {
        Map<String, Double> share = new HashMap<>();
        share.put("Neeraj", 0.0);
        share.put("Karan", 0.0);
        share.put("Hari", 0.0);
        expenseManager.addExpense("Movie-" + index, 2000, SplitType.valueOf("EQUAL"), share, "Vibhor");
    }

    private static void createSampleInputForEqual2(final ExpenseManager expenseManager, int index) {
        Map<String, Double> share = new HashMap<>();
        share.put("Neeraj", 0.0);
        share.put("Karan", 0.0);
        share.put("Swathi", 0.0);
        expenseManager.addExpense("Trip-" + index, 20000, SplitType.valueOf("EQUAL"), share, "Swathi");
    }

    private static void createSampleInputForExact2(final ExpenseManager expenseManager, int index) {
        Map<String, Double> share = new HashMap<>();
        share.put("Neeraj", 200.0);
        share.put("Karan", 500.0);
        share.put("Hari", 400.0);
        expenseManager.addExpense("Basketball-" + index, 1600, SplitType.valueOf("EXACT"), share, "Karan");
    }

    private static void createUserBase(final ParticipantManager manager) {
        manager.createParticipant("Vibhor", "vibhor@gmail.com");
        manager.createParticipant("Neeraj", "neeraj@gmail.com");
        manager.createParticipant("Karan", "karan@gmail.com");
        manager.createParticipant("Hari", "hari@gmail.com");
        manager.createParticipant("Swathi", "swathi@gmail.com");
    }

}
