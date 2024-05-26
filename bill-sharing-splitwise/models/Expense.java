package models;

import factory.SplitAmount;

import java.util.Map;

public class Expense {
    private final String name;
    private double totalUnpaidAmount;
    private final SplitType splitType;
    private Map<Participant, SplitAmount> share;
    private final String paidBy;
    private boolean isSettled;


    public Expense(
            String name,
            double amount,
            SplitType splitType,
            String paidBy
    ) {
        this.name = name;
        this.totalUnpaidAmount = amount;
        this.splitType = splitType;
        this.paidBy = paidBy;
        this.isSettled = false;
    }

    public void setShare(Map<Participant, SplitAmount> share) {
        this.share = share;
        this.setTotalUnpaidAmount(share.values().stream().map(SplitAmount::getAmount).mapToDouble(Double::doubleValue).sum());
    }

    public void setTotalUnpaidAmount(double totalUnpaidAmount) {
        this.totalUnpaidAmount = totalUnpaidAmount;
    }

    public String getName() {
        return name;
    }

    public double getTotalUnpaidAmount() {
        return totalUnpaidAmount;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public Map<Participant, SplitAmount> getShare() {
        return share;
    }

    public void settleShare(final Participant participant) {
        setTotalUnpaidAmount(getTotalUnpaidAmount() - share.get(participant).getAmount());
        this.share.remove(participant);
        if (totalUnpaidAmount == 0.0) {
            isSettled = true;
        }
    }

    public boolean isSettled() {
        return isSettled;
    }

    public String getPaidBy() {
        return paidBy;
    }
}
