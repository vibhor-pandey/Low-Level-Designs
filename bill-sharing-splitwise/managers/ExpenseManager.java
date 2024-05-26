package managers;

import factory.SplitAmount;
import factory.SplitAmountFactory;
import models.Expense;
import models.Participant;
import models.SplitType;

import java.util.*;

public class ExpenseManager {
    private final Map<Participant, List<Expense>> expenses;
    private final ParticipantManager participantManager;
    private final Map<String, Expense> expenseMap;

    private final Map<Participant, List<Expense>> balanceSheet;

    private final SplitAmountFactory splitAmountFactory;

    public ExpenseManager(final ParticipantManager participantManager) {
        this.expenses = new HashMap<>();
        this.expenseMap = new HashMap<>();
        this.balanceSheet = new HashMap<>();
        this.participantManager = participantManager;
        this.splitAmountFactory = new SplitAmountFactory();
    }

    private Expense createExpense(
            final String name,
            final double amount,
            final SplitType splitType,
            final Map<String, Double> participants,
            final String paidBy
    ) {
        final Expense expense = new Expense(name, amount, splitType, paidBy);

        final Map<Participant, SplitAmount> share = new HashMap<>();
        final Map<String, SplitAmount> splits = splitAmountFactory.getSplit(splitType, amount, participants);


        for(String participantName : splits.keySet()) {
            final Participant participant = participantManager.getParticipant(participantName);
            share.put(participant, splits.get(participantName));
            
            if(!balanceSheet.containsKey(participant)) {
                balanceSheet.put(participant, new ArrayList<>());
            }
            balanceSheet.get(participant).add(expense);
        }

        expense.setShare(share);
        return expense;
    }

    public void addExpense(
            final String name,
            final double amount,
            final SplitType splitType,
            final Map<String, Double> participants,
            final String paidBy
    ) {
        final Expense expense = createExpense(name, amount, splitType, participants, paidBy);
        final Participant participant = participantManager.getParticipant(paidBy);
        if(!expenses.containsKey(participant)) {
            expenses.put(participantManager.getParticipant(paidBy), new ArrayList<>());
        }
        expenses.get(participantManager.getParticipant(paidBy)).add(expense);
        expenseMap.put(expense.getName(), expense);
    }

    public void payExpense(final String participantName, final String expenseName) {
        final Expense expense = expenseMap.get(expenseName);
        final Participant payee = participantManager.getParticipant(participantName);
        expense.settleShare(payee);
        if (expense.isSettled()) {
            expenses.remove(participantManager.getParticipant(expense.getPaidBy()));
        }
        balanceSheet.getOrDefault(payee, new ArrayList<>()).remove(expense);
    }


    public List<String> showAllExpensesOfParticipant(final String participantName) {
        final Participant participant = participantManager.getParticipant(participantName);
        final List<Expense> lent = expenses.getOrDefault(participant, new ArrayList<>());
        final List<Expense> owed = balanceSheet.getOrDefault(participant, new ArrayList<>());
        final List<String> result = new ArrayList<>(lent.size() + owed.size());

        // "Name - Expense Name - Amount"

        for(final Expense expense : lent) {
            result.add(participant.getName() + " - " + expense.getName() + " - " + "(+)" + expense.getTotalUnpaidAmount() + "\n");
        }

        for(final Expense expense : owed) {
            result.add(participant.getName() + " - " + expense.getName() + " - " + "(-)" + expense.getShare().get(participant).getAmount() + "\n");
        }
        return result;
    }

    public List<String> showAllExpenses() {
        final List<String> result = new ArrayList<>();

        long sTime = System.nanoTime();
        //Lent
        for(final Participant creator : expenses.keySet()) {
            for(final Expense expense : expenses.get(creator)) {
                result.add(creator.getName() + " - " + expense.getName() + " :: " + "(+)" + expense.getTotalUnpaidAmount() + "\n");
                for(final Participant participant : expense.getShare().keySet()) {
                    result.add(participant.getName() + " - " + expense.getName() + " :: " + "(-)" + expense.getShare().get(participant).getAmount() + "\n");
                }
            }
        }
        long eTime = System.nanoTime();
        System.out.println((eTime - sTime) / 1_000_000.0);
        return result;
    }

    public List<String> showAllExpensesOptimized() {
        final List<String> result = new ArrayList<>();

        long sTime = System.nanoTime();
        //Lent
        for(final Participant participant : expenses.keySet()) {
            for (final Expense expense : expenses.getOrDefault(participant, new ArrayList<>())) {
                result.add(participant.getName() + " - " + expense.getName() + " :: " + "(+)" + expense.getTotalUnpaidAmount() + "\n");
            }
        }

        //Owed
        for (final Participant participant : balanceSheet.keySet()) {
            for (final Expense expense : balanceSheet.getOrDefault(participant, new ArrayList<>())) {
                result.add(participant.getName() + " - " + expense.getName() + " :: " + "(-)" + expense.getShare().get(participant).getAmount() + "\n");
            }
        }
        long eTime = System.nanoTime();
        System.out.println("Optimized: " + ((eTime - sTime) / 1_000_000.0));
        return result;
    }
}
