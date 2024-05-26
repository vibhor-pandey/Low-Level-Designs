package models;

import java.util.List;

public class Group {
    private final String name;
    private final List<String> participants;
    private final List<Expense> expenses;

    public Group(String name, List<String> participants, List<Expense> expenses) {
        this.name = name;
        this.participants = participants;
        this.expenses = expenses;
    }

    public String getName() {
        return name;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
