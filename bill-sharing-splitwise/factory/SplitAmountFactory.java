package factory;

import models.SplitType;

import java.util.HashMap;
import java.util.Map;


public class SplitAmountFactory {

    public Map<String, SplitAmount> getSplit(
            final SplitType type,
            final double amount,
            final Map<String, Double> participants
    ) {
        final Map<String, SplitAmount> split = new HashMap<>();
        switch (type) {
            case EQUAL -> {
                for(final String participant : participants.keySet()) {
                    final SplitAmount splitAmount = new EqualSplitAmount(amount / (participants.size() + 1));
                    split.put(participant, splitAmount);
                }
            }
            case EXACT -> {
                for(final String participant : participants.keySet()) {
                    final SplitAmount splitAmount = new ExactSplitAmount(participants.get(participant));
                    split.put(participant, splitAmount);
                }
            }
            default -> {
                for(final String participant : participants.keySet()) {
                    final double percent = participants.get(participant);
                    final double amountOwed = (percent * amount) / 100;
                    final SplitAmount splitAmount = new PercentageSplitAmount(amountOwed, percent);
                    split.put(participant, splitAmount);
                }
            }
        }
        return split;
    }
}
