package factory;

public abstract class SplitAmount {
    public double amount;

    public SplitAmount(double amount) {
        this.amount = amount;
    }

    public SplitAmount() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
